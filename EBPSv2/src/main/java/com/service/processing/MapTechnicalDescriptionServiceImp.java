package com.service.processing;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoMapTechnicalDescription;
import com.model.application.ApplicationApproved;
import com.model.processing.MapTechnicalDescription;

@Service
public class MapTechnicalDescriptionServiceImp implements MapTechnicalDescriptionService {

    @Autowired
    DaoMapTechnicalDescription da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getindex(String applicationNo) {
        message.list = da.getAll("from MapTechnicalDescription where applicationNo='" + applicationNo + "'");
        if (message.list.isEmpty()) {
            return message.respondWithError("Record not found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));
        message.map.put("comment", message.getComment(applicationNo, "39"));
        message.map.put("history", message.getHistory(applicationNo, "39"));
        return message.map;
    }

    @Override
    public Object buildingDetails(String unit, String id) {
        if (unit.equalsIgnoreCase("m")) {
            sql = "SELECT FLOOR floor,LENGTH length,WIDTH width,HEIGHT height,AREA area, N.NAME floorName FROM building_permit_floor F,floor_names N WHERE N.ID = F.FLOOR AND APPLICATION_NO='" + id + "' ORDER BY floor";
        } else if (unit.equalsIgnoreCase("f")) {
            sql = "SELECT FLOOR floor,LENGTH length,WIDTH width,HEIGHT height,FEET_AREA area, N.NAME floorName FROM building_permit_floor F,floor_names N WHERE N.ID = F.FLOOR AND APPLICATION_NO='" + id + "' ORDER BY floor";
        }
        return message.db.getRecord(sql);
    }

    @Override
    public Object save(MapTechnicalDescription obj, String Authorization) {

        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        obj.setEnterBy(td.getUserName());
        obj.setEnterDate(new Date());
        String userType = td.getUserType();

        if (!message.checkSaveStatus(userType, "39", obj.getApplicationNo())) {
            return message.respondWithError(message.getMsg());
        }
        try {

            obj.setErStatus("P");
            obj.setEnterBy(td.getUserName());
            obj.setEnterDate(new Date());
            row = da.save(obj);
            message.setHistory(obj.getApplicationNo(), td.getUserType(), "39", "S", td.getUserName());
            msg = da.getMsg();

            if (row > 0) {
                message.db.save(message.getEnterByStatus(userType, 39, obj.getApplicationNo()));
                message.map = new HashMap();//("39");
                message.map.put("message", "Success");
                return message.map;
            } else {
                if (msg.contains("Duplicate entry")) {
                    msg = "This record already exist";
                }
                return message.respondWithError(msg);
            }

        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
        return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "39");
    }
}/*
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }

        if (!message.checkApproveStatus(td.getUserType(), "39", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        String status = obj.getStatus();
        sql = "SELECT coalesce(er_status,'P') \"erStatus\" , coalesce(ser_status,'P') \"serStatus\", coalesce(chief_status,'P') \"chiefStatus\", enter_by \"enterBy\" FROM map_technical_description WHERE application_no=" + applicationNo;
        message.list = message.db.getRecord(sql);
        if (message.list.isEmpty()) {
            return message.respondWithError("Invalid Application No.");
        }
        message.map = (Map) message.list.get(0);
        String erStatus = message.map.get("erStatus").toString();
        String serStatus = message.map.get("serStatus").toString();
        String chiefStatus = message.map.get("chiefStatus").toString();
        String enterBy= message.map.get("enterBy").toString();

        if (td.getUserType().equalsIgnoreCase("B")) {
            if (chiefStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Chief!!");
            }
            if (erStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Engineer!!");
            }

            if (serStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by SubEngineer!!");
            }

            if (status.equalsIgnoreCase("A")) {
                sql = "UPDATE map_technical_description SET ser_date=NOW(),ser_name='" + td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "39", status, td.getUserName());

                sql = "UPDATE building_permit_application SET sub_engineer_status='" + status + "',sub_engineer_action='39',application_status='" + status + "',application_action='39',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE map_technical_description SET enter_date=null, ser_date=NOW(),ser_name='" + td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "39", status, td.getUserName());

                sql = "UPDATE building_permit_application SET sub_engineer_status='" + status + "',sub_engineer_action='39',application_status='" + status + "',application_action='39',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), "B", 39, obj.getComment(), obj.getRemark(),enterBy);
            }
            //return message.map;
        } else if (td.getUserType().equalsIgnoreCase("A")) {
            if (chiefStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Chief!!");
            }
            if (erStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Engineer!!");
            }

            if (status.equalsIgnoreCase("A")) {
                sql = "UPDATE map_technical_description SET er_date=NOW(),er_name='" + td.getUserName() + "',er_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "39", status, td.getUserName());

                sql = "UPDATE building_permit_application SET engineer_status='" + status + "',engineer_action='39',application_status='" + status + "',application_action='39',application_action_by='A' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE map_technical_description SET enter_date=null, er_date=NOW(),er_name='" + td.getUserName() + "',er_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "39", status, td.getUserName());

                sql = "UPDATE building_permit_application SET engineer_status='" + status + "',engineer_action='39',application_status='" + status + "',application_action='39',application_action_by='A' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 39, obj.getComment(), obj.getRemark(),enterBy);
            }
        } else if (td.getUserType().equalsIgnoreCase("C")) {
            if (chiefStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Chief!!");
            }

            if (status.equalsIgnoreCase("A")) {
                sql = "UPDATE map_technical_description SET chief_date=NOW(),chief_name='" + td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "39", status, td.getUserName());

                sql = "UPDATE building_permit_application SET chief_status='" + status + "',chief_action='39',application_status='" + status + "',application_action='39',application_action_by='C' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE map_technical_description SET enter_date=null, chief_date=NOW(),chief_name='" + td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "39", status, td.getUserName());

                sql = "UPDATE building_permit_application SET chief_status='" + status + "',chief_action='39',application_status='" + status + "',application_action='39',application_action_by='C' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 39, obj.getComment(), obj.getRemark(),enterBy);
            }
        }
        return message.map;
    }
}
*/

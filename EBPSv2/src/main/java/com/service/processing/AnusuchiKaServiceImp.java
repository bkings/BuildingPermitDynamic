package com.service.processing;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoImpAnusuchiKa;
import com.log.ApplicationLog;
import com.model.application.ApplicationApproved;
import com.model.processing.AnusuchiKa;

import model.Message;

@Service
public class AnusuchiKaServiceImp implements AnusuchiKaService {

    @Autowired
    DaoImpAnusuchiKa da;
    Message message = new Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getData(String Authorization, String applicationNo) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return ResponseEntity.ok(message.respondWithError("Authorization Error"));
        }
        message.list = da.getAll("from AnusuchiKa where applicationNo='" + applicationNo + "'");
        if (message.list.isEmpty()) {
            return message.respondWithError("Record not found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));
        message.map.put("comment", message.getComment(applicationNo, "2"));
        message.map.put("history", message.getHistory(applicationNo, "2"));
        ApplicationLog.save("api/Processing/Phase1/AnusuchiKa", "GET", td.getUserName(), "get the form");
        return message.map;
    }

    @Override
    public Object Save(AnusuchiKa obj, String Authorization) {

        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return ResponseEntity.ok(message.respondWithError("Authorization Error"));
        }

        String userType = td.getUserType();

        long applicationNo = obj.getApplicationNo();
        if (!message.checkSaveStatus(userType, "2", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        obj.setEnterBy(td.getUserId());
        obj.setSerDate(null);
        obj.setErDate(null);
        obj.setEnterDate(new Date());
        obj.setSerStatus("P");
        obj.setErStatus("P");
        row = da.save(obj);
        message.setHistory(applicationNo, td.getUserType(), "2", "S", td.getUserName());
        msg = da.getMsg();
        if (row == 1) {
            message.db.save(message.getEnterByStatus(userType, 2, applicationNo));
            message.map = new HashMap();
            message.map.put("message", "Success");
            return message.map;
        } else {
            if (msg.contains("Duplicate entry")) {
                return ResponseEntity.ok(message.respondWithMessage("designer already approved this process."));
            }
        }
        return ResponseEntity.ok(message.respondWithError(msg));

    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
        return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "2");
    }
}/*  
JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }

        if (!message.checkApproveStatus(td.getUserType(), "2", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }

        String status = obj.getStatus();
        sql = "SELECT coalesce(er_status,'P') \"erStatus\" , coalesce(ser_status,'P') \"serStatus\", coalesce(chief_status,'P') \"chiefStatus\",building_class \"buildingClass\", enter_by \"enterBy\" FROM anusuchi_ka WHERE application_no=" + applicationNo;
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
                sql = "UPDATE anusuchi_ka SET ser_date=NOW(),ser_name='" + td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "2", status, td.getUserName());

                sql = "UPDATE building_permit_application SET sub_engineer_status='" + status + "',sub_engineer_action='2',application_status='" + status + "',application_action='2',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Approved");
                ApplicationLog.save("api/Processing/Phase1/AnusuchiKa", "PUT", td.getUserName(), "approve form");
            } else {
                sql = "UPDATE anusuchi_ka SET enter_date=null, ser_date=NOW(),ser_name='" + td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "2", status, td.getUserName());

                sql = "UPDATE building_permit_application SET sub_engineer_status='" + status + "',sub_engineer_action='2',application_status='" + status + "',application_action='2',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);

                message.map = new HashMap();
                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), "B", 2, obj.getComment(), obj.getRemark(),enterBy);
                ApplicationLog.save("api/Processing/Phase1/AnusuchiKa", "PUT", td.getUserName(), "reject form");
            }
        } else if (td.getUserType().equalsIgnoreCase("A")) {
            if (chiefStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Chief!!");
            }
            if (erStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Engineer!!");
            }

            if (status.equalsIgnoreCase("A")) {
                sql = "UPDATE anusuchi_ka SET er_date=NOW(),er_name='" + td.getUserName() + "',er_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "2", status, td.getUserName());

                sql = "UPDATE building_permit_application SET engineer_status='" + status + "',engineer_action='2',application_status='" + status + "',application_action='2',application_action_by='A' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Approved");
                ApplicationLog.save("api/Processing/Phase1/AnusuchiKa", "PUT", td.getUserName(), "approve form");
            } else {
                sql = "UPDATE anusuchi_ka SET enter_date=null, er_date=NOW(),er_name='" + td.getUserName() + "',er_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "2", status, td.getUserName());

                sql = "UPDATE building_permit_application SET engineer_status='" + status + "',engineer_action='2',application_status='" + status + "',application_action='2',application_action_by='A' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Rejected.");
                ApplicationLog.save("api/Processing/Phase1/AnusuchiKa", "PUT", td.getUserName(), "reject form");
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 2, obj.getComment(), obj.getRemark(),enterBy);
            }
        } else if (td.getUserType().equalsIgnoreCase("C")) {
            if (chiefStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Chief!!");
            }

            if (status.equalsIgnoreCase("A")) {
                sql = "UPDATE anusuchi_ka SET chief_date=NOW(),chief_name='" + td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "2", status, td.getUserName());

                sql = "UPDATE building_permit_application SET chief_status='" + status + "',chief_action='2',application_status='" + status + "',application_action='2',application_action_by='C' WHERE id=" + applicationNo;
                message.db.save(sql);
                ApplicationLog.save("api/Processing/Phase1/AnusuchiKa", "PUT", td.getUserName(), "approve form");
                message.map = new HashMap();
                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE anusuchi_ka SET enter_date=null, chief_date=NOW(),chief_name='" + td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "2", status, td.getUserName());

                sql = "UPDATE building_permit_application SET chief_status='" + status + "',chief_action='2',application_status='" + status + "',application_action='2',application_action_by='C' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 2, obj.getComment(), obj.getRemark(),enterBy);
                ApplicationLog.save("api/Processing/Phase1/AnusuchiKa", "PUT", td.getUserName(), "reject form");
                message.map = new HashMap();
                message.map.put("message", "Application Rejected.");
            }
        }

        return message.map;
    }
}
*/

package com.service.processing;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoArchitectural;
import com.dao.processing.DaoImpArchitectura;
import com.model.application.ApplicationApproved;
import com.model.processing.ArchitecturalDesignB;
import com.model.processing.ArchitecturalDesignBDetails;
import com.model.processing.ArchitecturalDesignDetailsPK;

import model.Message;

@Service
public class ArchitecturalDesignBServiceImp implements ArchitecturalDesignBService {

    DaoArchitectural da = new DaoImpArchitectura();
    Message message = new Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getindex(String applicationNo) {
        model.DB db = new model.DB();
        try {
            if (applicationNo.length() == 0) {
                applicationNo = "";
            }
        } catch (Exception e) {
        }
        sql = "SELECT BUILDING_TYPE buildingType,coalesce(B.ENTER_BY,'') \"enterBy\",B.ENTER_DATE \"enterDate\",B.ER_DATE \"erDate\",B.ER_NAME \"erName\",coalesce(B.ER_STATUS,'P') \"erStatus\",B.SER_DATE \"serDate\",B.SER_NAME \"serName\",coalesce(B.SER_STATUS,'P') \"serStatus\" FROM building_permit_application A LEFT JOIN architectural_design_b B ON A.ID=B.APPLICATION_NO WHERE A.ID='" + applicationNo + "'";
        message.list = db.getRecord(sql);
        if (message.list.isEmpty()) {
            return message.respondWithError("invalid application no");
        }
        message.map = (Map) message.list.get(0);

//    sql = "SELECT group_id \"groupId\" ,M.BUILDING_ELEMENTS \"buildingElements\",M.GROUP_NAME \"groupName\",M.UNIT \"unit\",QTY \"qty\",REMARK remarks,M.ID \"classId\" FROM architectural_design_b_master M LEFT JOIN architectural_design_b_details D  ON M.ID=D.CLASS_ID WHERE coalesce(D.APPLICATION_NO,'" + applicationNo + "')='" + applicationNo + "' ORDER BY M.ID";
        sql = "SELECT group_id \"groupId\" ,M.BUILDING_ELEMENTS \"buildingElements\",M.GROUP_NAME \"groupName\",M.UNIT \"unit\",CONCAT(QTY,'') \"qty\",REMARK remarks,M.ID \"classId\",D.unit \"usedUnit\" FROM architectural_design_b_master M ,architectural_design_b_details D WHERE M.ID=D.CLASS_ID AND D.APPLICATION_NO='" + applicationNo + "' "
                + " UNION "
                + "SELECT group_id \"groupId\" ,M.BUILDING_ELEMENTS \"buildingElements\",M.GROUP_NAME \"groupName\",M.UNIT \"unit\",' ' \"qty\",'' AS  remarks,M.ID \"classId\",'' \"usedUnit\" FROM architectural_design_b_master M WHERE M.id NOT IN(SELECT D.class_id FROM architectural_design_b_details D WHERE D.application_no='" + applicationNo + "') ORDER BY \"classId\"";
        message.list = db.getRecord(sql);
        message.map.put("listData", message.list);
        message.map.put("comment", message.getComment(applicationNo, "3"));
        message.map.put("history", message.getHistory(applicationNo, "3"));
        return message.map;
    }

    @Override
    public Object save(long applicationNo, String jsonData, String Authorization) {
        try {
            JWTToken td = new JWTToken(Authorization);
            if (!td.isValid()) {
                return message.respondWithError("Authorization Error");
            }
            String userType = td.getUserType();

            if (!message.checkSaveStatus(userType, "3", applicationNo)) {
                return message.respondWithError(message.getMsg());
            }

            ArchitecturalDesignB obj = new ArchitecturalDesignB();
            ArchitecturalDesignBDetails details = new ArchitecturalDesignBDetails();
            ArchitecturalDesignDetailsPK pk = new ArchitecturalDesignDetailsPK();
            String jsonDataArray[] = message.jsonDataToStringArray(jsonData);
            try {
                message.map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonDataArray[0], new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {
                });
                obj.setBuildingType(message.map.get("buildingType").toString());
            } catch (Exception e) {             
            }
            String classId;
            obj.setEnterBy(td.getUserId());
            obj.setEnterDate(new Date());
            obj.setSerDate(null);
            obj.setErStatus("P");
            obj.setErDate(null);
            obj.setSerStatus("P");
            obj.setApplicationNo(applicationNo);
            row = da.save(obj);       
            message.setHistory(applicationNo, td.getUserType(), "3", "S", td.getUserName());
            message.list = message.objectMapper.readValue(jsonDataArray[1], new com.fasterxml.jackson.core.type.TypeReference<List>() {
            });

            if (row == 1) {
                for (int i = 0; i < message.list.size(); i++) {
                    message.map = (Map) message.list.get(i);
                    classId = message.map.get("classId").toString();
                    details.setQty(message.map.get("qty").toString());
                    try {
                        details.setUnit(message.map.get("usedUnit").toString());
                    } catch (Exception e) {

                    }
                    try {
                        details.setRemark(message.map.get("remarks").toString());
                    } catch (Exception e) {
                    }
                    pk.setApplicationNo(applicationNo);
                    pk.setClassId(classId);
                    details.setPk(pk);
                    da.save(details);
                }
            }
            msg = da.getMsg();
            if (row > 0) {
                message.db.save(message.getEnterByStatus(userType, 3, applicationNo));
                message.map = new HashMap();//("4");
                message.map.put("message", "Success");
                return message.map;
            } else {
                if (msg.contains("Duplicate entry")) {
                    msg = "This record already exist";
                }
                return message.respondWithError(msg);
            }

        } catch (Exception e) {
            System.out.println("exception::" + e);
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
     return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "3");
    }
}/*  
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        if (!message.checkApproveStatus(td.getUserType(), "3", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }

        String status = obj.getStatus();
        sql = "SELECT coalesce(er_status,'P') \"erStatus\" , coalesce(ser_status,'P') \"serStatus\", coalesce(chief_status,'P') \"chiefStatus\", enter_by \"enterBy\" FROM architectural_design_b WHERE application_no=" + applicationNo;
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
                sql = "UPDATE architectural_design_b SET ser_date=NOW(),ser_name='" + td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "3", status, td.getUserName());

                sql = "UPDATE building_permit_application SET sub_engineer_status='" + status + "',sub_engineer_action='3',application_status='" + status + "',application_action='3',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE architectural_design_b SET enter_date=null,ser_date=NOW(),ser_name='" + td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "3", status, td.getUserName());

                sql = "UPDATE building_permit_application SET sub_engineer_status='" + status + "',sub_engineer_action='3',application_status='" + status + "',application_action='3',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 3, obj.getComment(), obj.getRemark(),enterBy);
            }
   
        } else if (td.getUserType().equalsIgnoreCase("A")) {
            if (chiefStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Chief!!");
            }
            if (erStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Engineer!!");
            }

            if (status.equalsIgnoreCase("A")) {
                sql = "UPDATE architectural_design_b SET er_date=NOW(),er_name='" + td.getUserName() + "',er_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "3", status, td.getUserName());

                sql = "UPDATE building_permit_application SET engineer_status='" + status + "',engineer_action='3',application_status='" + status + "',application_action='3',application_action_by='A' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE architectural_design_b SET enter_date=null, er_date=NOW(),er_name='" + td.getUserName() + "',er_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "3", status, td.getUserName());

                sql = "UPDATE building_permit_application SET engineer_status='" + status + "',engineer_action='3',application_status='" + status + "',application_action='3',application_action_by='A' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 3, obj.getComment(), obj.getRemark(),enterBy);
            }
        } else if (td.getUserType().equalsIgnoreCase("C")) {
            if (chiefStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Chief!!");
            }
            if (status.equalsIgnoreCase("A")) {
                sql = "UPDATE architectural_design_b SET chief_date=NOW(),chief_name='" + td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "3", status, td.getUserName());

                sql = "UPDATE building_permit_application SET chief_status='" + status + "',chief_action='3',application_status='" + status + "',application_action='3',application_action_by='C' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();

                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE architectural_design_b SET enter_date=null, chief_date=NOW(),chief_name='" + td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "3", status, td.getUserName());

                sql = "UPDATE building_permit_application SET chief_status='" + status + "',chief_action='3',application_status='" + status + "',application_action='3',application_action_by='C' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();

                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 3, obj.getComment(), obj.getRemark(),enterBy);
            }
        }
        //  message.setApplicationActionStatus(applicationNo, td.getUserType(), 3, status);
        return message.map;
    }
}
*/
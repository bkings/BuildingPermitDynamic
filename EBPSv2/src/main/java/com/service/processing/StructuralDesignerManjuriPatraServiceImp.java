package com.service.processing;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoStructuralDesignerManjuriPatra;
import com.log.ApplicationLog;
import com.model.application.ApplicationApproved;
import com.model.processing.StructuralDesignerManjuriPatra;
import com.model.utility.OrganizationUser;

import model.DB;
import model.Message;

@Service
public class StructuralDesignerManjuriPatraServiceImp implements StructuralDesignerManjuriPatraService {

    @Autowired
    DaoStructuralDesignerManjuriPatra da;
    DB db = new DB();
    Message message = new Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll(String Authorization, String applicationNo) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
//        message.list = da.getAll("from structural_designer_manjuri_patra where applicationNo='" + applicationNo + "'");
        message.list = da.getAll("from StructuralDesignerManjuriPatra where applicationNo='" + applicationNo + "'");
        if (message.list.isEmpty()) {
//            return message.respondWithError("Application no invalid or not a procee in AnusuchiGha");
        	return message.respondWithError("Record Not Found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));
        message.map.put("comment", message.getComment(applicationNo, "10"));
        message.map.put("history", message.getHistory(applicationNo, "10"));
        ApplicationLog.save("api/Processing/Phase1/StructuralDesignerManjuriPatra", "GET", td.getUserName(), "get the form");
        return message.map;
    }

    @Override
    public Object save(StructuralDesignerManjuriPatra obj, String Authorization) {

        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return ResponseEntity.ok(message.respondWithError("Authorization Error"));
        }

        String userType = td.getUserType();

        long applicationNo = obj.getApplicationNo();
        if (!message.checkSaveStatus(userType, "10", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }

        sql = "SELECT ER_STATUS erstatus,SER_STATUS serstatus FROM structural_designer_manjuri_patra WHERE APPLICATION_NO='" + applicationNo + "'";
        message.list = message.db.getRecord(sql);
        if (message.list.size() > 0) {
            message.map = (Map) message.list.get(0);
            String erStatus = message.map.get("erstatus").toString();
            String serStatus = message.map.get("serstatus").toString();
            if ((erStatus.equalsIgnoreCase("A") && serStatus.equalsIgnoreCase("A"))) {
                return ResponseEntity.ok(message.respondWithError("Can not update after approved."));
            }
        }
        obj.setEnterBy(new OrganizationUser(td.getUserId()).getLoginId());
        obj.setEnterDate(new Date());
        obj.setErStatus("P");
        obj.setSerDate(null);
        obj.setErDate(null);
        obj.setSerStatus("P");
        row = da.save(obj);
        message.setHistory(applicationNo, td.getUserType(), "10", "S", td.getUserName());
        msg = da.getMsg();
        if (row == 1) {
            ApplicationLog.save("api/Processing/Phase1StructuralDesignerManjuriPatra", "POST", td.getUserName(), "save or update form");
            message.db.save(message.getEnterByStatus(userType, 10, applicationNo));
            message.map = new HashMap();//("39");
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
        return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "72");
    }
}/*  
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }

        if (!message.checkApproveStatus(td.getUserType(), "10", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        String status = obj.getStatus();
        sql = "SELECT coalesce(er_status,'P') \"erStatus\" , coalesce(ser_status,'P') \"serStatus\", coalesce(chief_status,'P') \"chiefStatus\", enter_by \"enterBy\" FROM anusuchi_gha WHERE application_no=" + applicationNo;
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
                sql = "UPDATE anusuchi_gha SET ser_date=NOW(),ser_name='" + td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "10", status, td.getUserName());

                sql = "UPDATE building_permit_application SET sub_engineer_status='" + status + "',sub_engineer_action='10',application_status='" + status + "',application_action='10',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);

                message.map.put("message", "Application Approved");
                ApplicationLog.save("api/Processing/Phase1/AnusuchiGha", "PUT", td.getUserName(), "approve form");
            } else {
                sql = "UPDATE anusuchi_gha SET enter_date=null,ser_date=NOW(),ser_name='" + td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "10", status, td.getUserName());

                sql = "UPDATE building_permit_application SET  sub_engineer_status='" + status + "',sub_engineer_action='10',application_status='" + status + "',application_action='10',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);

                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), "B", 10, obj.getComment(), obj.getRemark(),enterBy);
                ApplicationLog.save("api/Processing/Phase1/AnusuchiGha", "PUT", td.getUserName(), "reject form");
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
                sql = "UPDATE anusuchi_gha SET er_date=NOW(),er_name='" + td.getUserName() + "',er_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "10", status, td.getUserName());

                sql = "UPDATE building_permit_application SET engineer_status='" + status + "',engineer_action='10',application_status='" + status + "',application_action='10',application_action_by='A' WHERE id=" + applicationNo;
                message.db.save(sql);

                message.map.put("message", "Application Approved");
                ApplicationLog.save("api/Processing/Phase1/AnusuchiGha", "PUT", td.getUserName(), "approve form");
            } else {
                sql = "UPDATE anusuchi_gha SET enter_date=null, er_date=NOW(),er_name='" + td.getUserName() + "',er_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "10", status, td.getUserName());

                sql = "UPDATE building_permit_application SET engineer_status='" + status + "',engineer_action='10',application_status='" + status + "',application_action='10',application_action_by='A' WHERE id=" + applicationNo;
                message.db.save(sql);

                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 10, obj.getComment(), obj.getRemark(),enterBy);
                ApplicationLog.save("api/Processing/Phase1/AnusuchiGha", "PUT", td.getUserName(), "reject form");
            }
        } else if (td.getUserType().equalsIgnoreCase("C")) {
            if (chiefStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Chief!!");
            }

            if (status.equalsIgnoreCase("A")) {
                sql = "UPDATE anusuchi_ka SET chief_date=NOW(),chief_name='" + td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "10", status, td.getUserName());

                sql = "UPDATE building_permit_application SET chief_status='" + status + "',chief_action='10',application_status='" + status + "',application_action='10',application_action_by='C' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Approved");
                ApplicationLog.save("api/Processing/Phase1/AnusuchiGha", "PUT", td.getUserName(), "approve form");
            } else {
                sql = "UPDATE anusuchi_ka SET enter_date=null, chief_date=NOW(),chief_name='" + td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "10", status, td.getUserName());

                sql = "UPDATE building_permit_application SET chief_status='" + status + "',chief_action='10',application_status='" + status + "',application_action='10',application_action_by='C' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 10, obj.getComment(), obj.getRemark(),enterBy);
                ApplicationLog.save("api/Processing/Phase1/AnusuchiGha", "PUT", td.getUserName(), "reject form");
            }
        }
        //  message.setApplicationActionStatus(applicationNo, td.getUserType(), 10, status);
        return message.map;
    }
}
*/

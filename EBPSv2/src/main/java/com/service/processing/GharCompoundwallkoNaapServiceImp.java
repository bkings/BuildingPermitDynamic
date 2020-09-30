package com.service.processing;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.EBPSDao;
import com.model.application.ApplicationApproved;
import com.model.processing.GharCompoundwallkoNaap;

@Service
public class GharCompoundwallkoNaapServiceImp implements GharCompoundwallkoNaapService {

    @Autowired
    EBPSDao da;
    model.Message message = new model.Message();
    String msg = "", sql;

    @Override
    public Object getAll(long applicationNo) {
        message.list = da.getAll("from GharCompoundwallkoNaap where applicationNo=" + applicationNo);
        if (message.list.isEmpty()) {
            return message.respondWithError("Record not found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));
        message.map.put("comment", message.getComment("" + applicationNo, "45"));
        message.map.put("history", message.getHistory(applicationNo, "45"));
        return message.map;
    }

    @Override
    public Object save(GharCompoundwallkoNaap obj, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        String userType = td.getUserType();
        long applicationNo = obj.getApplicationNo();
        if (!message.checkSaveStatus(userType, "45", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }

        int row = 0;
        obj.setEnterBy(td.getUserId());
        obj.setEnterDate(new Date());
        try {
            row = da.save(obj);

            if (row == 1) {
                message.db.save(message.getEnterByStatus(userType, 45, applicationNo));
                message.setHistory(applicationNo, td.getUserType(), "45", "S", td.getUserName());
                return message.respondWithMessage("Success");
            }
            return message.respondWithError(da.getMsg());
        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
        return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "45");
    }
}/*     JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }

        if (!message.checkApproveStatus(td.getUserType(), "45", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        String status = obj.getStatus();
        sql = "SELECT coalesce(er_status,'P') \"erStatus\" ,coalesce(amini_status,'P') \"aminiStatus\",  coalesce(ser_status,'P') \"serStatus\", coalesce(chief_status,'P') \"chiefStatus\", enter_by \"enterBy\"  FROM ghar_compoundwallko_naap WHERE application_no=" + applicationNo;
        message.list = message.db.getRecord(sql);
        if (message.list.isEmpty()) {
            return message.respondWithError("Invalid Application No.");
        }
        message.map = (Map) message.list.get(0);
        String erStatus = message.map.get("erStatus").toString();
        String serStatus = message.map.get("serStatus").toString();
        String chiefStatus = message.map.get("chiefStatus").toString();
        String aminiStatus = message.map.get("aminiStatus").toString();
        String enterBy= message.map.get("enterBy").toString();

        if (td.getUserType().equalsIgnoreCase("AD")) {
            if (aminiStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Action already performed!!");
            }

            if (chiefStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Action already performed!!");
            }

            if (erStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Engineer!!");
            }

            if (serStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Action already performed!!");
            }
            if (status.equalsIgnoreCase("A")) {
                sql = "UPDATE ghar_compoundwallko_naap SET amini_date=NOW(),amini_name='" + td.getUserName() + "',amini_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "45", status, td.getUserName());

                sql = "UPDATE building_permit_application SET amin_status='" + status + "',amin_action='45',application_status='" + status + "',application_action='45',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE ghar_compoundwallko_naap SET enter_date=null, amini_date=NOW(),amini_name='" + td.getUserName() + "',amini_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "45", status, td.getUserName());

                sql = "UPDATE building_permit_application SET amin_status='" + status + "',amin_action='45',application_status='" + status + "',application_action='45',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), "B", 45, obj.getComment(), obj.getRemark(),enterBy);
            }

        } else if (td.getUserType().equalsIgnoreCase("B")) {
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
                sql = "UPDATE ghar_compoundwallko_naap SET ser_date=NOW(),ser_name='" + td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "45", status, td.getUserName());

                sql = "UPDATE building_permit_application SET sub_engineer_status='" + status + "',sub_engineer_action='45',application_status='" + status + "',application_action='45',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE ghar_compoundwallko_naap SET enter_date=null, ser_date=NOW(),ser_name='" + td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "45", status, td.getUserName());

                sql = "UPDATE building_permit_application SET sub_engineer_status='" + status + "',sub_engineer_action='45',application_status='" + status + "',application_action='45',application_action_by='B' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), "B", 45, obj.getComment(), obj.getRemark(),enterBy);
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
                sql = "UPDATE ghar_compoundwallko_naap SET er_date=NOW(),er_name='" + td.getUserName() + "',er_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "45", status, td.getUserName());

                sql = "UPDATE building_permit_application SET engineer_status='" + status + "',engineer_action='45',application_status='" + status + "',application_action='45',application_action_by='A' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE ghar_compoundwallko_naap SET enter_date=null, er_date=NOW(),er_name='" + td.getUserName() + "',er_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "45", status, td.getUserName());

                sql = "UPDATE building_permit_application SET engineer_status='" + status + "',engineer_action='45',application_status='" + status + "',application_action='45',application_action_by='A' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();
                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 45, obj.getComment(), obj.getRemark(),enterBy);
            }
        } else if (td.getUserType().equalsIgnoreCase("C")) {
            if (chiefStatus.equalsIgnoreCase("A")) {
                return message.respondWithError("Application has been already approved by Chief!!");
            }
            if (status.equalsIgnoreCase("A")) {
                sql = "UPDATE ghar_compoundwallko_naap SET chief_date=NOW(),chief_name='" + td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "45", status, td.getUserName());

                sql = "UPDATE building_permit_application SET chief_status='" + status + "',chief_action='45',application_status='" + status + "',application_action='45',application_action_by='C' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();

                message.map.put("message", "Application Approved");
            } else {
                sql = "UPDATE ghar_compoundwallko_naap SET enter_date=null,chief_date=NOW(),chief_name='" + td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
                message.setHistory(applicationNo, td.getUserType(), "45", status, td.getUserName());

                sql = "UPDATE building_permit_application SET chief_status='" + status + "',chief_action='45',application_status='" + status + "',application_action='45',application_action_by='C' WHERE id=" + applicationNo;
                message.db.save(sql);
                message.map = new HashMap();

                message.map.put("message", "Application Rejected.");
                message.comment(applicationNo, td.getUserName(), td.getUserType(), 45, obj.getComment(), obj.getRemark(),enterBy);
            }

        }
        //  message.setApplicationActionStatus(applicationNo, td.getUserType(), 45, status);
        return message.map;
    }

}*/

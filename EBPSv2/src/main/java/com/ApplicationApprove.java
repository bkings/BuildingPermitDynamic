/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.config.JWTToken;
import com.model.application.ApplicationApproved;

import model.ApplicationReject;
import model.CheckApproveStatus;
import model.DB;
import model.DateConvert;
import model.Message;

public class ApplicationApprove {

    public ResponseEntity doApprove(Long applicationNo, ApplicationApproved obj, String Authorization, String formId) {
        Message message = new Message();
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return ResponseEntity.ok(message.respondWithError("Authorization Error"));
        }
        DB db = new DB();
        String userType = td.getUserType();
        String userName = td.getUserName();
        CheckApproveStatus checkApproveStatus = new CheckApproveStatus();

        if (!checkApproveStatus.get(userType, Integer.parseInt(formId), applicationNo)) {
            return ResponseEntity.ok(message.respondWithError(checkApproveStatus.getMsg()));
        }

        String tableName = checkApproveStatus.getTableName();
        System.out.println("table name " + tableName);
        /*String userTableColumn = userTableColumn(userType);
        String buildingPermitApplicationColumn = buildingPermitApplicationColumn(userType);
        if (userTableColumn.length() < 2) {
            return ResponseEntity.ok(message.respondWithError("User type not define!!"));
        } else if (userTableColumn.length() < 2) {
            return ResponseEntity.ok(message.respondWithError("User type not define!!"));
        }*/
        int row;
        String sql, status = obj.getStatus();
        String[] multiSql=new String[2];
        if (status.equalsIgnoreCase("A")) {
//            sql = "UPDATE " + tableName + " SET " + userTableColumn + "date=NOW()," + userTableColumn + "name='" + td.getUserName() + "'," + userTableColumn + "status='" + status + "' WHERE COALESCE(" + userTableColumn + "status,'P')!='A' and application_no=" + applicationNo;
        	sql = "UPDATE status SET date=NOW(),name='"+td.getUserName()+"',status='"+status+"' WHERE application_no="+applicationNo+" AND user_type = '"+userType+"' AND form_id='"+formId+"' AND COALESCE(status,'P')!='A'";
            row = db.save(sql);
            System.out.println("row " + row);
            if (row == 0) {
//                sql = " SELECT COALESCE(" + userTableColumn + "status,'P') status FROM " + tableName + " WHERE application_no=" + applicationNo;
            	sql = "SELECT COALESCE(status,'P') status FROM status WHERE application_no="+applicationNo+" AND user_type='"+userType+"' AND form_id='"+formId+"'";
                message.list = db.getRecord(sql);
                if (message.list.isEmpty() || message.list.size() == 0) {
                    return ResponseEntity.ok(message.respondWithError("This form could not be saved!"));
                }
                return ResponseEntity.ok(message.respondWithError("This form already approve by this user!!"));
            }

            message.setHistory(applicationNo, userType, formId, status, td.getUserName());
//            sql = "UPDATE building_permit_application SET " + buildingPermitApplicationColumn + "status='" + status + "'," + buildingPermitApplicationColumn + "action='" + formId + "',application_status='" + status + "',application_action='" + formId + "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
            multiSql[0] = "UPDATE application_status SET user_full_status='"+status+"',user_action='"+formId+"' WHERE application_no="+applicationNo+" AND user_type='"+userType+"'";
            multiSql[1] = "UPDATE building_permit_application SET application_status='"+status+"',application_action='"+formId+"',application_action_by='"+userType+"' WHERE application_no="+applicationNo+"";
            db.saveMultiple(multiSql);
            sql = "INSERT INTO applications_comments (APPLICATION_NO,USER_TYPE,COMMENT_BY,COMMENT,COMMENT_DATE,PAGE,REMARK) VALUES('" + applicationNo + "','" + userType + "','" + userName + "',?,'" + DateConvert.now() + "','" + formId + "',?)";
            db.save(sql, obj.getComment(), "APPROVED_COMMENT");
            
            return ResponseEntity.ok(message.respondWithMessage("Application Approved"));

        } else {
//            sql = "UPDATE " + tableName + " SET enter_date=null, " + userTableColumn + "date=NOW()," + userTableColumn + "name='" + td.getUserName() + "'," + userTableColumn + "status='" + status + "' WHERE COALESCE(" + userTableColumn + "status,'P')!='A' and application_no=" + applicationNo;
        	sql = "UPDATE status SET date=NOW(),name='"+td.getUserName()+"',status='"+status+"' WHERE application_no="+applicationNo+" AND user_type = '"+userType+"' AND form_id='"+formId+"' AND COALESCE(status,'P')!='A'";
            row = db.save(sql);
            if (row == 0) {
//                sql = " SELECT COALESCE(" + userTableColumn + "status,'P') status FROM " + tableName + " WHERE application_no=" + applicationNo;
            	sql = "SELECT COALESCE(status,'P') status FROM status WHERE application_no="+applicationNo+" AND user_type='"+userType+"' AND form_id='"+formId+"'";
                message.list = db.getRecord(sql);
                if (message.list.isEmpty()) {
                    return ResponseEntity.ok(message.respondWithError("This form could not be saved!"));
                }
                return ResponseEntity.ok(message.respondWithError("This form already approve by this user!!"));

            }
//            sql = "UPDATE building_permit_application SET " + buildingPermitApplicationColumn + "status='" + status + "'," + buildingPermitApplicationColumn + "action='" + formId + "',application_status='" + status + "',application_action='" + formId + "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
            multiSql[0] = "UPDATE application_status SET user_full_status='"+status+"',user_action='"+formId+"' WHERE application_no="+applicationNo+" AND user_type='"+userType+"'";
            multiSql[1] = "UPDATE building_permit_application SET application_status='"+status+"',application_action='"+formId+"',application_action_by='"+userType+"' WHERE application_no="+applicationNo+"";
            db.saveMultiple(multiSql);
            message.setHistory(applicationNo, userType, formId, status, td.getUserName());
            sql = "SELECT U.designation AS designation FROM user_type_master U,form_name_master F WHERE U.id=F.enter_by AND F.id='" + formId + "'";
            message.map = (Map) db.getRecord(sql).get(0);
            new ApplicationReject().doAction(applicationNo, userName, userType, formId, obj.getComment(), obj.getRemark(), td.getUserTypeName(userType), message.map.get("designation").toString());

            return ResponseEntity.ok(message.respondWithMessage("Application Rejected."));
        }

    }

    /*private String userTableColumn(String type) {
        if (type.equalsIgnoreCase("A")) {
            return "er_";
        } else if (type.equalsIgnoreCase("B")) {
            return "ser_";
        } else if (type.equalsIgnoreCase("C")) {
            return "chief_";
        } else if (type.equalsIgnoreCase("AD")) {
            return "amini_";
        } else if (type.equalsIgnoreCase("E")) {
            return "e_";
        } else if (type.equalsIgnoreCase("F")) {
            return "f_";
        } else if (type.equalsIgnoreCase("G")) {
            return "g_";
        }
        return "";
    }

    private String buildingPermitApplicationColumn(String type) {

        if (type.equalsIgnoreCase("A")) {
            return "engineer_";
        } else if (type.equalsIgnoreCase("B")) {
            return "sub_engineer_";
        } else if (type.equalsIgnoreCase("C")) {
            return "chief_";
        } else if (type.equalsIgnoreCase("AD")) {
            return "amin_";
        } else if (type.equalsIgnoreCase("R")) {
            return "rajasow_";
        } else if (type.equalsIgnoreCase("E")) {
            return "poste_";
        } else if (type.equalsIgnoreCase("F")) {
            return "postf_";
        } else if (type.equalsIgnoreCase("G")) {
            return "postg_";
        }
        return "";
    }*/
}

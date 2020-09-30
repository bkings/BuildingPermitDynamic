/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Map;

public class CheckSaveStatus {

    String msg;

    public boolean get(String userType, String formId, long applicationNo) {
        List list;
        DB db = new DB();
        Map map;
        String enterBy = "", sql;
        sql = "SELECT coalesce(enter_by,'') \"enterBy\" FROM form_name_master WHERE id=" + formId;
        list = db.getRecord(sql);
        if (list.isEmpty()) {
            return false;
        }
        map = (Map) list.get(0);
        enterBy = map.get("enterBy").toString();
        if (!enterBy.equalsIgnoreCase(userType)) {
            setMsg("You dont have to save this form!!");
            return false;
        }
        if (applicationNo == 1) {
            return true;
        }
        sql = "SELECT construction_type \"constructionType\" FROM building_permit_application WHERE id='" + applicationNo + "'";
        list = db.getRecord(sql);
        if (list.isEmpty()) {
            setMsg("Invalid Application no!");
            return false;
        }
        map = (Map) list.get(0);
        String constructionType = map.get("constructionType").toString();
        constructionType = new Message().getGroupType(constructionType);
        sql = "SELECT coalesce(CONCAT(previous_form,''),'') \"previousForm\" FROM form_group WHERE form_id=" + formId + " AND user_type='" + userType + "' AND group_type='" + constructionType + "'";
        list = db.getRecord(sql);
        if (list.isEmpty()) {
            return true;
        }
        map = (Map) list.get(0);
        String previousForm = map.get("previousForm").toString();
        if (previousForm.length() < 2) {
            return true;
        }
//        sql = "SELECT \"table_name\" \"previousTable\",name \"previousFormName\" FROM form_name_master WHERE id=" + previousForm;
        sql = "SELECT (SELECT table_name FROM ebps_tables WHERE id=table_id) \"previousTable\",name \"previousFormName\" from form_name_master WHERE id=" + previousForm;
        list = db.getRecord(sql);
        if (list.isEmpty()) {
            return true;
        }
        map = (Map) list.get(0);
        String previousTable = map.get("previousTable").toString();
        String previousFormName = map.get("previousFormName").toString();
        if (previousTable.length() < 3) {
            return true;
        }
        sql = "SELECT COALESCE(CONCAT(enter_date),'') \"enterDate\" FROM " + previousTable + " WHERE application_no='" + applicationNo + "'";
        list = db.getRecord(sql);
        if (list.size() > 0) {
            map = (Map) list.get(0);
            String enterDate = map.get("enterDate").toString();
            if (enterDate.length() >= 10) {
                return true;
            } else {
                setMsg("Please fill up previous form!! (" + previousFormName + " )");
                return false;
            }
        } else {
            sql = "SELECT * FROM has_revised WHERE user_type='" + userType + "' AND ignored_form=" + previousForm + " AND status='Y'";
            list = db.getRecord(sql);
            if (list.size() > 0) {
                return true;
            }
        }
        sql = "SELECT has_revised \"hasRevised\" FROM has_revised WHERE user_type='" + userType + "' AND ignored_form=" + formId + " AND status='Y'";
        list = db.getRecord(sql);
        if (list.isEmpty()) {
            setMsg("Please fill up previous form!!");
            return false;
        }
        map = (Map) list.get(0);
        String hasRevised = map.get("hasRevised").toString();
//        sql = "SELECT \"table_name\" AS \"tableName\" FROM form_name_master WHERE id=" + hasRevised;
        sql = "SELECT (SELECT table_name FROM ebps_tables WHERE id=table_id) as \"tableName\" FROM form_name_master WHERE id=" + hasRevised;
        list = db.getRecord(sql);
        if (list.isEmpty()) {
            setMsg("Please fill up previous form!!");
            return false;
        }
        map = (Map) list.get(0);
        String tableName = map.get("tableName").toString();
        sql = "SELECT COALESCE(has_revised,'') \"hasRevised\" FROM " + tableName + " WHERE application_no='" + applicationNo + "'";
        list = db.getRecord(sql);
        if (list.isEmpty()) {
            setMsg("Please fill up previous form!!");
            return false;
        }
        map = (Map) list.get(0);
        hasRevised = map.get("hasRevised").toString();
        if (hasRevised.equalsIgnoreCase("Y")) {
            return true;
        } else {
            setMsg("Please fill up previous form!!");
            return false;
        }

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;

public class CheckApproveStatus {

    String msg;
    private String tableName;

    public boolean get(String userType, int formId, long id) {
        String previousTable = "", formName = "";
        tableName = "building_permit_application";
        previousTable = tableName;
        if (formId == 1) {
            tableName = "building_permit_application";
            return true;
        }
        String sql;
        String buildingClass = "";
        Message message = new Message();

        String approveBy = "";
        approveBy = "P.user_type='"+userType+"' AND P.approval_status";
        /*if (userType.equalsIgnoreCase("A")) {
            approveBy = " COALESCE(engr_approval,'N')";
        } else if (userType.equalsIgnoreCase("B")) {
            approveBy = " COALESCE(sub_engr_approval,'N')";
        } else if (userType.equalsIgnoreCase("C")) {
            approveBy = " COALESCE(chief_approval,'N')";
        } else if (userType.equalsIgnoreCase("D")) {
            approveBy = " COALESCE(designer_approval,'N')";
        } else if (userType.equalsIgnoreCase("R")) {
            approveBy = " COALESCE(rajasow_approval,'N')";
        } else if (userType.equalsIgnoreCase("AD")) {
            approveBy = " COALESCE(amin_approval,'N')";
        } else if (userType.equalsIgnoreCase("E")) {
            approveBy = " COALESCE(poste_approval,'N')";
        } else if (userType.equalsIgnoreCase("F")) {
            approveBy = " COALESCE(postf_approval,'N')";
        } else if (userType.equalsIgnoreCase("G")) {
            approveBy = " COALESCE(postg_approval,'N')";
        }*/
        
        /*String approveColumn;
        if (userType.equalsIgnoreCase("A")) {
            approveColumn = "COALESCE(er_status,'P') \"approveBy\"";
        } else if (userType.equalsIgnoreCase("B")) {
            approveColumn = "COALESCE(ser_status,'P') \"approveBy\"";
        } else if (userType.equalsIgnoreCase("C")) {
            approveColumn = "COALESCE(chief_status,'P') \"approveBy\"";
        } else if (userType.equalsIgnoreCase("R")) {
            approveColumn = "COALESCE(rw_status,'P') \"approveBy\"";
        } else if (userType.equalsIgnoreCase("AD")) {
            approveColumn = "COALESCE(amini_status,'P') \"approveBy\"";
        } else if (userType.equalsIgnoreCase("E")) {
            approveColumn = "COALESCE(e_status,'P') \"approveBy\"";
        } else if (userType.equalsIgnoreCase("F")) {
            approveColumn = "COALESCE(f_status,'P') \"approveBy\"";
        } else if (userType.equalsIgnoreCase("G")) {
            approveColumn = "COALESCE(g_status,'P') \"approveBy\"";
        } else {
            setMsg("Invalid User type");
            return false;
        }*/

        try {
            sql = "SELECT building_class \"buildingClass\" FROM anusuchi_ka WHERE application_no='" + id + "'";
            try {
                message.list = message.db.getRecord(sql);
                if (!message.list.isEmpty()) {
                    message.map = (Map) message.list.get(0);
                    buildingClass = message.map.get("buildingClass").toString();
                } else {
                	sql = "SELECT coalesce(user_status,'P') \"approveBy\" FROM application_status WHERE application_no=" +id + " AND user_type='"+userType+"'";
//                    sql = "SELECT " + approveColumn + " FROM building_permit_application WHERE ID='" + id + "'";
                    message.map = (Map) message.db.getRecord(sql).get(0);
                    approveBy = message.map.get("approveBy").toString();
                    if (approveBy.equalsIgnoreCase("A")) {
                        tableName = "building_permit_application";
                        return true;
                    } else {
                        setMsg("निवेदन मा फारममा काम भएको छैन ।");
                        return false;
                    }
                }
            } catch (Exception e) {

            	sql = "SELECT coalesce(user_status,'P') \"approveBy\" FROM application_status WHERE application_no=" +id + " AND user_type='"+userType+"'";
//                sql = "SELECT " + approveColumn + " FROM building_permit_application WHERE ID='" + id + "'";
                message.map = (Map) message.db.getRecord(sql).get(0);
                approveBy = message.map.get("approveBy").toString();
                if (approveBy.equalsIgnoreCase("A")) {
                    tableName = "building_permit_application";
                    return true;
                } else {
                    setMsg("निवेदन मा फारममा काम भएको छैन ।");
                    return false;
                }

            }
        } catch (Exception e) {
        }
        String hasRevised = message.hasRevisedForm(userType, id);
        sql = "SELECT CONSTRUCTION_TYPE AS \"constructionType\",name_transafer_id AS \"nameTransferId\" FROM building_permit_application WHERE ID='" + id + "'";
        message.map = (Map) message.db.getRecord(sql).get(0);
//        String groupType = message.getGroupType(message.map.get("constructionType").toString());
        Long nameTransferId = 0l;
        try {
        	nameTransferId = Long.parseLong(message.map.get("nameTransferId").toString());
		} catch (Exception e) {
			nameTransferId = 0l;
		}
        
        String groupType = message.getGroupType1(message.map.get("constructionType").toString(),nameTransferId);
        String classGroup = "-1";
        if (buildingClass.length() > 0) {
            classGroup = message.classGroup(groupType, buildingClass);
        }

        int cFormId;
        int j = 0;
        try {

            sql = "SELECT (SELECT table_name FROM ebps_tables WHERE id=table_id) AS \"previousTable\",G.form_id AS \"cFormId\",group_position,F.name \"formName\" FROM form_group G,form_name_master F,form_permissions P WHERE G.form_id=F.id AND F.id=P.form_id AND G.group_type='" + groupType + "' AND G.user_type='" + userType + "' AND " + approveBy + "='Y' AND F.ID NOT IN(" + hasRevised + "," + classGroup + ") ORDER BY group_position";
            message.list = message.db.getRecord(sql);
            if (message.list.isEmpty()) {
                return true;
            }

            for (j = 0; j < message.list.size(); j++) {
                message.map = (Map) message.list.get(j);
                cFormId = Integer.parseInt(message.map.get("cFormId").toString());
                tableName = message.map.get("previousTable").toString();
                if (cFormId == formId) {
                    break;
                }
                previousTable = tableName;
                formName = message.map.get("formName").toString();
            }
            if (j == 0) {
                sql = "SELECT (SELECT table_name FROM ebps_tables WHERE id=table_id) \"previousTable\" FROM form_name_master WHERE id=" + formId;
                message.map = (Map) message.db.getRecord(sql).get(0);
                tableName = message.map.get("previousTable").toString();
                return true;
            }

//            sql = "SELECT " + approveColumn + " FROM " + previousTable + " WHERE application_no='" + id + "'";
            sql = "SELECT coalesce(status,'P') \"approveBy\" FROM status WHERE form_id='"+formId+"' AND user_type='"+userType+"' AND application_no=" + id;
            try {
                message.map = (Map) message.db.getRecord(sql).get(0);
                approveBy = message.map.get("approveBy").toString();
                if (approveBy.equalsIgnoreCase("A")) {
                    return true;
                } else {
                    setMsg(formName + " मा फारममा काम भएको छैन ।");
                    return false;
                }
            } catch (Exception e) {
                setMsg(formName + " मा फारममा काम भएको छैन ।");
                return false;
            }
        } catch (Exception e) {
            setMsg(e.getMessage());
            return false;
        }

    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}

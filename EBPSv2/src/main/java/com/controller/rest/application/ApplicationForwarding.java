package com.controller.rest.application;

import java.util.List;
import java.util.Map;

import model.DB;

public class ApplicationForwarding {

    public int getReceiverAction(String forwaTo, String groupType, String hasRevised, String classGroup, int oldPossion,Long applicationNo) {
        String approval, enterBy, position = "", forwardToNext = "'N' \"enterBy\",'N' \"forwardToNext\"";
        int newPossion = oldPossion;
        DB db = new DB();
        /*if (forwaTo.equalsIgnoreCase("A")) {
            forwardToNext = "coalesce(enter_by,'N') \"enterBy\",coalesce(engr_approval,'N') \"forwardToNext\"";
        } else if (forwaTo.equalsIgnoreCase("B")) {
            forwardToNext = "coalesce(enter_by,'N') \"enterBy\",coalesce(sub_engr_approval,'N') \"forwardToNext\"";
        } else if (forwaTo.equalsIgnoreCase("C")) {
            forwardToNext = "coalesce(enter_by,'N') \"enterBy\",coalesce(chief_approval,'N') \"forwardToNext\"";
        } else if (forwaTo.equalsIgnoreCase("D")) {
            forwardToNext = "coalesce(enter_by,'N') \"enterBy\",coalesce(enter_by,'N') \"forwardToNext\"";
        } else if (forwaTo.equalsIgnoreCase("AD")) {
            forwardToNext = "coalesce(enter_by,'N') \"enterBy\",coalesce(amin_approval,'N') \"forwardToNext\"";
        } else if (forwaTo.equalsIgnoreCase("R")) {
            forwardToNext = "coalesce(enter_by,'N') \"enterBy\",coalesce(rajasow_approval,'N') \"forwardToNext\"";
        } else if (forwaTo.equalsIgnoreCase("E")) {
        	forwardToNext = "coalesce(enter_by,'N') \"enterBy\",coalesce(poste_approval,'N') \"forwardToNext\"";
        } else if (forwaTo.equalsIgnoreCase("F")) {
        	forwardToNext = "coalesce(enter_by,'N') \"enterBy\",coalesce(postf_approval,'N') \"forwardToNext\"";
        } else if (forwaTo.equalsIgnoreCase("G")) {
        	forwardToNext = "coalesce(enter_by,'N') \"enterBy\",coalesce(postg_approval,'N') \"forwardToNext\"";
        }*/

//        String sql = "SELECT F.ID \"formId\",G.group_position \"position\"," + forwardToNext + " FROM form_name_master F,form_group G WHERE F.id=G.form_id AND table_name IS NOT NULL AND G.group_type='" + groupType + "' AND G.user_type='" + forwaTo + "'  AND F.ID NOT IN(" + hasRevised + "," + classGroup + ")  ORDER BY G.group_position";
        String sql = "SELECT F.ID \"formId\",G.group_position \"position\",coalesce(F.enter_by,'N') \"enterBy\",coalesce(P.approval_status,'N') \"forwardToNext\" FROM form_name_master F,form_group G,form_permissions P WHERE F.id=G.form_id AND F.id=P.form_id AND P.user_type='"+forwaTo+"' AND F.table_id is not null AND G.group_type='"+groupType+"' AND G.user_type='"+forwaTo+"' AND F.ID NOT IN ("+hasRevised+","+classGroup+") ORDER BY G.group_position";
        List list = db.getRecord(sql);
        if(list.isEmpty()) {
        	sql = "SELECT F.ID \"formId\",G.group_position \"position\",'N' as \"forwardToNext\",coalesce(F.enter_by,'N') \"enterBy\" FROM form_name_master F,form_group G WHERE F.id=G.form_id AND table_id IS NOT NULL AND G.group_type='" + groupType + "' AND G.user_type='" + forwaTo + "'  AND F.ID NOT IN(" + hasRevised + "," + classGroup + ")  ORDER BY G.group_position";
        	list = db.getRecord(sql);
        }
        Map map;
        int firstFormId = 0;
//        float firstFormId
        for (int i = 0; i < list.size(); i++) {
            try {
                map = (Map) list.get(i);
                int formId = Integer.parseInt(map.get("formId").toString());
                position = map.get("position").toString();
                approval = map.get("forwardToNext").toString();
                enterBy = map.get("enterBy").toString();
                System.out.println(enterBy + " " + approval + " " + firstFormId);
                if ((enterBy.equalsIgnoreCase(forwaTo) || approval.equalsIgnoreCase("Y")) && firstFormId == 0) {
                    firstFormId = formId;
                    System.out.println("firstFormId "+firstFormId);
                }
                if (formId == oldPossion) {
                    System.out.println("Old Possion " + position + " form ID " + formId);
                    //added
                    int jValue = i+1;
//                    String fTo = forwaTo.toLowerCase();
                    /**
                     * Because received value is "ad" and model variable name is "da"
                     */
                    /*if(fTo.equals("ad")) {
                    	fTo = "da";
                    }*/
//                    String rejectSql = "SELECT coalesce(rejected_"+fTo+",'N') \"isRejected\" FROM building_permit_application WHERE id=" + applicationNo;
                    String rejectSql = "SELECT coalesce(rejected_by_user,'N') \"isRejected\" FROM application_status WHERE application_no="+applicationNo+" AND user_type='"+forwaTo+"'";
                    List isRejectedList = db.getRecord(rejectSql);
                    Map rejectMap = (Map) isRejectedList.get(0);
                    String isRejected = rejectMap.get("isRejected").toString();
                    System.out.println("rejected or not " + isRejected);
                    if(isRejected.equalsIgnoreCase("Y")) {
                    	jValue = i;
                    }
                    //
                    
//                    for (int j = i + 1; j < list.size(); j++)
                    for (int j = jValue; j < list.size(); j++)
                        try {
                        map = (Map) list.get(j);
                        approval = map.get("forwardToNext").toString();
                        enterBy = map.get("enterBy").toString();
                        if (enterBy.equalsIgnoreCase(forwaTo) || approval.equalsIgnoreCase("Y")) {
                            newPossion = Integer.parseInt(map.get("formId").toString());
                            position = map.get("position").toString();

                            break;
                        }
                        continue;
                    } catch (Exception e) {
                    }
                    break;
                }
            } catch (Exception e) {
            }

        }

        if (newPossion == 0) {
            newPossion = firstFormId;
        }
        if (newPossion == 0) {
            newPossion = 1;
        }
        System.out.println("new Possion " + position + " form ID " + newPossion);
        return newPossion;
    }

}

package com.service.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.config.JWTToken;

import model.ApplicationForm;
import model.Message;

public class ApplicationFIlter {

    public Object get(Long applicationNo, String constructionType, String year, String nibedakName, String kittaNo, String wardNo, String Authorization, String applicationStatus) {
        Message message = new Message();
        
        List list = new ArrayList<>();
        Map m = new HashMap<>();
        
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        try {
            if (applicationStatus.equalsIgnoreCase("C")) {
                applicationStatus = " AND application_status ='C'";
            } else if (applicationStatus.equalsIgnoreCase("P")) {
                applicationStatus = " AND application_status !='C'";
            } else {
                applicationStatus = " ";
            }
        } catch (Exception e) {
            applicationStatus = "";
        }
        if (nibedakName.length() > 1) {
            nibedakName = " AND nibedak_name LIKE '%" + nibedakName + "%'";
        } else {
            nibedakName = "";
        }
        if (constructionType.length() > 0) {
            constructionType = " AND construction_type = '" + constructionType + "'";
        } else {
            constructionType = "";
        }
        if (year.length() > 1) {
            year = " AND applicant_year = '" + year + "'";
        } else {
            year = "";
        }
        if (kittaNo.length() > 0) {
            kittaNo = " AND kitta_no LIKE '%" + kittaNo + "%'";
        } else {
            kittaNo = "";
        }
        if (wardNo.length() > 0) {
            wardNo = " AND new_ward_no ='" + wardNo + "'";
        } else {
            wardNo = "";
        }
        String sql = "";
        String userType = td.getUserType();
                
        try {
        	sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='"+userType+"' ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce((select user_action from application_status WHERE application_no=id and user_type='"+userType+"'),'"+firstForm+"') \"yourForm\",coalesce((select user_full_status from application_status WHERE application_no=id and user_type='"+userType+"'),'P') \"yourStatus\",(select forward_to_user_remark FROM application_status WHERE application_no=id and user_type='"+userType+"') \"forwardTo\",construction_type \"constructionType\",name_transafer_id \"nameTransaferId\" FROM building_permit_application WHERE COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE("+applicationNo+",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType +" order by (select forward_to_user_date from application_status WHERE application_no=id AND user_type='"+userType+"')";
		} catch (Exception e) {
			System.out.println("ex " + e.getMessage());
			sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\" FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + "";
		}
        
        
        /*if (userType.equalsIgnoreCase("D")) {
            sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='D' ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(designer_action,'" + firstForm + "') \"yourForm\",designer_status \"yourStatus\",forward_to_d_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\"  FROM building_permit_application  where enter_by='" + td.getUserId() + "' AND COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + " order by forward_to_d_date desc";

        } else if (userType.equalsIgnoreCase("B")) {
            sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='B' ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }
            
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(sub_engineer_action ,'" + firstForm + "') \"yourForm\",coalesce(sub_engineer_status,'P') \"yourStatus\",forward_to_b_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\"  FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + " order by forward_to_b_date desc";
        } else if (userType.equalsIgnoreCase("A")) {
            sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='A' ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }

            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(engineer_action, '" + firstForm + "') \"yourForm\",coalesce(engineer_status,'P') \"yourStatus\",forward_to_a_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\"  FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + "  order by forward_to_a_date desc";
        }else if (userType.equalsIgnoreCase("E")) {
            sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='E' ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }

            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(poste_action, '" + firstForm + "') \"yourForm\",coalesce(poste_status,'P') \"yourStatus\",forward_to_e_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\"  FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + "  order by forward_to_e_date desc";
        }else if (userType.equalsIgnoreCase("F")) {
            sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='F' ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }

            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(postf_action, '" + firstForm + "') \"yourForm\",coalesce(postf_status,'P') \"yourStatus\",forward_to_f_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\"  FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + "  order by forward_to_f_date desc";
        }else if (userType.equalsIgnoreCase("G")) {
            sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='G' ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }

            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(postg_action,'" + firstForm + "') \"yourForm\",coalesce(postg_status,'P') \"yourStatus\",forward_to_g_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\"  FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + " order by forward_to_g_date desc";
        }else if (userType.equalsIgnoreCase("C")) {
            sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='C' ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }

            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(chief_action,'" + firstForm + "') \"yourForm\",coalesce(chief_status,'P') \"yourStatus\",forward_to_c_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\"  FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + " order by forward_to_c_date desc";
        }
        else if (userType.equalsIgnoreCase("AD")) {
            sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='AD' ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }

            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(amin_action,'" + firstForm + "') \"yourForm\",coalesce(amin_status,'P') \"yourStatus\",forward_to_ad_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\" FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + " order by forward_to_ad_date desc";
        } else if (userType.equalsIgnoreCase("R")) {
            sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='R' ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }


            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(rajasow_action,'" + firstForm + "') \"yourForm\",coalesce(rajasow_status,'P') \"yourStatus\",forward_to_r_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\" FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + " order by forward_to_r_date desc";
        }else {
        	sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id ORDER  BY G.group_position,FG.group_position";
            message.list = message.db.getRecord(sql);
            String firstForm = "0";
            if (!message.list.isEmpty()) {
                message.map = (Map) message.list.get(0);
                firstForm = message.map.get("id").toString();
            }
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\" FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day')  AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType + "";
        }*/
        
        /*else {
            return message.respondWithError("Please provide permission to user type " + userType + " Can not access this feature!!");
        }*/
        message.list = message.db.getRecord(sql);
        List l = new ArrayList();

        for (int i = 0; i < message.list.size(); i++) {
            message.map = (Map) message.list.get(i);
            try {
                message.map.put("applicationAction", ApplicationForm.getById(message.map.get("applicationAction").toString()));
            } catch (Exception e) {
            }
            try {
                message.map.put("yourAction", ApplicationForm.getById(message.map.get("yourForm").toString()));
            } catch (Exception e) {
                message.map.put("yourAction", ApplicationForm.getById("1"));
                message.map.put("yourForm", "1");
            }
            try {
                applicationStatus = message.map.get("applicationStatus").toString();
                if (applicationStatus.equalsIgnoreCase("R")) {
                    message.map.put("isRejected", "Y");
                } else {
                    message.map.put("isRejected", "N");
                }
                if (!applicationStatus.equalsIgnoreCase("C")) {
                    applicationStatus = "P";
                }
                message.map.put("applicationStatus", Message.applicationStatus(applicationStatus));

            } catch (Exception e) {
                message.map.put("applicationStatus", e.getMessage());
            }
            try {
                message.map.put("yourStatus", Message.status(message.map.get("yourStatus").toString()));
            } catch (Exception e) {
            }
            try {
//                message.map.put("applicationActionBy",
//                        new UserTypeName().userTypeName(message.map.get("applicationActionBy").toString()));
            	message.map.put("applicationActionBy",message.map.get("applicationActionBy").toString());
            } catch (Exception e) {
            }
            try {
                constructionType = message.map.get("constructionType").toString();
                if (constructionType.equalsIgnoreCase("1")) {
                    message.map.put("constructionType", "नयाँ");
                } else if (constructionType.equalsIgnoreCase("2")) {
                    message.map.put("constructionType", "पुरानो");
                } else if (constructionType.equalsIgnoreCase("3")) {
                    message.map.put("constructionType", "तला थप");
                }
            } catch (Exception e) {
                constructionType = "1";
                message.map.put("constructionType", "नयाँ");
            }
            
            /**
             * To receive designer change status and name after changing designer for an applicationNo
             */
            
            sql = "select C.application_no as \"applicationNo\" ,(select user_name from organization_user where login_id = P.enter_by) as \"newDesignerName\" from change_designer C,building_permit_application P where C.application_no=P.application_no and C.application_no = "+ Long.parseLong(message.map.get("applicantNo").toString()) +" order by C.change_date,C.id desc";
			list = message.db.getRecord(sql);
			if(!list.isEmpty()) {
				m = (Map) list.get(0);
				message.map.put("designerChangeStatus", "Y");
				message.map.put("newDesignerName", m.get("newDesignerName"));
			}else {
				message.map.put("designerChangeStatus", "N");
			}
            
            l.add(message.map);
        }

        return l;
    }

}

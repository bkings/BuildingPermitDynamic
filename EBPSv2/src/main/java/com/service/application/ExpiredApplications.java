/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.UserTypeName;
import com.config.JWTToken;

import model.ApplicationForm;
import model.DateConvert;
import model.Message;

/**
 *
 * @author MS
 */
public class ExpiredApplications {

    public Object set(Long id, String Authorization) {
        Message message = new Message();

        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        if (!td.getUserType().equalsIgnoreCase("D")) {
            return message.respondWithError("Yor have not access to use this feature!!");
        }
        String sql = "UPDATE building_permit_application SET MAYADTHAP_REQUEST_DATE='" + DateConvert.now() + "' WHERE id='" + id + "'";
        message.db.delete(sql);
        return message.respondWithMessage("Success");
    }

    public Object setRequest(Long id, String Authorization) {
        Message message = new Message();

        JWTToken td = new JWTToken(Authorization);
        String userType = td.getUserType();
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        } else if (!(userType.equalsIgnoreCase("C") || userType.equalsIgnoreCase("A") || userType.equalsIgnoreCase("ADM") || userType.equalsIgnoreCase("TADM"))) {
            return message.respondWithError("Yor have not access to use this feature!!");
        }
        String sql = "UPDATE building_permit_application SET MAYADTHAP_DATE=TO_DATE(enter_date,'YYYY-MM-DD'),MAYADTHAP_BY='" + td.getUserName() + "' WHERE id='" + id + "'";
        message.db.delete(sql);
        sql = "UPDATE building_permit_application SET enter_date='" + DateConvert.now() + "' WHERE id='" + id + "'";
        message.db.delete(sql);
        return message.respondWithMessage("Success");
    }

    public Object get(String Authorization) {
        Message message = new Message();

        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        String applicationStatus = "";

        String sql = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        String userType = td.getUserType();
        if (!userType.equalsIgnoreCase("D")) {
            return message.respondWithError("Yor have not access to use this feature!!");
        }
        sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='D' ORDER  BY G.group_position,FG.group_position";
        message.list = message.db.getRecord(sql);
        String firstForm = "0";
        if (!message.list.isEmpty()) {
            message.map = (Map) message.list.get(0);
            firstForm = message.map.get("id").toString();
        }
        sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(designer_action,'" + firstForm + "') \"yourForm\",designer_status \"yourStatus\",forward_to_d_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\",coalesce(rejected_a,'N') \"rejectedA\",coalesce(rejected_b,'N') \"rejectedB\",coalesce(rejected_c,'N') \"rejectedC\",coalesce(rejected_d,'N') \"rejectedD\",coalesce(rejected_da,'N') AS \"rejectedAD\",coalesce(rejected_r,'N') \"rejectedR\"  FROM building_permit_application  where enter_by='" + td.getUserId() + "' AND COALESCE(IS_DISCARD,'N')!='Y' AND NOW() > (TO_DATE(CONCAT(enter_date,''),'YYYY-MM-DD') + INTERVAL '735 day') AND application_status !='C' order by forward_to_d_date desc";

        message.list = message.db.getRecord(sql);
        List l = new ArrayList();
        Long yourForm = 1l;
        for (int i = 0; i < message.list.size(); i++) {
            message.map = (Map) message.list.get(i);
            try {
                message.map.put("applicationAction", ApplicationForm.getById(message.map.get("applicationAction").toString()));
            } catch (Exception e) {
            }
            try {
                yourForm = Long.parseLong("0" + message.map.get("yourForm").toString());
                if (yourForm < 1) {
                    yourForm = 1l;
                }
//                System.out.println("yourForm "+yourForm);
                message.map.put("yourForm", "" + yourForm + "");
                message.map.put("yourAction", ApplicationForm.getById(yourForm.toString()));
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
                message.map.put("applicationActionBy", new UserTypeName().userTypeName(message.map.get("applicationActionBy").toString()));
            } catch (Exception e) {
            }
            String constructionType = "";
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
            l.add(message.map);
        }

        return l;
    }

    public Object getRequest(String Authorization) {
        Message message = new Message();

        JWTToken td = new JWTToken(Authorization);
        String userType = td.getUserType();
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        } else if (!(userType.equalsIgnoreCase("C") || userType.equalsIgnoreCase("A") || userType.equalsIgnoreCase("ADM") || userType.equalsIgnoreCase("TADM"))) {
            return message.respondWithError("Yor have not access to use this feature!!");
        }
        String applicationStatus = "";

        String sql = "";
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        cal.add(Calendar.DATE, 1);

        sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='D' ORDER  BY G.group_position,FG.group_position";
        message.list = message.db.getRecord(sql);
        String firstForm = "0";
        if (!message.list.isEmpty()) {
            message.map = (Map) message.list.get(0);
            firstForm = message.map.get("id").toString();
        }
        sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(designer_action,'" + firstForm + "') \"yourForm\",designer_status \"yourStatus\",forward_to_d_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\",coalesce(rejected_a,'N') \"rejectedA\",coalesce(rejected_b,'N') \"rejectedB\",coalesce(rejected_c,'N') \"rejectedC\",coalesce(rejected_d,'N') \"rejectedD\",coalesce(rejected_da,'N') AS \"rejectedAD\",coalesce(rejected_r,'N') \"rejectedR\"  FROM building_permit_application  WHERE MAYADTHAP_REQUEST_DATE IS NOT NULL AND MAYADTHAP_DATE IS NULL AND APPLICATION_STATUS !='C' order by forward_to_d_date desc";

        message.list = message.db.getRecord(sql);
        List l = new ArrayList();
        Long yourForm = 1l;
        for (int i = 0; i < message.list.size(); i++) {
            message.map = (Map) message.list.get(i);
            try {
                message.map.put("applicationAction", ApplicationForm.getById(message.map.get("applicationAction").toString()));
            } catch (Exception e) {
            }
            try {
                yourForm = Long.parseLong("0" + message.map.get("yourForm").toString());
                if (yourForm < 1) {
                    yourForm = 1l;
                }
//                System.out.println("yourForm "+yourForm);
                message.map.put("yourForm", "" + yourForm + "");
                message.map.put("yourAction", ApplicationForm.getById(yourForm.toString()));
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
                message.map.put("applicationActionBy", new UserTypeName().userTypeName(message.map.get("applicationActionBy").toString()));
            } catch (Exception e) {
            }
            String constructionType = "";
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
            l.add(message.map);
        }

        return l;
    }

}

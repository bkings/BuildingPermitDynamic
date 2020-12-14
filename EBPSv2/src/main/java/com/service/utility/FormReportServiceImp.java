/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.application.DaoImpBuildingPermitApplication;

import model.DB;
import model.Message;

@Service
public class FormReportServiceImp implements FormReportService {

    @Override
    public Object getAll(String id, String designer, String wardNo, String constructionType, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }

        String NEW_WARD_NO = "";
        if (wardNo.length() > 0) {
            NEW_WARD_NO = " AND NEW_WARD_NO='" + wardNo + "'";
        }
        try {
            if (constructionType.length() > 0) {
                constructionType = " AND construction_type = '" + constructionType + "'";
            } else {
                constructionType = "";
            }
        } catch (Exception e) {
            constructionType = "";
        }
        model.Message message = new model.Message();
        String startDate, endDate, sql;
//        int yearCode = Integer.parseInt(id);
        DB db = new DB();
        sql = "SELECT start_date AS \"startDate\",end_date AS \"endDate\" FROM fiscal_year WHERE year_code='" + id + "'";
        message.list = db.getRecord(sql);
        if (message.list.isEmpty()) {
            return message.respondWithError("Invalid Year Code");
        }
        message.map = (Map) message.list.get(0);
        startDate = message.map.get("startDate").toString();
        endDate = message.map.get("endDate").toString();
        String designerbuilding_permit_application = "";
        try {
            if (td.getUserType().equalsIgnoreCase("D")) {
                designer = " AND B.enter_by='" + td.getUserId() + "'";
                designerbuilding_permit_application = " AND enter_by='" + td.getUserId() + "'";
            } else {
                designer = "";
                designerbuilding_permit_application = "";
            }
        } catch (Exception e) {
            designer = "";
            designerbuilding_permit_application = "";
        }
        Map map = new HashMap();
//        sql = "SELECT B.id \"applicantNo\",B.nibedak_name \"nibedakName\",B.applicant_ms \"applicantMs\",B.applicant_name \"applicantName\",B.applicant_address \"applicantAddress\",B.applicant_mobile_no \"applicantMobileNo\",B.application_action \"applicationAction\",B.application_action_by \"applicationActionBy\",B.application_status \"applicationStatus\",get_bsdate(B.applicant_date) \"applicantDate\",B.construction_type \"constructionType\" FROM building_permit_application B WHERE (amini_status='A' OR rw_status='A' OR ser_status='A' OR er_status='A' OR chief_status='A' ) AND applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designerbuilding_permit_application + NEW_WARD_NO + constructionType;
        sql = "SELECT B.id \"applicantNo\",B.nibedak_name \"nibedakName\",B.applicant_ms \"applicantMs\",B.applicant_name \"applicantName\",B.applicant_address \"applicantAddress\",B.applicant_mobile_no \"applicantMobileNo\",B.application_action \"applicationAction\",B.application_action_by \"applicationActionBy\",B.application_status \"applicationStatus\",get_bsdate(B.applicant_date) \"applicantDate\",B.construction_type \"constructionType\" FROM building_permit_application B WHERE (S.user_type IN ('AD','R','B','A','B','C') AND S.user_full_status='A') AND applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designerbuilding_permit_application + NEW_WARD_NO + constructionType;
        map.put("totalReg", db.getRecord(sql));
        sql = "SELECT B.id \"applicantNo\",B.nibedak_name \"nibedakName\",B.applicant_ms \"applicantMs\",B.applicant_name \"applicantName\",B.applicant_address \"applicantAddress\",B.applicant_mobile_no \"applicantMobileNo\",B.application_action \"applicationAction\",B.application_action_by \"applicationActionBy\",B.application_status \"applicationStatus\",get_bsdate(B.applicant_date) \"applicantDate\",B.construction_type \"constructionType\" FROM building_permit_application B WHERE application_status='C' AND applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designerbuilding_permit_application + NEW_WARD_NO;
        map.put("totalComplete", db.getRecord(sql));

//        sql = "SELECT B.id \"applicantNo\",B.nibedak_name \"nibedakName\",B.applicant_ms \"applicantMs\",B.applicant_name \"applicantName\",B.applicant_address \"applicantAddress\",B.applicant_mobile_no \"applicantMobileNo\",B.application_action \"applicationAction\",B.application_action_by \"applicationActionBy\",B.application_status \"applicationStatus\",get_bsdate(B.applicant_date) \"applicantDate\",B.construction_type \"constructionType\" FROM super_structure_construction V,building_permit_application B where V.application_no=B.application_no AND (V.amini_status='A' OR V.rw_status='A' OR V.ser_status='A' OR V.er_status='A' OR V.chief_status='A' ) AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        sql = "SELECT B.id \"applicantNo\",B.nibedak_name \"nibedakName\",B.applicant_ms \"applicantMs\",B.applicant_name \"applicantName\",B.applicant_address \"applicantAddress\",B.applicant_mobile_no \"applicantMobileNo\",B.application_action \"applicationAction\",B.application_action_by \"applicationActionBy\",B.application_status \"applicationStatus\",get_bsdate(B.applicant_date) \"applicantDate\",B.construction_type \"constructionType\" FROM super_structure_construction V,building_permit_application B,status S where V.application_no=B.application_no AND S.application_no=B.application_no AND S.form_id=(SELECT id from form_name_master WHERE table_id=(SELECT id from ebps_tables WHERE table_name='super_structure_construction')) AND (S.user_type in ('AD','R','B','A','C')) AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        map.put("superStructure", db.getRecord(sql));

//        sql = "SELECT B.id \"applicantNo\",B.nibedak_name \"nibedakName\",B.applicant_ms \"applicantMs\",B.applicant_name \"applicantName\",B.applicant_address \"applicantAddress\",B.applicant_mobile_no \"applicantMobileNo\",B.application_action \"applicationAction\",B.application_action_by \"applicationActionBy\",B.application_status \"applicationStatus\",get_bsdate(B.applicant_date) \"applicantDate\",B.construction_type \"constructionType\" FROM allowance_paper V,building_permit_application B where V.application_no=B.application_no AND (V.amini_status='A' OR V.rw_status='A' OR V.ser_status='A' OR V.er_status='A' OR V.chief_status='A' ) AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        sql = "SELECT B.id \"applicantNo\",B.nibedak_name \"nibedakName\",B.applicant_ms \"applicantMs\",B.applicant_name \"applicantName\",B.applicant_address \"applicantAddress\",B.applicant_mobile_no \"applicantMobileNo\",B.application_action \"applicationAction\",B.application_action_by \"applicationActionBy\",B.application_status \"applicationStatus\",get_bsdate(B.applicant_date) \"applicantDate\",B.construction_type \"constructionType\" FROM allowance_paper V,building_permit_application B,status S where V.application_no=B.application_no AND S.application_no=B.application_no AND S.form_id=(SELECT id from form_name_master WHERE table_id=(SELECT id from ebps_tables WHERE table_name='allowance_paper')) AND (S.user_type in ('AD','R','B','A','C')) AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        map.put("allowancePaper", db.getRecord(sql));

//        sql = "SELECT json_data AS \"jsonData\" FROM rajaswa_entry E,rajaswa_voucher V,building_permit_application B WHERE V.application_no=B.id AND E.application_no=V.application_no AND (V.amini_status='A' OR V.rw_status='A' OR V.ser_status='A' OR V.er_status='A' OR V.chief_status='A' ) AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        sql = "SELECT coalesce(json_data,'') AS \"jsonData\" FROM rajaswa_entry E,rajaswa_voucher V,building_permit_application B JOIN (SELECT distinct(application_no) FROM status WHERE status='A' AND form_id=(SELECT id from form_name_master WHERE table_id=(SELECT id from ebps_tables WHERE table_name='rajaswa_voucher'))) Z ON B.id=Z.application_no WHERE V.application_no=B.id AND E.application_no=V.application_no AND B.applicant_date BETWEEN '"+startDate+"' AND '"+endDate+"' "+designer+ NEW_WARD_NO + constructionType; // + " group by Z.application_no";
        map.put("rajaswaVoucher", db.getRecord(sql));
//        sql = " SELECT json_data AS \"jsonData\" FROM pahilocharan_bill_vuktani V,building_permit_application B WHERE V.application_no=B.id AND (V.amini_status='A' OR V.rw_status='A' OR V.ser_status='A' OR V.er_status='A' OR V.chief_status='A' ) AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        sql = "SELECT coalesce(json_data,'') AS \"jsonData\" FROM  pahilocharan_bill_vuktani V,building_permit_application B JOIN (SELECT distinct(application_no) FROM status WHERE status='A' AND form_id=(SELECT id from form_name_master WHERE table_id=(SELECT id from ebps_tables WHERE table_name='pahilocharan_bill_vuktani'))) Z ON B.id=Z.application_no WHERE V.application_no=B.id AND B.applicant_date BETWEEN '"+startDate+"' AND '"+endDate+"' "+designer+ NEW_WARD_NO + constructionType; // + " group by Z.application_no";
        map.put("PahilocharanBillVuktani", db.getRecord(sql));
//        sql = "SELECT json_data AS \"jsonData\" FROM dosrocharan_bill_vuktani V,building_permit_application B WHERE V.application_no=B.id AND (V.amini_status='A' OR V.rw_status='A' OR V.ser_status='A' OR V.er_status='A' OR V.chief_status='A' ) AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        sql = "SELECT coalesce(json_data,'') AS \"jsonData\" FROM  dosrocharan_bill_vuktani V,building_permit_application B JOIN (SELECT distinct(application_no) FROM status WHERE status='A' AND form_id=(SELECT id from form_name_master WHERE table_id=(SELECT id from ebps_tables WHERE table_name='dosrocharan_bill_vuktani'))) Z ON B.id=Z.application_no WHERE V.application_no=B.id AND B.applicant_date BETWEEN '"+startDate+"' AND '"+endDate+"' "+designer+ NEW_WARD_NO + constructionType;
        map.put("DosrocharanBillVuktani", db.getRecord(sql));
//        sql = " SELECT json_data AS \"jsonData\" FROM namsari_bill_vuktani V,building_permit_application B WHERE V.application_no=B.id AND (V.amini_status='A' OR V.rw_status='A' OR V.ser_status='A' OR V.er_status='A' OR V.chief_status='A') AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        sql = "SELECT coalesce(json_data,'') AS \"jsonData\" FROM  namsari_bill_vuktani V,building_permit_application B JOIN (SELECT distinct(application_no) FROM status WHERE status='A' AND form_id=(SELECT id from form_name_master WHERE table_id=(SELECT id from ebps_tables WHERE table_name='namsari_bill_vuktani'))) Z ON B.id=Z.application_no WHERE V.application_no=B.id AND B.applicant_date BETWEEN '"+startDate+"' AND '"+endDate+"' "+designer+ NEW_WARD_NO + constructionType;
        map.put("NamsariBillVuktani", db.getRecord(sql));
//        sql = "SELECT json_data AS \"jsonData\" FROM sansodhan_bill_vuktani V,building_permit_application B WHERE V.application_no=B.id AND (V.amini_status='A' OR V.rw_status='A' OR V.ser_status='A' OR V.er_status='A' OR V.chief_status='A' ) AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        sql = "SELECT coalesce(json_data,'') AS \"jsonData\" FROM  sansodhan_bill_vuktani V,building_permit_application B JOIN (SELECT distinct(application_no) FROM status WHERE status='A' AND form_id=(SELECT id from form_name_master WHERE table_id=(SELECT id from ebps_tables WHERE table_name='sansodhan_bill_vuktani'))) Z ON B.id=Z.application_no WHERE V.application_no=B.id AND B.applicant_date BETWEEN '"+startDate+"' AND '"+endDate+"' "+designer+ NEW_WARD_NO + constructionType + " group by Z.application_no";
        map.put("SansodhanBillVuktani", db.getRecord(sql));

//        sql = "SELECT json_data AS \"jsonData\" FROM building_build_certificate V,building_permit_application B WHERE V.application_no=B.id AND (V.amini_status='A' OR V.rw_status='A' OR V.ser_status='A' OR V.er_status='A' OR V.chief_status='A' ) AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        sql = " SELECT json_data AS \"jsonData\" FROM building_build_certificate V,building_permit_application B,status S WHERE V.application_no=B.id AND S.application_no=V.application_no AND (S.form_id=(SELECT id from form_name_master WHERE table_id=(SELECT id from ebps_tables WHERE table_name='building_build_certificate')) AND (S.user_type in ('AD','R','B','A','C'))) AND B.applicant_date BETWEEN '" + startDate + "' AND '" + endDate + "' " + designer + NEW_WARD_NO + constructionType;
        map.put("buildingBuildCertificate", db.getRecord(sql));

        DaoImpBuildingPermitApplication da = new DaoImpBuildingPermitApplication();
        String newWardNo = "";
        if (wardNo.length() > 0) {
            newWardNo = " and newWardNo='" + newWardNo + "'";
        }
        sql = "from BuildingPermitApplication where applicantYear='" + id + "' and UPPER(COALESCE(isDiscard,'N'))='Y' " + newWardNo;
        List list = da.getAll(sql);
        map.put("discard", list);
        map.put("discardNo", list.size());
        return map;

    }

}

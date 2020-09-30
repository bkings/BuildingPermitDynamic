/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.controller.rest.application.ApplicationForwarding;
import com.dao.application.DaoBuildingPermitApplication;
import com.model.application.BuildingPermitApplication;
import com.model.application.BuildingPermitApplicationNameTransafer;

import model.DateConvert;
import model.Message;

@Service
public class BuildingPermitApplicationNameTransaferServiceImpl implements BuildingPermitApplicationNameTransaferService {

    @Autowired
    DaoBuildingPermitApplication da;

    @Override
    public Object index(Long applicationNo, Long year, String constructionType, String nibedakName, String kittaNo, String wardNo, String Authorization) {
        Message message = new Message();
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }

        String sql = "", userType = td.getUserType(),approval = "";
        approval = "WHERE coalesce(amin_status,'S')='A' OR coalesce(chief_status,'S')='A' OR coalesce(engineer_status,'S')='A' OR coalesce(poste_status,'S')='A' OR coalesce(postf_status,'S')='A' OR coalesce(postg_status,'S')='A' OR coalesce(rajasow_status,'S')='A' OR coalesce(sub_engineer_status,'S') ='A'";
        if (userType.equalsIgnoreCase("D")) {
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(designer_action,'1') \"yourForm\",designer_status \"yourStatus\",forward_to_d_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application where enter_by='" + td.getUserId() + "' AND (WHERE coalesce(amin_status,'S')='A' OR coalesce(chief_status,'S')='A' OR coalesce(engineer_status,'S')='A' OR coalesce(poste_status,'S')='A' OR coalesce(postf_status,'S')='A' OR coalesce(postg_status,'S')='A' OR coalesce(rajasow_status,'S')='A' OR coalesce(sub_engineer_status,'S') ='A') order by forward_to_d_date desc";
        } else if (userType.equalsIgnoreCase("B")) {
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(sub_engineer_action,'1') \"yourForm\",coalesce(sub_engineer_status,'P') \"yourStatus\",forward_to_b_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application "+ approval +" order by forward_to_b_date desc";
        } else if (userType.equalsIgnoreCase("A")) {
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(engineer_action,'1') \"yourForm\",coalesce(engineer_status,'P') \"yourStatus\",forward_to_a_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application "+approval+" order by forward_to_a_date desc";
        } else if (userType.equalsIgnoreCase("C")) {
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(chief_action,'1') \"yourForm\",coalesce(chief_status,'P') \"yourStatus\",forward_to_c_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application "+approval+" order by forward_to_c_date desc";
        } else if (userType.equalsIgnoreCase("R")) {
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(rajasow_action,'1') \"yourForm\",coalesce(rajasow_status,'P') \"yourStatus\",forward_to_r_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application "+approval+" order by forward_to_r_date desc";
        } else if (userType.equalsIgnoreCase("AD")) {
            sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(amin_action,'1') \"yourForm\",coalesce(amin_status,'P') \"yourStatus\",forward_to_ad_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application "+approval+" order by forward_to_ad_date desc";
        } else if (userType.equalsIgnoreCase("E")) {
        	sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(amin_action,'1') \"yourForm\",coalesce(poste_status,'P') \"yourStatus\",forward_to_e_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application "+approval+" order by forward_to_e_date desc";
        } else if (userType.equalsIgnoreCase("F")) {
        	sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(amin_action,'1') \"yourForm\",coalesce(postf_status,'P') \"yourStatus\",forward_to_f_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application "+approval+" order by forward_to_f_date desc";
        } else if (userType.equalsIgnoreCase("G")) {
        	sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(amin_action,'1') \"yourForm\",coalesce(postg_status,'P') \"yourStatus\",forward_to_g_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application "+approval+" order by forward_to_g_date desc";
        }
        message.list = message.db.getRecord(sql);
        List l = new ArrayList();
        List l1;
        Map map;
        for (int i = 0; i < message.list.size(); i++) {
            message.map = (Map) message.list.get(i);
            applicationNo = Long.parseLong(message.map.get("applicantNo").toString());
            sql = "SELECT COUNT(*)+1 as time FROM building_permit_application_name_transafer WHERE application_no='" + applicationNo + "'";
            l1 = message.db.getRecord(sql);
            if (l1.isEmpty()) {
                message.map.put("time", 1);
            } else {
                map = (Map) l1.get(0);
                message.map.put("time", map.get("time"));
            }
            l.add(message.map);
        }
        return l;
    }

    @Override
    public Object save(long applicationNo, int time, String Authorization) {
        Message message = new Message();
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }
        
        List<BuildingPermitApplication> list = da.getAll("from BuildingPermitApplication where id=" + applicationNo);
        if (list.isEmpty()) {
            return message.respondWithError("Invalid application No!!");
        }
        String sql;
        if (time > 1) {
            sql = "SELECT * FROM namsari_tippani_ades WHERE id='" + applicationNo + (time - 1) + "'";
            List l = da.getRecord(sql);
            if (l.isEmpty()) {
                return message.respondWithError("PNC");
                //Previous Name Transfer Yet to Complete!
            }
            sql = "SELECT * FROM certificate_note WHERE id='" + applicationNo + (time - 1) + "'";
            l = da.getRecord(sql);
            if (l.isEmpty()) {
                return message.respondWithError("PNC");
                //Previous Name Transfer Yet to Complete!
            }
            sql = "SELECT * FROM building_build_certificate WHERE id='" + applicationNo + (time - 1) + "'";
            l = da.getRecord(sql);
            if (l.isEmpty()) {
                return message.respondWithError("PNC");
                //Previous Name Transfer Yet to Complete!
            }
        }
        BuildingPermitApplication obj = list.get(0);
        BuildingPermitApplicationNameTransafer obj1 = new BuildingPermitApplicationNameTransafer();
        obj1.setId(Long.parseLong(applicationNo + "" + time));
        obj1.setApplicationNo(applicationNo);
        obj1.setTransaferTime(time);
        obj1.setApplicantDate(obj.getApplicantDate());
        obj1.setApplicantMs(obj.getApplicantMs());
        obj1.setApplicantName(obj.getApplicantName());
        obj1.setApplicantAddress(obj.getApplicantAddress());
        obj1.setApplicantMobileNo(obj.getApplicantMobileNo());
//        obj1.setCitizenshipNo(obj.getChiefActionNo());
        obj1.setNationalIdNo(obj.getNationalIdNo());
        obj1.setFathersSpouseName(obj.getFathersSpouseName());
        obj1.setOldMunicipal(obj.getOldMunicipal());
        obj1.setOldWardNo(obj.getOldWardNo());
        obj1.setNewMunicipal(obj.getNewMunicipal());
        obj1.setNewWardNo(obj.getNewWardNo());
        obj1.setKittaNo(obj.getKittaNo());
        obj1.setLandArea(obj.getLandArea());
        obj1.setLandAreaType(obj.getLandAreaType());
        obj1.setNearestLocation(obj.getNearestLocation());
        obj1.setBuildingJoinRoad(obj.getBuildingJoinRoad());
        obj1.setBuildingJoinRoadType(obj.getBuildingJoinRoadType());
        obj1.setBuildingJoinRoadTypeOther(obj.getBuildingJoinRoadTypeOther());
        obj1.setLandPassDate(obj.getLandPassDate());
        obj1.setLandRegNo(obj.getLandRegNo());
        obj1.setOwnershipName(obj.getOwnershipName());
        obj1.setCertificateArea(obj.getCertificateArea());
        obj1.setLandCertificateNo(obj.getLandCertificateNo());
        obj1.setLandCertificateIssueDate(obj.getLandCertificateIssueDate());
        obj1.setPurposeOfConstruction(obj.getPurposeOfConstruction());
        obj1.setPurposeOfConstructionOther(obj.getPurposeOfConstructionOther());
        obj1.setConstructionType(obj.getConstructionType());
        obj1.setConstructionTypeOther(obj.getConstructionTypeOther());
        obj1.setOldMapDate(obj.getOldMapDate());
        obj1.setMohada(obj.getMohada());
        obj1.setConstructionFinishing(obj.getConstructionFinishing());
        obj1.setConstructionFinishingOther(obj.getConstructionFinishingOther());
        obj1.setDhalNikasArrangement(obj.getDhalNikasArrangement());
        obj1.setDhalNikasArrangementOther(obj.getDhalNikasArrangementOther());
        obj1.setFoharArrangement(obj.getFoharArrangement());
        obj1.setFoharArrangementOther(obj.getFoharArrangementOther());
        obj1.setPipeline(obj.getPipeline());
        obj1.setPipelineDistance(obj.getPipelineDistance());
        obj1.setDoPipelineConnection(obj.getDoPipelineConnection());
        obj1.setIsHighTensionLine(obj.getIsHighTensionLine());
        obj1.setIsHighTensionLineDistance(obj.getIsHighTensionLineDistance());
        obj1.setSadak(obj.getSadak());
        obj1.setTol(obj.getTol());
        obj1.setDhalUnit(obj.getDhalUnit());
        obj1.setPipelineUnit(obj.getPipelineUnit());
        obj1.setHighTensionLineUnit(obj.getHighTensionLineUnit());
        obj1.setNibedakName(obj.getNibedakName());
        obj1.setNibedakSadak(obj.getNibedakSadak());
        obj1.setNibedakAdditional(obj.getNibedakAdditional());
        obj1.setNibedakTol(obj.getNibedakTol());
        obj1.setEnterBy(obj.getEnterBy());
        obj1.setEnterDate(obj.getEnterDate());
        /*obj1.setSerName(obj.getSerName());
        obj1.setSerDate(obj.getSerDate());
        obj1.setSerStatus(obj.getSerStatus());
        obj1.setErDate(obj.getErDate());
        obj1.setErName(obj.getErName());
        obj1.setErStatus(obj.getErStatus());
        obj1.setRwDate(obj.getRwDate());
        obj1.setRwName(obj.getRwName());
        obj1.setRwStatus(obj.getRwStatus());
        obj1.setAminiDate(obj.getAminiDate());
        obj1.setAminiName(obj.getAminiName());
        obj1.setAminStatus(obj.getAminStatus());*/
        obj1.setIsLowTensionLine(obj.getIsLowTensionLine());
        obj1.setOldApplicationNo(obj.getOldApplicationNo());
        obj1.setPhoto(obj.getPhoto());
        obj1.setTalaThapAssign(obj.getTalaThapAssign());
        obj1.setTalaThapAssignBy(obj.getTalaThapAssignBy());
        obj1.setTalaThapAssignDate(obj.getTalaThapAssignDate());
        obj1.setTalathapDocument(obj.getTalathapDocument());
        obj1.setTransaferEnterBy(td.getUserName());
        obj1.setTransaferEnterDate(new Date());
        obj1.setTransaferApproveDate(new Date());
        obj1.setTransaferApproveBy(td.getUserName());
        int row = da.save(obj1);
        String msg = da.getMsg().toLowerCase();
        if (row > 0) {
            ApplicationForwarding call = new ApplicationForwarding();
            String forwaTo = td.getUserType();
//            String remark = ",forward_to_b_remark='यो फाईल " + call.getUserTypeName(td.getUserType()) + " बाट पठाईएको छ ।',forward_to_a_remark='यो फाईल " + call.getUserTypeName(forwaTo) + " लाई पठाईएको छ ।',forward_to_r_remark='यो फाईल " + call.getUserTypeName(forwaTo) + " लाई पठाईएको छ ।',forward_to_d_remark='यो फाईल " + call.getUserTypeName(forwaTo) + " लाई पठाईएको छ ।',forward_to_c_remark='यो फाईल " + call.getUserTypeName(forwaTo) + " लाई पठाईएको छ ।',forward_to_ad_remark='यो फाईल " + call.getUserTypeName(forwaTo) + " लाई पठाईएको छ ।'";
//            String date = DateConvert.today();
//            sql = "UPDATE building_permit_application SET name_transafer_id='" + obj1.getId() + "',application_action='37',sub_engineer_action='37',forward_to_d_date='" + date + "',forward_to_b_date='" + date + "'" + remark + ",forward_to_d_name='" + td.getUserName() + "',forward_to_d='O',forward_to_b='I' WHERE ID='" + applicationNo + "'";
//            da.delete(sql);
            
            
            String remark = ",forward_to_b_remark='यो फाईल " + td.getUserType() + " बाट पठाईएको छ ।',forward_to_a_remark='यो फाईल " + "sub-eng" + " लाई पठाईएको छ ।',forward_to_r_remark='यो फाईल " + "sub-eng" + " लाई पठाईएको छ ।',forward_to_d_remark='यो फाईल " + "sub-eng" + " लाई पठाईएको छ ।',forward_to_c_remark='यो फाईल " + "sub-eng" + " लाई पठाईएको छ ।',forward_to_ad_remark='यो फाईल " + "sub-eng" + " लाई पठाईएको छ ।'";
            String date = DateConvert.today();
            sql = "UPDATE building_permit_application SET name_transafer_id='" + obj1.getId() + "',application_action='37',sub_engineer_action='37',forward_to_d_date='" + date + "',forward_to_b_date='" + date + "'" + remark + ",forward_to_d_name='" + td.getUserName() + "',forward_to_d='O',forward_to_b='I',NAAMSARI_STATUS = 'R' WHERE ID='" + applicationNo + "'";
            da.delete(sql);
            
            return message.respondWithMessage("Success");
        } else if (msg.contains("duplicate key")) {
            msg = "This record already exist";
        }
        return message.respondWithError(msg);

    }

    @Override
    public Object index(Long applocationNo, String Authorization) {
        return da.getAll("from BuildingPermitApplicationNameTransafer where applicationNo='" + applocationNo + "'");

    }

    @Override
    public Object history(Long applicationNo, Long year, String constructionType, String nibedakName, String kittaNo, String wardNo, String Authorization) {
    	List l = da.getAll("from BuildingPermitApplicationNameTransafer ");
    	Map m = new HashMap();
    	String sql = "SELECT * FROM certificate_note";
    	m.put("nameTransferHistory", l);
    	m.put("certificateNoteData", da.getRecord(sql));
        return m;
    }

}

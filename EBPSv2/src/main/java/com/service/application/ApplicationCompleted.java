package com.service.application;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.config.JWTToken;

import model.DateConvert;
import model.Message;

public class ApplicationCompleted {

    public Object get(String constructionType, Long year, Long applicationNo, String nibedakName, String Authorization) {
        Message message = new Message();
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        if (!(td.getUserType().equalsIgnoreCase("C") || td.getUserType().equalsIgnoreCase("A"))) {
            return message.respondWithError("Sorry, you don't have access!!");
        }
        try {
            if (constructionType.length() > 0) {
                constructionType = " and construction_type='" + constructionType + "'";
            } else {
                constructionType = "";
            }
        } catch (Exception e) {
            constructionType = "";
        }
        try {
            if (nibedakName.length() > 0) {
                nibedakName = " and nibedak_name='" + nibedakName + "'";
            } else {
                nibedakName = "";
            }
        } catch (Exception e) {
            nibedakName = "";
        }
        String sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",construction_type \"constructionType\"  FROM building_permit_application where application_status='C' AND coalesce(TALA_THAP_ASSIGN,'N')!='Y' AND applicant_year= coalesce(" + year + ",applicant_year) AND COALESCE(IS_DISCARD,'N')!='Y' AND id= coalesce(" + applicationNo + ",id)  " + constructionType + nibedakName + " ORDER BY ID";
        return message.db.getRecord(sql);
    }

    public Object issueDesigner(HttpServletRequest request, Long applicationNo, String designer, MultipartFile file, String Authorization) {
        Message message = new Message();
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        if (!(td.getUserType().equalsIgnoreCase("C") || td.getUserType().equalsIgnoreCase("A"))) {
            return message.respondWithError("Sorry, you don't have access!!");
        }
        String fileStorageLocation = message.filePath(request.getContextPath());
        String fiscalYear = String.valueOf(applicationNo).substring(0, 4);
        String filePath = "/" + fiscalYear + "/" + applicationNo + "/";
        String fileName = "";
        File f = new File(fileStorageLocation + filePath);
        try {
            if (!f.exists()) {
                f.mkdirs();
            }
        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
        fileName = filePath + "talathap.png";

        try {
            f = new File(fileStorageLocation + fileName);
            if (f.exists()) {
                f.delete();
            }
        } catch (Exception e) {
        }
        f = new File(fileStorageLocation + fileName);
        try {
            file.transferTo(f);
        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
        String sql = "UPDATE building_permit_application SET enter_by='" + designer + "',tala_thap_document='" + fileName + "',tala_thap_assign='Y',application_status='C',TALA_THAP_ASSIGN_BY='" + td.getUserId() + "',TALA_THAP_ASSIGN_DATE='" + DateConvert.now() + "' WHERE id='" + applicationNo + "'";
        int row = message.db.save(sql);
        if (row == 1) {
            return message.respondWithMessage("Success");
        }
        return message.respondWithError(message.db.getMsg());
    }

    public Object talathap(String Authorization, String status, Long year) {
        Message message = new Message();
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        String dd = "";
        if (td.getUserType().equalsIgnoreCase("D")) {
            dd = " enter_by='" + td.getUserId() + "' AND ";
        } else {
            dd = "";
        }
        if (status.length() > 0) {
            status = " AND tala_thap_assign='" + status + "' ";
        } else {
            status = " AND tala_thap_assign IN('Y','W')";
        }
        
        String sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",construction_type \"constructionType\", enter_by AS  designer ,tala_thap_document \"talathapDocument\",tala_thap_assign \"talathapAssign\",talathap_application_no \"talathapApplicationNo\",TALA_THAP_ASSIGN_DATE \"talaThapAssignDate\",TALA_THAP_ASSIGN_BY \"talaThapAssignBy\" FROM building_permit_application where " + dd + " application_status='C' AND COALESCE(IS_DISCARD,'N')!='Y' AND applicant_year=coalesce(" + year + ",applicant_year) " + status + " ORDER BY ID";
        return message.db.getRecord(sql);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.processing;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoFileStorageApplication;
import com.dao.processing.DaoImpFileStorageApplication;
import com.dao.processing.EBPSDao;
import com.model.application.ApplicationApproved;
import com.model.processing.NoticePaymentApplication;

import model.DateConvert;

@Service
public class NoticePaymentApplicationServiceImp implements NoticePaymentApplicationService {

    @Autowired
    EBPSDao da;
    model.Message message = new model.Message();
    String sql;

    @Override
    public Object getAll(Long applicationNo) {
        message.list = da.getAll("from NoticePaymentApplication where applicationNo=" + applicationNo);
        if (message.list.isEmpty()) {
            return message.respondWithError("Record not found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));
        message.map.put("comment", message.getComment("" + applicationNo, "14"));
        message.map.put("history", message.getHistory(applicationNo, "14"));
        return message.map;
    }

    @Override
    public Object save(NoticePaymentApplication obj, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }

        String userType = td.getUserType();
        long applicationNo = obj.getApplicationNo();
        if (!message.checkSaveStatus(userType, "14", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        int row = 0;
        obj.setEnterBy(td.getUserId());
        obj.setEnterDate(new Date());
        try {
            row = da.save(obj);
            if (row == 1) {
                message.db.save(message.getEnterByStatus(userType, 14, applicationNo));
                message.setHistory(applicationNo, td.getUserType(), "14", "A", td.getUserName());
                return message.respondWithMessage("Success");
            }
            return message.respondWithError(da.getMsg());
        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object do15DayNoticeDate(HttpServletRequest request, Long applicationNo, String date, MultipartFile[] file, MultipartFile file2, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        Date noticeDate = DateConvert.bsToAdDate(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(noticeDate);
        cal.add(Calendar.DATE, 16);
        Date noticeTillDate = cal.getTime();
        String noticeDateBs = DateConvert.adtobsDate(noticeTillDate);
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
            System.out.println(e.getMessage());
        }
        DaoFileStorageApplication da = new DaoImpFileStorageApplication();
        List multipalFileList = new ArrayList();
        try {
            for (int i = 0; i < file.length; i++) {
                try {
                    fileName = filePath + "_" + (i + 1) + file[i].getOriginalFilename().replace(" ", "");
                    fileName = fileName.replace("'", "");
                    fileName = fileName.replace(",", "");
                    f = new File(fileStorageLocation + fileName);
                    multipalFileList.add(fileName);
                    file[i].transferTo(f);

                } catch (Exception e) {
                }
            }
            if (file.length > 0) {
                sql = "UPDATE notice_payment_application SET file_url1='" + multipalFileList.toString() + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
            }
        } catch (Exception e) {
        }

        try {
            if (file2.getSize() > 100) {
                String fileName2 = filePath + file2.getOriginalFilename().replace(" ", "");
                f = new File(fileStorageLocation + fileName2);
                file2.transferTo(f);
                sql = "UPDATE notice_payment_application SET file_url2='" + fileName2 + "' WHERE application_no=" + applicationNo;
                message.db.save(sql);
            }
        } catch (Exception e) {
        }
        try {
            sql = "UPDATE notice_payment_application SET notice_date='" + date + "',notice_till_date='" + noticeTillDate + "',notice_enter_by='" + td.getUserName() + "',notice_enter_date='" + DateConvert.now() + "' WHERE application_no=" + applicationNo;
            message.db.save(sql);

            //sql = "SELECT construction_type \"constructionType\" from building_permit_application where id = "+applicationNo+" ";
            //select form_id from form_group where previous_form = '+formId+"" AND user_type='"+td.getUserType()+"' AND group_type='"+constructionType+"'';
            
            String userType = td.getUserType(), nextAction = "";
            if (userType.equalsIgnoreCase("A")) {
                nextAction = "application_action=14,engineer_action=14,";
            } else if (userType.equalsIgnoreCase("B")) {
                nextAction = "application_action=14,sub_engineer_action=14,";
            } else if (userType.equalsIgnoreCase("C")) {
                nextAction = "application_action=14,chief_action=14,";
            } else if (userType.equalsIgnoreCase("C")) {
                nextAction = "application_action=14,rajasow_action=14,";
            }

            sql = "UPDATE building_permit_application SET " + nextAction + "forward_to_a_remark='यो फाईल मिति  " + noticeDateBs + "  सम्म १५ दिने सुचनाको लागि रोकिएको छ / थियो ।',forward_to_b_remark='यो फाईल मिति  " + noticeDateBs + "  सम्म १५ दिने सुचनाको लागि रोकिएको छ / थियो ।',forward_to_c_remark='यो फाईल मिति  " + noticeDateBs + "  सम्म १५ दिने सुचनाको लागि रोकिएको छ / थियो ।',forward_to_d_remark='यो फाईल मिति  " + noticeDateBs + "  सम्म १५ दिने सुचनाको लागि रोकिएको छ / थियो ।',forward_to_ad_remark='यो फाईल मिति  " + noticeDateBs + "  सम्म १५ दिने सुचनाको लागि रोकिएको छ / थियो ।',forward_to_r_remark='यो फाईल मिति  " + noticeDateBs + "  सम्म १५ दिने सुचनाको लागि रोकिएको छ / थियो ।',notice15_till_date='" + noticeTillDate + "' WHERE id=" + applicationNo;
            message.db.save(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return message.respondWithMessage("Success");
    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
        return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "14");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.config.JWTToken;
import com.dao.utility.DaoChangeDesigner;
import com.model.utility.ChangeDesigner;

import model.DateConvert;
import model.Message;

@Service
public class ChangeDesignerServiceImpl implements ChangeDesignerService {

@Autowired
DaoChangeDesigner da;

@Override
public Object getRecord(String Authorization, Long applicationNo) {

    JWTToken td = new JWTToken(Authorization);
    if (!td.isValid()) {
        return new Message().respondWithError("Authorization Error");
    }
    Message message = new Message();
    String userType = td.getUserType();
    if (!(userType.equalsIgnoreCase("C") || userType.equalsIgnoreCase("A"))) {
        return message.respondWithError("You have no access this feature!!");
    }
    return da.getAll("From ChangeDesigner WHERE application_no=" + applicationNo);

}

@Override
public Object doSave(HttpServletRequest request, String Authorization, Long applicationNo, String designer, MultipartFile file) {
    System.out.println("change designer " + file);
    JWTToken td = new JWTToken(Authorization);
    if (!td.isValid()) {
        return new Message().respondWithError("Authorization Error");
    }
    Message message = new Message();
    String userType = td.getUserType();
    String id;
    String today = DateConvert.today();;
    String fiscalYear;
    if (!(userType.equalsIgnoreCase("C") || userType.equalsIgnoreCase("A") || userType.equalsIgnoreCase("ADM") )) {
        return message.respondWithError("You have no access this feature!!");
    }
    try {
        String sql = "SELECT coalesce(MAX(id),0)+1 \"id\" FROM change_designer";

        message.map = (Map) message.db.getRecord(sql).get(0);
        id = message.map.get("id").toString();
    } catch (Exception e) {
        System.out.println("Exception is::" + e);
        return message.respondWithError("Could not change the receiver");
    }

    if (file.getSize() <= 0) {
        return message.respondWithError("no file!!");
    }

    try {
        String sql = "SELECT year_name \"yearName\" FROM fiscal_year WHERE '" + today + "' BETWEEN start_date AND end_date";
        message.map = (Map) message.db.getRecord(sql).get(0);
        fiscalYear = message.map.get("yearName").toString();
    } catch (Exception e) {
        System.out.println(e);
        return message.respondWithError("Please define the fiscal Year");

    }

    String fileStorageLocation = message.filePath(request.getContextPath());
    String filePath = "/" + fiscalYear + "/";
    String fileName = "";
    File f = new File(fileStorageLocation + filePath);
    try {
        if (!f.exists()) {
            f.mkdirs();
        }
    } catch (Exception e) {
        return message.respondWithError(e.getMessage());
    }

    System.out.println(file.getOriginalFilename());
    try {
        fileName = filePath + id + file.getOriginalFilename().replace(" ", "");
    } catch (Exception e) {
        System.out.println(e);
    }

    f = new File(fileStorageLocation + fileName);

    System.out.println(f.getAbsolutePath());
    try {
        file.transferTo(f);
    } catch (IOException ex) {
        Logger.getLogger(ChangeDesignerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalStateException ex) {
        Logger.getLogger(ChangeDesignerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    ChangeDesigner obj = new ChangeDesigner();
    obj.setChangeDate(DateConvert.toDate(today));
    obj.setApplicationNo(applicationNo);
    obj.setChangeBy(td.getUserName());
    obj.setChangeTo(designer);
    obj.setId(Long.parseLong(id));
    obj.setFileUrl(fileName);
    int row = da.save(obj);

    String sql = "update building_permit_application SET enter_by='" + designer + "' where ID='" + applicationNo + "'";
    message.db.save(sql);
    return message.respondWithMessage("Success");

}

}

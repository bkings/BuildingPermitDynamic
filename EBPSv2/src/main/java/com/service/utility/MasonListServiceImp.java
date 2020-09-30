/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.config.JWTToken;
import com.dao.utility.DaoMasonList;
import com.model.utility.MasonList;

@Service
public class MasonListServiceImp implements MasonListService {

    @Autowired
    DaoMasonList da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll() {

        return da.getAll("from MasonList");
    }

    /**/
    @Override
    public Object save(MasonList obj, MultipartFile photo, HttpServletRequest request, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        int id = 0;

        sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM mason_list";
        message.map = (Map) da.getRecord(sql).get(0);
        id = Integer.parseInt(message.map.get("id").toString());
        obj.setId(id);
        long photosize = 0;
        try {
            photosize = photo.getSize();
            System.out.println("photo name " + photo.getOriginalFilename());
        } catch (Exception e) {
            photosize = 0;
        }
        System.out.println(photosize);
        try {

            if (photosize < 100) {

                obj.setPhoto("");
            } else {
//                String fileStorageLocation = "C:/Program Files/Apache Software Foundation/Apache-Tomcat9.0.30/webapps" + request.getContextPath() + "Document/";

                String fileStorageLocation = message.filePath(request.getContextPath());
                String filePath = "/Mason/";
           
                File f = new File(fileStorageLocation + filePath);
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = new File(fileStorageLocation + filePath + "id" + id + ".png");
                try {
                    photo.transferTo(f);
                    obj.setPhoto(filePath + "id" + id + ".png");
                } catch (Exception e) {
                    return message.respondWithError(e.getMessage());
                }
            }

        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
        row = da.save(obj);
        msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("Duplicate entry")) {
            msg = "This record already exist";
        } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);
    }

    @Override
    public Object update(MasonList obj, MultipartFile photo, HttpServletRequest request, Integer id, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        obj.setId(id);
        long photosize = 0;
        try {
            photosize = photo.getSize();
        } catch (Exception e) {
            photosize = 0;
        }
        try {

            if (photosize < 100) {
                sql = "SELECT coalesce(photo,'') AS photo FROM mason_list WHERE id='" + id + "'";
                message.map = (Map) da.getRecord(sql).get(0);
                obj.setPhoto(message.map.get("photo").toString());
            } else {
//                String fileStorageLocation = "C:/Program Files/Apache Software Foundation/Apache-Tomcat9.0.30/webapps" + request.getContextPath() + "Document/";

                String fileStorageLocation = message.filePath(request.getContextPath());
                String filePath = "/Mason/";
                File f = new File(fileStorageLocation + filePath);
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = new File(fileStorageLocation + filePath + "id" + id + ".png");
                try {
                    photo.transferTo(f);
                    System.out.println("photo save Success!!");
                    obj.setPhoto(filePath + "id" + id + ".png");
                } catch (Exception e) {
                    return message.respondWithError(e.getMessage());
                }
            }
        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
        row = da.update(obj);
        msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("Duplicate entry")) {
            msg = "This record already exist";
        } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);
    }

    @Override
    public Object delete(String id, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }

        id = "'" + id.replace(",", "','") + "'";
        sql = "DELETE FROM mason_list WHERE ID IN (" + id + ")";
        row = da.delete(sql);
        msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.rest.application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.config.JWTToken;
import com.dao.application.DaoPublicDownloads;

import model.DateConvert;
import model.Message;

@RestController
@CrossOrigin
@RequestMapping("api/Application/PublicDownloads")


public class PublicDownloadsRestController {
    @Autowired
    DaoPublicDownloads da;
    
    Message message=new Message();
    int row;
    String msg;
        
    
    
    @GetMapping
    public Object getRecord(){
        
        String sql;
        
        sql = "Select id \"id\",file_url \"fileUrl\",file_type \"fileType\",upload_date \"uploadDate\" FROM public_downloads";
        message.list = message.db.getRecord(sql); 
        if (message.list.size() < 0) {
             return message.respondWithError("No Record Found");
            } 
        List l = new ArrayList();
        for (int i = 0; i < message.list.size(); i++) {
            message.map = (Map) message.list.get(i);
            l.add(message.map);
        }
        return l;
        
    }
    
    @GetMapping("/{id}")
    public Object getRecord(Integer id){
        
        String sql;
        
        sql = "Select id \"id\",file_url \"fileUrl\",file_type \"fileType\",upload_date \"uploadDate\" FROM public_downloads where id="+id;
        message.list = message.db.getRecord(sql); 
        if (message.list.size() < 0) {
             return message.respondWithError("No Record Found");
            } 
        message.map = (Map) message.list.get(0);
        return message.map;
          
    }

    @PostMapping
    public Object save(HttpServletRequest request,@RequestParam String fileType, @RequestParam(required = false) MultipartFile file, @RequestHeader(value = "Authorization") String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        
        Message message = new Message();
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        
        if(file.getSize()<=0){
            return message.respondWithError("no file!!");
        }
        
        String sql;
        String fileStorageLocation="";
        String year="";
        String fileNo="";
        
        try{
        sql = "SELECT coalesce(MAX(id),0)+1 \"fileNo\" FROM public_downloads";
        
        message.map = (Map) message.db.getRecord(sql).get(0);
        fileNo = message.map.get("fileNo").toString();
        fileStorageLocation = message.filePath(request.getContextPath());
        year = DateConvert.today().substring(0, 7);
        }catch(Exception e){
            System.out.println("Exception is::"+e);
            
        }
        String filePath = "/" + year + "/";
        String fileName = "";
        String today=DateConvert.today();
        File f = new File(fileStorageLocation + filePath);
        
        try {
            if (!f.exists()) {
                f.mkdirs();
            }
        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
        
        System.out.println(file.getOriginalFilename());
        try{
        fileName = filePath + fileNo + file.getOriginalFilename().replace(" ", "");
        }catch(Exception e){
            System.out.println(e);
        }
        
        f = new File(fileStorageLocation + fileName);
        
        System.out.println(f.getAbsolutePath());
        
        try {
            file.transferTo(f);
            sql = "INSERT INTO public_downloads (file_url, id,file_type,upload_date) VALUES ('" + fileName + "', " + fileNo + ",'"+fileType+"','"+today+"')";
            message.db.save(sql);
            return message.respondWithMessage(" uploaded!!");
        } catch (Exception ex) {
            return message.respondWithError(ex.getMessage());
        }

    }
    
    @PostMapping("/{id}")
    public Object update(HttpServletRequest request, @PathVariable String id, @RequestParam String fileType, @RequestParam(required = false) MultipartFile file, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        String sql;
        String fileStorageLocation = message.filePath(request.getContextPath());
        String year = DateConvert.today().substring(0, 7);
        String filePath = "/" + year + "/";
        String fileName = "";
        String today=DateConvert.today();
        File f = new File(fileStorageLocation + filePath);
        
        try {
            if (!f.exists()) {
                f.mkdirs();
            }
        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
        
        System.out.println(file.getOriginalFilename());
        try{
        fileName = filePath + id + file.getOriginalFilename().replace(" ", "");
        }catch(Exception e){
            System.out.println(e);
        }
        
        f = new File(fileStorageLocation + fileName);
        
        System.out.println(f.getAbsolutePath());
        
        try {
            file.transferTo(f);
            sql="UPDATE public_downloads SET file_url='"+fileName+"', file_type='"+fileType+"', upload_date='"+today+"' where id='"+id+"'";
            //sql = "INSERT INTO public_downloads (file_url, id,file_type,upload_date) VALUES ('" + fileName + "', " + fileNo + ",'"+fileType+"','"+today+"')";
            message.db.save(sql);
            return message.respondWithMessage(" uploaded!!");
        } catch (Exception ex) {
            return message.respondWithError(ex.getMessage());
        }

        
        
        
        //PublicDownloads obj=new PublicDownloads();
        //obj.setId(Integer.parseInt(id));
        //row = da.update(obj);
        //msg = da.getMsg();
        
        
        
        
//        if (row > 0) {
//            return message.respondWithMessage("Success");
//        } else if (msg.contains("Duplicate entry")) {
//            msg = "This record already exist";
//        } else if (msg.contains("foreign key")) {
//            msg = "this record already used in reference tables, Cannot delete of this record";
//        }
//        return message.respondWithError(msg);

//return service.update(obj, Authorization);
}
    @DeleteMapping("/{id}")
    public Object doDelete(@PathVariable String id,@RequestHeader(value = "Authorization") String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }
        String sql;
        
        id = "'" + id.replace(",", "','") + "'";
        sql = "DELETE FROM public_downloads WHERE ID IN (" + id + ")";
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

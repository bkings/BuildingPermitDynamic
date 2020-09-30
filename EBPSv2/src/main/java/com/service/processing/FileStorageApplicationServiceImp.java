/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.processing;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.config.JWTToken;
import com.dao.processing.DaoFileStorageApplication;
import com.dao.processing.DaoImpFileStorageApplication;
import com.model.processing.FileStorageApplication;
import com.model.processing.FileStorageApplicationPK;

import model.Message;

@Service
public class FileStorageApplicationServiceImp implements FileStorageApplicationService {

    @Autowired
    DaoFileStorageApplication da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll(Long applicationNo) {
        return da.getAll("from FileStorageApplication where pk.applicationNo=" + applicationNo);
    }

    @Override
    public Object save(HttpServletRequest request, Long applicationNo, Long fileType, MultipartFile[] file, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        Message message = new Message();
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        String sql;

        sql = "SELECT form_name FROM file_storage_category WHERE id=" + fileType;
        message.list = message.db.getRecord(sql);
        message.map = (Map) message.list.get(0);
        String formId = message.map.get("form_name").toString();

        String userType = td.getUserType();

        if (!message.checkSaveStatus(userType, formId, 1l)) {
            return message.respondWithError(message.getMsg());
        }
        if (file.length == 0) {
            return message.respondWithError("Please provide files");
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
//        return message.respondWithError(e.getMessage());
        }
        DaoFileStorageApplication da = new DaoImpFileStorageApplication();
        FileStorageApplication obj = new FileStorageApplication();
        FileStorageApplicationPK pk = new FileStorageApplicationPK();
        pk.setApplicationNo(applicationNo);
        pk.setFileType(fileType);
        obj.setEnterBy(td.getUserName());
        obj.setEnterDate(new Date());
        sql = "SELECT is_multiple \"isMultiple\",\"name\" AS category FROM file_storage_category where id=" + fileType;
        message.map = (Map) message.db.getRecord(sql).get(0);
        String isMultiple = message.map.get("isMultiple").toString();
        String category = message.map.get("category").toString();
        if (isMultiple.equalsIgnoreCase("N")) {
            fileName = filePath + fileType + file[0].getOriginalFilename().replace(" ", "");
            f = new File(fileStorageLocation + fileName);
            try {
                file[0].transferTo(f);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (IllegalStateException ex) {
                return message.respondWithError(ex.getMessage());
            }
            pk.setFileNo(1);
            obj.setPk(pk);
            obj.setFileUrl(fileName);
            da.save(obj);
            return message.respondWithMessage(category + " uploaded!!");
        } else {
            sql = "SELECT coalesce(MAX(file_no),0)+1 \"fileNo\" FROM file_storage_application WHERE application_no='" + applicationNo + "' AND file_type='" + fileType + "'";
            message.map = (Map) message.db.getRecord(sql).get(0);
            int count = 0, fileNo = Integer.parseInt(message.map.get("fileNo").toString());
            for (int i = 0; i < file.length; i++) {
//            fileSize = file[i].getSize();
                fileName = filePath + fileType + fileNo + file[i].getOriginalFilename().replace(" ", "");
                f = new File(fileStorageLocation + fileName);
                try {
                    file[i].transferTo(f);
                } catch (IOException ex) {
//                return message.respondWithError(ex.getMessage());
                } catch (IllegalStateException ex) {
//                return message.respondWithError(ex.getMessage());
                }
                pk.setFileNo(fileNo);
                obj.setFileUrl(fileName);
                obj.setPk(pk);
                da.save(obj);
                fileNo++;
                count++;

            }
            return message.respondWithMessage(count + " " + category + " uploaded!!");
        }
    }

    @Override
    public Object delete(String id, String Authorization) {
        System.out.println("delete service");
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        System.out.println("id");
        String url[] = id.split("-");
        for (String url1 : url) {
            System.out.println("part:  " + url1);
        }
        String applicationNo = url[0];
        String fileType = url[1];
        String fileNo = url[2];
        sql = "DELETE FROM file_storage_application WHERE file_no='" + fileNo + "' AND file_type='" + fileType + "' AND application_no='" + applicationNo + "'";
        row = da.delete(sql);

//    String fileStorageLocation = message.getFileStorageLocation();
//    File f;
        //sql = "DELETE FROM file_storage_application WHERE file_url='" + url+ "'";
        //row = da.delete(sql);
//    id = "'" + id.replace(",", "','") + "'";
        //String url[] = id.split(",");
//    for (int i = 0; i < url.length; i++) {
//        sql = "DELETE FROM file_storage_application WHERE file_url='" + url[i] + "'";
//        row = da.delete(sql);
//        if (row > 0) {
//            try {
//                f = new File(fileStorageLocation + url[i]);
//                System.out.println(f.getAbsolutePath());
//                System.out.println(f.delete());
//
//            } catch (Exception e) {
//            }
//        }
//    }
        msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);
    }
}

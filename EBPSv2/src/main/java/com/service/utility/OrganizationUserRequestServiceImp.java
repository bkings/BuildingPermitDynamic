/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.EmailService;
import com.config.JWTToken;
import com.dao.utility.DaoOrganizationUser;
import com.model.utility.OrganizationUserRequest;

import model.DateConvert;

@Service
public class OrganizationUserRequestServiceImp implements OrganizationUserRequestService {

    @Autowired
    DaoOrganizationUser da;

    @Override
    public Object getAll(String Authorization) {
        model.Message message = new model.Message();
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        return da.getAll("from OrganizationUserRequest where approveDate is null");
    }

    @Override
    public Object save(HttpServletRequest request, OrganizationUserRequest obj, MultipartFile userImage, MultipartFile userStamp, MultipartFile userSignature, String Authorization) {
        model.Message message = new model.Message();
        String msg = "", sql;
        int row;

        String contextPath = request.getContextPath();
        String id = "";
        try {

            sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM organization_user_request";
            message.map = (Map) da.getRecord(sql).get(0);
            id = message.map.get("id").toString();
            obj.setId(Long.parseLong(id));
            obj.setLoginId(obj.getLoginId().toUpperCase());
            obj.setJoinDate(new Date());
            row = da.save(obj);
            msg = da.getMsg();
            if (row > 0) {
                try {
                    saveUserImage(contextPath, userImage, "photo", obj.getId());
                } catch (Exception e) {

                }
                try {
                    saveUserImage(contextPath, userSignature, "signature", obj.getId());
                } catch (Exception e) {

                }
                try {
                    saveUserImage(contextPath, userStamp, "stamp", obj.getId());
                } catch (Exception e) {

                }

                return message.respondWithMessage("Success");
            } else if (msg.contains("Duplicate entry")) {
                msg = "This record already exist";
            }
            return message.respondWithError(msg);

        } catch (Exception e) {
            System.out.println(e);
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object update(HttpServletRequest request, Long id, OrganizationUserRequest obj, MultipartFile userImage, MultipartFile userStamp, MultipartFile userSignature, String Authorization) {
        model.Message message = new model.Message();
        String msg = "";
        int row;
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        String contextPath = request.getContextPath();
        String userType = td.getUserType();
        if (!(userType.equalsIgnoreCase("C") || userType.equalsIgnoreCase("ADM") || userType.equalsIgnoreCase("TADM"))) {
            return message.respondWithError("You can not access this feature!!");
        }

        obj.setLoginId(obj.getLoginId().toUpperCase());
        row = da.update(obj);

        msg = da.getMsg();
        if (row > 0) {
            try {
                saveUserImage(contextPath, userImage, "photo", obj.getId());
            } catch (Exception e) {

            }
            try {
                saveUserImage(contextPath, userStamp, "stamp", obj.getId());
            } catch (Exception e) {

            }
            try {
                saveUserImage(contextPath, userSignature, "signature", obj.getId());
            } catch (Exception e) {

            }

            return message.respondWithMessage("Success");
        } else if (msg.contains("Duplicate entry")) {
            msg = "This record already exist";
        } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);
    }

    void saveUserImage(String contextPath, MultipartFile file, String fileName, long userId) {
        model.Message message = new model.Message();
        String sql;

        try {
            String fileStorageLocation = message.filePath(contextPath);
            String filePath = "/designer/request/" + userId + "/";
            if (file.getSize() < 100) {
                return;
            }
            File f = new File(fileStorageLocation + filePath);
            if (!f.exists()) {
                f.mkdirs();
            }
            f = new File(fileStorageLocation + filePath + fileName + ".png");
            if (f.exists()) {
                f.deleteOnExit();
            }
            try {
                file.transferTo(f);
//                sql = "UPDATE organization_user SET " + fileName + "='" + filePath + fileName + ".png' WHERE id='" + userId + "'";
                sql = "UPDATE organization_user_request SET " + fileName + "='" + filePath + fileName + ".png' WHERE id='" + userId + "'";
                da.delete(sql);
            } catch (Exception e) {
                System.out.println("File Upload error : " + e);
            }
        } catch (Exception e) {
            System.out.println("File Upload error : " + e);
        }
    }

    @Override
    public Object approve(HttpServletRequest request, long id, String Authorization) {
        model.Message message = new model.Message();
        String sql, msg = "";
        int row;
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        sql = "UPDATE organization_user_request SET APPROVE_DATE='" + DateConvert.now() + "',APPROVE_BY='" + td.getUserName() + "' WHERE  ID='" + id + "'";
        row = da.delete(sql);
        msg = da.getMsg();
        if (row == 1) {
            sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM organization_user";
            message.map = (Map) da.getRecord(sql).get(0);
            String userId = message.map.get("id").toString();

//            String password = String.valueOf(new Random().nextDouble()).substring(3, 9);
            
            /**
             * Random Password Generation
             */
            
            String choices = "",password;
    		char alphabet;
    		for (alphabet = 'a'; alphabet <= 'z'; alphabet++) {
    			choices = choices + alphabet;
    		}
    		for (alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
    			choices = choices + alphabet;
    		}
    		choices = choices + "@#_1234567890$&";

    		String lengthValue = "6789";
    		int randomTemp = (int) (Math.random() * lengthValue.length());
    		char lengthOfRandomPwdChar = lengthValue.charAt(randomTemp);
    		int lengthOfRandowPwd = Character.getNumericValue(lengthOfRandomPwdChar);
    		char[] value = new char[lengthOfRandowPwd];
    		for (int i = 0; i < lengthOfRandowPwd; i++) {
    			int randomValue = (int) (Math.random() * choices.length());
    			value[i] = choices.charAt(randomValue);
    		}
    		
    		password = new String(value);
            
            sql = "INSERT INTO organization_user (login_id,address,db_password,education_qualification,email,join_date,license_no,mobile,municipal_reg_no,photo,signature,stamp,status,user_name,consultancy_name,id,user_type) SELECT login_id,address,'" + password + "' AS db_password,education_qualification,email,approve_date,license_no,mobile,municipal_reg_no,photo,signature,stamp,status,user_name,consultancy_name,'" + userId + "' AS id,'D' AS user_type FROM organization_user_request WHERE ID='" + id + "'";
            row = da.delete(sql);
            msg = da.getMsg();
            if (row == 1) {
                sql = "SELECT email,login_id,user_name FROM organization_user where id='" + userId + "'";
                message.map = (Map) da.getRecord(sql).get(0);
                String email = message.map.get("email").toString();
                String loginId = message.map.get("login_id").toString();
                String user_name = message.map.get("user_name").toString();
                String consultancy_name = "";
                try {
                	consultancy_name=message.map.get("consultancy_name").toString();
				} catch (Exception e) {
					consultancy_name = "Test";
				}
                
                try {
                    new EmailService().sendmail(email, "EBPS Login Details", "Dear " + consultancy_name + " Your EBPS login ID : " + loginId + "  and password : " + password+". <br />This is autogenerated mail. Please do not reply and do not share this mail. ");
                    return message.respondWithMessage("Success");
                } catch (MessagingException ex) {
                    return message.respondWithError(ex.getMessage());
                } catch (IOException ex) {
                    return message.respondWithError(ex.getMessage());
                }
            }
        }
        return message.respondWithError(msg);

    }
}

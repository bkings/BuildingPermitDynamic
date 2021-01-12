/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.EmailService;
import com.config.JWTToken;
import com.dao.utility.DaoOrganizationUser;
import com.model.utility.DesignerRenewStatus;
import com.model.utility.OrganizationUser;

import model.DateConvert;

@Service
public class OrganizationUserServiceImp implements OrganizationUserService {

	@Autowired
	DaoOrganizationUser da;
	@Autowired
	PasswordEncoder encoder;
	model.Message message = new model.Message();
	String msg = "", sql;
	int row;

	@Override
	public Object getAll() {
		return da.getAll("from OrganizationUser");
	}

	@Override
	public Object save(HttpServletRequest request, OrganizationUser obj, MultipartFile userImage, MultipartFile userStamp, MultipartFile userSignature,
			String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("invalid token");
		}
		String userType = td.getUserType();
		if (!(userType.equalsIgnoreCase("C") || userType.equalsIgnoreCase("ADM") || userType.equalsIgnoreCase("TADM"))) {
			return message.respondWithError("You can not access this feature!!");
		}
		String contextPath = request.getContextPath();
		String id = "", mailMsg = "",encryptedPwd;
		try {

			sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM organization_user";
			message.map = (Map) da.getRecord(sql).get(0);
			id = message.map.get("id").toString();
			obj.setId(Long.parseLong(id));

//            String password = String.valueOf(new Random().nextDouble()).substring(3, 9);

			/**
			 * Random Password Generation
			 */

			String choices = "", password;
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
			encryptedPwd = encoder.encode(password);
			obj.setDbPassword(encryptedPwd);
			obj.setJoinDate(DateConvert.today());
			obj.setLoginId(obj.getLoginId().toUpperCase());
			row = da.save(obj);
			msg = da.getMsg();

			if (row > 0) {
				mailMsg = new EmailService().sendmail(obj.getEmail(), "EBPS Password", "Dear " + obj.getLoginId() + " Your EBPS login ID : " + obj.getLoginId()
						+ "  and password : " + password);
				System.out.println("mailmsg " + mailMsg);
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
//                return message.respondWithMessage("Success");
				return message.respondWithMessage("Success", mailMsg);
			} else if (msg.contains("Duplicate entry")) {
				msg = "This record already exist";
			}
//            return message.respondWithError(msg);
			return message.respondWithError(msg, mailMsg);
		} catch (Exception e) {
			System.out.println(e);
//            return message.respondWithError(e.getMessage());
			return message.respondWithError(e.getMessage(), mailMsg);
		}
	}

	@Override
	public Object update(HttpServletRequest request, Long id, OrganizationUser obj, MultipartFile userImage, MultipartFile userStamp,
			MultipartFile userSignature, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("invalid token");
		}
		String contextPath = request.getContextPath();
		String userType = td.getUserType();
		if (!(userType.equalsIgnoreCase("C") || userType.equalsIgnoreCase("ADM") || userType.equalsIgnoreCase("TADM"))) {
			return message.respondWithError("You can not access this feature!!");
		}
		sql = "update organization_user set login_id='" + obj.getLoginId().toUpperCase() + "' WHERE  login_id='" + obj.getLoginId() + "'";
		da.delete(sql);
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
		try {
			String fileStorageLocation = message.filePath(contextPath);
			String filePath = "/userdocument/id" + userId + "/";
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
				sql = "UPDATE organization_user SET " + fileName + "='" + filePath + fileName + ".png' WHERE id='" + userId + "'";
				da.delete(sql);
			} catch (Exception e) {
				System.out.println("File Upload error : " + e);
			}
		} catch (Exception e) {
			System.out.println("File Upload error : " + e);
		}
	}

	@Override
	public Object renew(HttpServletRequest request, long id, String billNo, String tillD, MultipartFile file, String fiscalYear, String Authorization) {
		tillD = DateConvert.bsToAd(tillD);
		DesignerRenewStatus obj = new DesignerRenewStatus();
		obj.setDesignerId(id);
		obj.setTilldate(tillD);
		obj.setFiscalYear(fiscalYear);
		obj.setBillNo(billNo);

		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("invalid token");
		}

		String fileStorageLocation = "";
		String renewId = "";

		try {
			sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM designer_renew_status";
			message.map = (Map) da.getRecord(sql).get(0);
			renewId = message.map.get("id").toString();
			obj.setId(Long.parseLong(renewId));
		} catch (Exception e) {
			return message.respondWithError("connection error or invalid table name");
		}

		if (file.getSize() <= 0) {
			return message.respondWithError("no file!!");
		}
		fileStorageLocation = message.filePath(request.getContextPath());
		String filePath = "/" + fiscalYear + "/";
		String fileName = "";
		File f = new File(fileStorageLocation + filePath);
		try {
			System.out.println(f.getAbsolutePath());
			if (!f.exists()) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println(e);
			return message.respondWithError(e.getMessage(), f.getAbsolutePath());
		}

		fileName = filePath + "/" + renewId + ".png";
		f = new File(fileStorageLocation + fileName);
		try {
			System.out.println(file.getSize());
			file.transferTo(f);
			System.out.println("Success \n" + f.getAbsolutePath());
		} catch (Exception e) {
			System.out.println(f.getAbsolutePath());
			System.out.println(e);
			return message.respondWithError(e.getMessage(), f.getAbsolutePath());
		}

		try {
			sql = "UPDATE organization_user SET valid_date='" + tillD + "' WHERE  id='" + id + "'";
			da.delete(sql);
			obj.setFileUrl(fileName);
			obj.setRenewBy(td.getUserName());
			obj.setRenewDate(DateConvert.today());
			da.save(obj);
		} catch (Exception ex) {
			return message.respondWithError(ex.getMessage());
		}
		message.list = new ArrayList();
		message.list.add(f.getAbsolutePath());
		message.list.add(fileStorageLocation);
		return message.respondWithMessage("Success", message.list);

	}

	@Override
	public Object getAll(String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("invalid token");
		}
		return da.getAll("from OrganizationUser where loginId='" + td.getUserId() + "'");
	}

	@Override
	public Object getAllRenew() {
		return da.getAll("from DesignerRenewStatus");
	}

	@Override
	public Object resend(HttpServletRequest request, String Authorization, String id) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("invalid token");
		}
		String userType = td.getUserType();
		if (!(userType.equalsIgnoreCase("C") || userType.equalsIgnoreCase("ADM") || userType.equalsIgnoreCase("TADM"))) {
			return message.respondWithError("You can not access this feature!!");
		}

		String mailMsg = "";
		Map map = new HashMap<>();

		try {
			sql = "SELECT login_id \"loginId\",db_password AS pwd,email,user_name \"userName\" FROM organization_user WHERE login_id='" + id + "'";
			map = (Map) da.getRecord(sql).get(0);
			mailMsg = new EmailService().sendmail(map.get("email").toString(), "EBPS Password", "Dear " + map.get("userName").toString()
					+ " Your EBPS login ID : " + map.get("loginId").toString() + "  and password : " + map.get("pwd").toString());
			return message.respondWithMessage(mailMsg);
		} catch (AddressException e) {
			msg = e.getMessage();
			System.out.println("addr exc " + e.getMessage());
			e.printStackTrace();
		} catch (MessagingException e) {
			msg = e.getMessage();
			System.out.println("msging exc " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			msg = e.getMessage();
			System.out.println("ioex " + e.getMessage());
			e.printStackTrace();
		}
		return message.respondWithError(msg, mailMsg);
	}

}

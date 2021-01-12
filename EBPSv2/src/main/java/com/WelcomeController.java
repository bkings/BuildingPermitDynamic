package com;

import static model.ApplicationForm.data;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.config.JWTToken;
import com.dao.utility.DaoImpOrganizationUser;
import com.dao.utility.DaoOrganizationUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.DB;
import model.DateConvert;
import model.HibernateUtil;

@Controller
@ComponentScan
@CrossOrigin
public class WelcomeController {

	@Autowired
	PasswordEncoder encoder;

	@RequestMapping(value = "/Test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, ModelMap map) {
//        String token = com.config.JWTToken.get(request, "MANOJ", "MANOJ", "MANOJ");
//        map.put("token", "Bearer " + token);
		return "Test";
	}

	@RequestMapping(value = "/Organization", method = RequestMethod.GET)
	@ResponseBody
	public Object organization() {
		DaoOrganizationUser db = new DaoImpOrganizationUser();
		model.Message message = new model.Message();
		message.map = new HashMap();
		String sql = "SELECT DESIGNER_LOGIN_TYPE \"designerLoginType\",name,organization_code \"organizationCode\",OFFICE_NAME \"officeName\",ADDRESS address,province FROM organization_master ";
		try {
			message.map.put("organization", db.getRecord(sql).get(0));
		} catch (Exception e) {
			sql = "SELECT 'N/A' \"designerLoginType\",'N/A' name,'N/A' \"officeName\",'N/A' address,'N/A' province FROM dual ";
			message.map.put("organization", db.getRecord(sql).get(0));
		}
		return message.map;
	}

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	@ResponseBody
	public Object doLoginget(HttpServletRequest request, @RequestParam String loginId, @RequestParam String password) {
		DaoOrganizationUser db = new DaoImpOrganizationUser();
		List list = new ArrayList();
		model.Message message = new model.Message();
		String dbPassword, status, sql, userCode, userName, userType, hql;
		sql = "SELECT LOGIN_ID \"userCode\",USER_NAME \"userName\",USER_TYPE \"userType\",STATUS \"status\",coalesce(DB_PASSWORD,'') \"dbPassword\"  FROM organization_user WHERE (LOGIN_ID='"
				+ loginId.toUpperCase() + "' OR email='" + loginId + "')";

		try {
			try {
				list = db.getRecord(sql);
				if (list.isEmpty() || list.size() == 0) {
					return message.respondWithError("Invalid login id");
				}
			} catch (Exception e) {
				return message.respondWithError("no connection");
			}
			message.map = (Map) list.get(0);
			status = message.map.get("status").toString();
			if (!status.equals("Y")) {
				return message.respondWithError("Your Login is locked.");
			}
			dbPassword = message.map.get("dbPassword").toString();
			if (!encoder.matches(password, dbPassword)) {
				return message.respondWithError("Invalid Password");
			}
			/*
			 * if (!password.equalsIgnoreCase(dbPassword)) { return
			 * message.respondWithError("Invalid Password"); }
			 */
			userCode = message.map.get("userCode").toString();
			userName = message.map.get("userName").toString();
			userType = message.map.get("userType").toString();

			if (userType.equalsIgnoreCase("D")) {
				sql = "SELECT designer_login_type FROM organization_master WHERE id=1";
				Map m = (Map) db.getRecord(sql).get(0);
				if (m.get("designer_login_type").toString().equalsIgnoreCase("public")) {
					sql = "SELECT coalesce(user_name,'') \"userName\",coalesce(signature,'') signature,coalesce(stamp,'') stamp,coalesce(photo,'') photo,coalesce(municipal_reg_no,'') \"municipalRegNo\",coalesce(license_no,'') \"licenseNo\",coalesce(education_qualification,''),coalesce(address,'') address,coalesce(consultancy_name,'कृपया कन्सल्टेन्सिको नाम प्रविष्ट गर्नुहोस् ।') \"consultancyName\" FROM organization_user  WHERE login_id='"
							+ userCode + "' ";
				} else {
					sql = "SELECT coalesce(user_name,'') \"userName\",coalesce(signature,'') signature,coalesce(stamp,'') stamp,coalesce(photo,'') photo,coalesce(municipal_reg_no,'') \"municipalRegNo\",coalesce(license_no,'') \"licenseNo\",coalesce(education_qualification,''),coalesce(address,'') address,coalesce(consultancy_name,'कृपया कन्सल्टेन्सिको नाम प्रविष्ट गर्नुहोस् ।') \"consultancyName\" FROM organization_user  WHERE login_id='"
							+ userCode + "' AND valid_date>='" + DateConvert.today() + "'";
				}
				List ll = db.getRecord(sql);
				if (ll.isEmpty() || ll.size() == 0) {
					return message.respondWithError("Your login id has been expired!! ");
				}
				message.map.put("designer", ll.get(0));
			}
			message.map.remove("dbPassword");
			sql = "SELECT name,organization_code \"organizationCode\",OFFICE_NAME \"officeName\",ADDRESS address,province,COALESCE(designer_login_type,'') \"designerLoginType\",COALESCE(SHOW_SIGNATURE_IMAGE,'N') \"showSignatureImage\" FROM organization_master ";
			try {
				message.map.put("organization", db.getRecord(sql).get(0));
			} catch (Exception e) {
				sql = "SELECT 'N/A' name,'N/A' \"officeName\",'N/A' address,'N/A' province ,'' AS \"designerLoginType\"FROM dual ";
				message.map.put("organization", db.getRecord(sql).get(0));
			}
			sql = "SELECT year_code \"yearCode\",year_name \"yearName\",status,get_bsdate(start_date) \"startDate\",get_bsdate(end_date) \"endDate\" FROM fiscal_year ORDER BY status DESC,year_code DESC";
			message.map.put("fiscalYear", db.getRecord(sql));
			sql = "SELECT id,\"name\",group_position \"groupPosition\" FROM form_group_master ORDER BY group_position ";
			message.map.put("formGroupMaster", db.getRecord(sql));
//            sql = "SELECT id ,amin_approval \"aminApproval\",chief_approval \"chiefApproval\",designer_approval \"designerApproval\",engr_approval \"engrApproval\",sub_engr_approval \"subEngrApproval\" ,enter_by \"enterBy\",\"name\",rajasow_approval \"rajasowApproval\",\"table_name\" AS \"tableName\",view_url \"viewUrl\",COALESCE(poste_approval,'N') \"posteApproval\",COALESCE(postf_approval,'N') \"postfApproval\",COALESCE(postg_approval,'N') \"postgApproval\" FROM form_name_master";
			hql = "FROM FormNameMaster";
			message.map.put("formNameMaster", db.getAll(hql));
			sql = "from OrganizationUser where loginId='" + userCode + "'";
			try {
				message.map.put("userInfo", db.getAll(sql).get(0));
			} catch (Exception e) {
				message.map.put("userInfo", null);
			}
			sql = "SELECT forward_by \"forwardBy\",forward_to \"forwardTo\",form_group \"formGroup\" FROM application_forwarding_setup WHERE forward_by='"
					+ userType + "'";
			message.map.put("applicationForwardingSetup", db.getRecord(sql));
			sql = "SELECT forward_by \"forwardBy\",forward_to \"forwardTo\",form_group \"formGroup\",skip_parameter \"skipParameter\" FROM application_forwarding_setup WHERE forward_by='"
					+ userType + "'";
			message.map.put("applicationForwardingSetup", db.getRecord(sql));
			sql = "SELECT S.id,is_multiple \"isMultiple\",S.name,form_name \"formId\",F.name \"formName\",F.view_url \"viewUrl\",is_required \"isRequired\",static_category_identifier \"staticCategoryIdentifier\" FROM file_storage_category S,form_name_master F WHERE F.id=S.form_name";
			message.map.put("fileStorageCategory", db.getRecord(sql));
			sql = "SELECT M.menu,M.url,M.menu_type FROM menu_master M,menu_user_access D WHERE D.menu=M.id AND D.user_type='" + userType + "' AND D.status='Y'";
			message.map.put("menuList", db.getRecord(sql));
			sql = "SELECT id,\"name\" FROM ward_master";
			message.map.put("wardMaster", db.getRecord(sql));
			sql = "SELECT id AS \"userType\",coalesce(designation_nepali,designation) AS designation ,approve_column AS \"column\",action_status \"actionStatus\" FROM user_type_master";
			Map m = new HashMap();
			list = db.getRecord(sql);
			String userTypeAll = "";
			for (int i = 0; i < list.size(); i++) {
				m = (Map) list.get(i);
				userTypeAll += "{\"userType\":\"" + m.get("userType") + "\",\"designation\":\"" + m.get("designation") + "\",\"column\":\"" + m.get("column")
						+ "\",\"actionStatus\":\"" + m.get("actionStatus") + "\"}";
			}
			userTypeAll = "[" + userTypeAll.replace("}{", "},{") + "]";
			System.out.println(userTypeAll);
			m.put("userCode", userCode);
			m.put("userName", userName);
			m.put("userType", userType);
			m.put("userTypeAll", userTypeAll);
			String remoteUrl = request.getRemoteHost();
			m.put("remoteUrl", remoteUrl);
			String token = com.config.JWTToken.get(m);
			System.out.println("Token " + token);
			message.map.put("token", token);

			sql = "select login_id loginId,user_name userName,user_type userType,coalesce(signature,'') signature from organization_user where user_type not in ('D','TADM','ADM')";
			message.map.put("userDetails", db.getRecord(sql));
			sql = "FROM Vocabulary";
			message.map.put("vocabulary", db.getAll(sql));
			return message.map;
		} catch (Exception e) {
			return message.respondWithError(e.getMessage());
		}
	}

	@RequestMapping(value = "api/ChangePassword", method = RequestMethod.POST)
	@ResponseBody
	public Object changePassword(@RequestHeader(value = "Authorization") String Authorization, @RequestParam String password,
			@RequestParam String oldPassword) {
		model.Message message = new model.Message();
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("invalid token");
		}
		model.DB db = new model.DB();
		String sql = "SELECT db_password \"dbPassword\" FROM organization_user WHERE login_id='" + td.getUserId() + "'";
		List list = db.getRecord(sql);
		if (list.isEmpty()) {
			return message.respondWithError("Invalid Authorization");
		}
		Map map = (Map) list.get(0);
		String dbPassword = map.get("dbPassword").toString();
		if (!encoder.matches(oldPassword, dbPassword)) {
			return message.respondWithError("Old Password not valid!!");
		}
		/*
		 * if (!dbPassword.equals(oldPassword)) { return
		 * message.respondWithError("Old Password not valid!!"); }
		 */
		sql = "UPDATE organization_user SET db_password='" + encoder.encode(password) + "' WHERE login_id='" + td.getUserId() + "'";
		int row = db.save(sql);
		if (row == 0) {
			return message.respondWithError(db.getMsg());
		}
		return message.respondWithMessage("Success");
	}

	@RequestMapping(value = "/ForgetPassword", method = RequestMethod.POST)
	@ResponseBody
	public Object forgetPassword(@RequestBody String obj) throws MessagingException, AddressException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap();
		try {
			map = mapper.readValue(obj, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String email = map.get("email").toString();
		model.Message message = new model.Message();

		String sql = "SELECT login_id \"id\" FROM organization_user WHERE email='" + email + "'";
		List list = message.db.getRecord(sql);
		if (list.isEmpty()) {
			return message.respondWithError("Invalid Email Address");
		}
		message.map = (Map) list.get(0);
		String id = message.map.get("id").toString();

//        String password = String.valueOf(new Random().nextDouble()).substring(3, 9);

		/**
		 * random password generation with numbers,letters and special characters
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

		sql = "UPDATE organization_user set db_password='" + encoder.encode(password) + "'where login_id='" + id + "'";
		message.db.save(sql);

		EmailService emailService = new EmailService();

		String sub = "Your password has been reset successfully!";
		String body = "Your login: " + id + " and  password: " + password;
		return emailService.sendmail(email, sub, body);
	}

	@RequestMapping(value = "api/DesignerList", method = RequestMethod.GET)
	@ResponseBody
	public Object designerList() {
		String sql;
		model.Message message = new model.Message();
		sql = "SELECT get_bsdate((SELECT MAX(D.renew_date) FROM designer_renew_status D WHERE D.designer_id=O.id)) \"renewDate\",get_bsdate((SELECT MAX(D.till_date) FROM designer_renew_status D WHERE D.designer_id=O.id)) \"tillDate\",O.user_name \"userName\",O.address \"address\", O.education_qualification \"educationQualification\",O.email \"email\",get_bsdate(O.join_date) \"joinDate\",O.mobile \"mobile\",O.municipal_reg_no \"municipalRegNo\",O.consultancy_name \"consultancyName\",O.license_no \"licenseNo\",O.photo,O.signature,O.stamp FROM organization_user O WHERE O.user_type='D' ORDER BY O.user_name";
		message.list = message.db.getRecord(sql);
		if (message.list.size() < 0) {
			return message.respondWithError("no designer is registered");
		}
		List l = new ArrayList();
		for (int i = 0; i < message.list.size(); i++) {
			message.map = (Map) message.list.get(i);
			l.add(message.map);
		}
		return l;
	}

	@RequestMapping(value = "/api/DatabaseConfigration", method = RequestMethod.POST)
	@ResponseBody
	public Map databaseConfigration() {

		Map map = new HashMap();
		try {
			HibernateUtil.init();
			DB db = new DB();
			String sql = "SELECT SYSDATE() 'today' FROM dual";
			List list = db.getRecord(sql);
			if (list.size() > 0) {
				map.put("message", "Success");
			} else {
				map.put("error", "Database connection error");
			}
		} catch (Exception e) {
			map.put("error", e.getMessage());
		}
		return map;

	}

	@RequestMapping(value = "Emp/LoginVerification", method = RequestMethod.GET)
	public String loginVerification(@RequestParam String path, HttpSession session) {
		session.setAttribute("path", path);
		return "login/LoginVerification";
	}

	@RequestMapping(value = "Emp/LoginVerification/{token}", method = RequestMethod.GET)
	public String tokenVerification(@PathVariable String token, HttpSession session) {
		JWTToken td = new JWTToken("Bearer " + token);
		try {
			if (!td.isStatus()) {
				session.invalidate();
				return "redirect:../../";
			}
		} catch (Exception e) {
			session.invalidate();
			return "redirect:../../";
		}
		session.setAttribute("token", "Bearer " + token);
		return "redirect:../TokenVerification";

	}

	@RequestMapping(value = "Emp/TokenVerification", method = RequestMethod.GET)
	public String tokenVerification() {
		return "login/TokenVerification";
	}

	@RequestMapping(value = "Logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "login/Logout";
	}

	@RequestMapping(value = "FormNameMaster", method = RequestMethod.GET)
	@ResponseBody
	public String logout() {
		DB db = new DB();
		String sql;
		for (int i = 0; i < data.length; i++) {
			sql = "INSERT INTO form_name_master(id,page_code,\"name\",view_url) VALUES('" + data[i][0] + "','" + data[i][1] + "','" + data[i][2] + "','"
					+ data[i][4] + "');";
			db.save(sql);
		}
		return "OK";
	}

	@RequestMapping(value = "AdBsCalender", method = RequestMethod.GET)
	@ResponseBody
	public String adBsCalender() {
		DB db = new DB();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat d = new SimpleDateFormat("EEE");
		Date bb = DateConvert.bsToAdDate("2000-01-10");
		Calendar c = Calendar.getInstance();
		c.setTime(bb);
		String adDate, bsDate, day, sql;
		Long i = 0l;
		for (i = 0l; true; i++) {
			bb = c.getTime();
			adDate = df.format(bb);
			bsDate = DateConvert.adtobsDate(adDate);
			if (bsDate.equalsIgnoreCase("2080-12-30")) {
				break;
			}
			sql = "INSERT INTO public.ad_bs_calender (ad_date, bs_date, \"day\") VALUES ('" + adDate + "', '" + bsDate + "', '" + d.format(bb) + "');";
			db.save(sql);
			c.add(Calendar.DATE, 1);
		}
		return i.toString();
	}
}

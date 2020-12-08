package com.controller.rest.application.adminPrivileges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.config.JWTToken;
import com.dao.dynamic.general.DaoGeneral;
import com.log.ApplicationLog;

import model.HibernateUtil;
import model.Message;

/**
 * 
 * @author bkings_bjr
 *
 */

@RestController
@CrossOrigin
@RequestMapping("api/admin")
public class AdminPrivileges {

	@Autowired
	DaoGeneral dao;

	Message message = new Message();
	int row;
	String sql, msg;
	List list;
	Map map;

	@GetMapping("/formData/{applicationNo}")
	public ResponseEntity<Object> adminGet(@PathVariable String applicationNo, @RequestParam Long formId) {
		String tableName;
		Properties props = HibernateUtil.getProps();
		String tableSchema = props.getProperty("hibernate.default_schema");
		if (applicationNo.length() < 6)
			return ResponseEntity.badRequest().body(message.respondWithError("Invalid Application Number."));

		try {
			sql = "SELECT table_id AS \"tableId\",(SELECT table_name FROM ebps_tables WHERE id=table_id) AS \"tableName\" FROM form_name_master WHERE id="
					+ formId;
			list = dao.getRecords(sql);
			map = (Map) list.get(0);
			tableName = map.get("tableName").toString();

			sql = "SELECT c.column_name as \"primaryKey\" FROM information_schema.key_column_usage AS c LEFT JOIN information_schema.table_constraints AS t ON t.constraint_name = c.constraint_name WHERE t.table_name = '"
					+ tableName + "' AND t.constraint_type = 'PRIMARY KEY' AND t.table_schema='" + tableSchema + "' AND c.table_schema='" + tableSchema + "'";
			list = dao.getRecords(sql);
			map = (Map) list.get(0);

			sql = "SELECT * FROM " + tableName + " WHERE " + map.get("primaryKey").toString() + "=" + applicationNo;
			list = dao.getRecords(sql);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message.respondWithError("Server Error"));
		}

		if (list.isEmpty())
			return ResponseEntity.accepted().body(message.respondWithMessage("Record Not Found."));
		return ResponseEntity.ok(list);
	}

	@PutMapping("/formAdminEdit/{applicationNo}")
	public ResponseEntity<Object> adminEdit(@RequestHeader String Authorization, @PathVariable String applicationNo, @RequestParam Long formId,
			@RequestBody Object obj) {

		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return ResponseEntity.badRequest().body(message.respondWithError("Invalid Authorization."));
		}
		if (!td.getUserType().equals("TADM")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message.respondWithError("You are unauthorized to perform this action."));
		}

		Properties props = HibernateUtil.getProps();
		String tableSchema = props.getProperty("hibernate.default_schema");
		String tableName, columnName = "", columns = "";
		Map m = new HashMap<>();
		List values = new ArrayList<>();

		if (applicationNo.length() < 6)
			return ResponseEntity.badRequest().body(message.respondWithError("Invalid Application Number."));

		try {
			sql = "SELECT table_id AS \"tableId\",(SELECT table_name FROM ebps_tables WHERE id=table_id) AS \"tableName\" FROM form_name_master WHERE id="
					+ formId;
			list = dao.getRecords(sql);
			map = (Map) list.get(0);
			tableName = map.get("tableName").toString();

			map = (Map) obj;
			System.out.println("user " + td.getUserId());
			Map credentials = (Map) map.get("cred");
			Boolean verification = verifyUser(credentials, td.getUserId());
			if (!verification)
				return ResponseEntity.badRequest().body(message.respondWithError("Verification Failed !"));
			for (Object key : ((Map) map.get("columns")).keySet()) {
				sql = "SELECT coalesce((SELECT column_name FROM ebps_columns WHERE id=ebps_column_id),'') \"columnName\" FROM form_fields WHERE id='" + key
						+ "'";
				try {
					list = dao.getRecords(sql);
					m = (Map) list.get(0);
					columnName = m.get("columnName").toString();
				} catch (Exception e) {
					System.out.println("col exc " + e.getMessage());
					return ResponseEntity.badRequest().body(message.respondWithError("Could not find column for " + key));
				}

				String x;
				try {
					x = ((Map) map.get("columns")).get(key).toString();
				} catch (Exception e) {
					x = null;
				}
				if (x != null) {
					columns = columns + "," + columnName;
					values.add(((Map) map.get("columns")).get(key));
				}
			}

			sql = "SELECT c.column_name as \"primaryKey\" FROM information_schema.key_column_usage AS c LEFT JOIN information_schema.table_constraints AS t ON t.constraint_name = c.constraint_name WHERE t.table_name = '"
					+ tableName + "' AND t.constraint_type = 'PRIMARY KEY' AND t.table_schema='" + tableSchema + "' AND c.table_schema='" + tableSchema + "'";
			map = (Map) dao.getRecords(sql).get(0);

			sql = "SELECT * FROM " + tableName + " WHERE " + map.get("primaryKey").toString() + "=" + applicationNo;
			list = dao.getRecords(sql);

			if (list.isEmpty()) {
				return ResponseEntity.badRequest().body(message.respondWithError("Record Not Found."));
			} else {
				String[] cols = columns.substring(1).split(",");
				String updateSql = "";
				for (int j = 0; j < cols.length; j++) {
					updateSql = updateSql + "," + cols[j] + "='" + String.valueOf(values.get(j)) + "' ";
				}
				sql = "UPDATE " + tableName + " SET " + updateSql.substring(1) + "WHERE " + map.get("primaryKey").toString() + "=" + applicationNo;
				System.out.println("final sql " + sql);
				row = dao.execute(sql);
				msg = dao.getMsg();
				if (row > 0) {
					ApplicationLog.save("/formAdminEdit/" + applicationNo +"?formId=" + formId, "PUT", td.getUserType() + " " + td.getUserName(), "updated: [" + updateSql.substring(1) + "]");
					return ResponseEntity.ok(message.respondWithMessage("Data Updated."));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message.respondWithError("Server Error"));
		}
		return ResponseEntity.badRequest().body(message.respondWithError(msg));
	}

	public Boolean verifyUser(Map cred, String loginId) {
		Map m2 = new HashMap<>();
		String pwd = "", credPassword = "", credConfirmPassword = "";
		sql = "SELECT db_password \"dbPassword\" FROM organization_user WHERE login_id='" + loginId + "' AND user_type='TADM'";
		try {
			list = dao.getRecords(sql);
			if (list.isEmpty())
				return false;
			m2 = (Map) list.get(0);
			pwd = m2.get("dbPassword").toString();
			credPassword = cred.get("password").toString();
			credConfirmPassword = cred.get("confirmPassword").toString();
			if (!credPassword.equals(credConfirmPassword))
				return false;
			if (!pwd.equals(credConfirmPassword) || !pwd.equals(credPassword))
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

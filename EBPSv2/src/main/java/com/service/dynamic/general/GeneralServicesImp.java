package com.service.dynamic.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.dynamic.DaoStatus;
import com.dao.dynamic.general.DaoGeneral;
import com.model.application.ApplicationApproved;
import com.model.dynamic.Status;
import com.model.dynamic.StatusPK;
import com.service.dynamic.StatusService;

import model.HibernateUtil;
import model.Message;

/**
 * 
 * @author bkings_bjr
 *
 */

@Service
public class GeneralServicesImp implements GeneralServices {

	@Autowired
	DaoGeneral dao;
	@Autowired
	StatusService service;
	String msg = "", sql;
	int row;
	Message message = new Message();
	List list = new ArrayList<>();
	Map map = new HashMap<>();

	@Override
	public Object getAll(Long applicationNo, String Authorization, String formId) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithMessage("Invalid Authorization");
		}
		sql = "SELECT table_name \"tableName\" FROM ebps_tables WHERE id=(SELECT table_id FROM form_name_master WHERE id=" + formId + ")";
		list = dao.getRecords(sql);
		map = (Map) list.get(0);
		String tableName;
		try {
			tableName = map.get("tableName").toString();
		} catch (Exception e) {
			tableName = "";
		}

		try {
			message.list = dao.getRecords("SELECT * FROM " + tableName + " WHERE application_no=" + applicationNo);
		} catch (Exception e) {
			msg = e.getMessage();
		}

		if (msg.contains("does not exist")) {
			return message.respondWithError("Table does not exist! Create table first.");
		}

		if (message.list.isEmpty()) {
			return message.respondWithError("Record Not Found");
		}
		message.map = new HashMap();
		message.map.put("data", message.list.get(0));
		message.map.put("comment", message.getComment("" + applicationNo, "48"));
		message.map.put("history", message.getHistory(applicationNo, "48"));
		return message.map;

	}

	/**
	 * Single method for all forms (through a single controller)
	 * 
	 * @return Either the integer value greater than 0 for successful transaction or
	 *         the error message.
	 */
	@Override
	public Object save(Object obj, Long applicationNo, String formId, String Authorization) {
		
		Properties props = HibernateUtil.getProps();
		String tableSchema = props.getProperty("hibernate.default_schema");

		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}

		Map m = new HashMap<>();
		String userType = td.getUserType();

		String columns = "", columnName = "";
		Object values = "";
		try {
			sql = "SELECT ET.table_name as \"tableName\",FF.name as \"name\",ET.id as \"tableId\" FROM ebps_tables ET,ebps_columns EC,form_name_master FM,form_fields FF WHERE ET.id=FM.table_id AND FF.form_id=FM.id AND FF.ebps_column_id=EC.id AND FM.id="
					+ formId;
			list = dao.getRecords(sql);
			map = (Map) list.get(0);
			String tableName = map.get("tableName").toString();
			Long tableId = Long.parseLong(map.get("tableId").toString());

			map = (Map) obj;
			for (Object key : map.keySet()) {
				sql = "SELECT coalesce((SELECT column_name FROM ebps_columns WHERE id=ebps_column_id),'') \"columnName\" FROM form_fields WHERE id='" + key
						+ "'";
				try {
					list = dao.getRecords(sql);
					m = (Map) list.get(0);
					columnName = m.get("columnName").toString();
				} catch (Exception e) {
					System.out.println("col exc " + e.getMessage());
					return message.respondWithError("Could not find column for " + key);
				}
				columns = columns + "," + columnName;
				values = values + ",'" + map.get(key).toString() + "'";
			}

			// get primary key (doesn't make much sense i guess)
			sql = "SELECT c.column_name as \"primaryKey\" FROM information_schema.key_column_usage AS c LEFT JOIN information_schema.table_constraints AS t ON t.constraint_name = c.constraint_name WHERE t.table_name = '"
					+ tableName + "' AND t.constraint_type = 'PRIMARY KEY' AND t.table_schema='"+tableSchema+"' AND c.table_schema='"+tableSchema+"'";
			map = (Map) dao.getRecords(sql).get(0);

			sql = "SELECT * FROM " + tableName + " WHERE " + map.get("primaryKey").toString() + "=" + applicationNo;
			list = dao.getRecords(sql);
			if (list.isEmpty()) {
				sql = "INSERT INTO " + tableName + " (" + columns.substring(1) + ") values(" + values.toString().substring(1) + ")";
				System.out.println("sql " + sql);
				row = dao.execute(sql);
				msg = dao.getMsg();

				// save status part
				sql = "SELECT id as \"userType\" FROM user_type_master WHERE id NOT IN ('ADM','TADM')";
				List userTypeList = dao.getRecords(sql);
				Map mm = new HashMap<>();
				for (Object o : userTypeList) {
					try {
						mm = (Map) o;
						Status status = new Status();
						status.setPk(new StatusPK(applicationNo, Long.parseLong(formId), mm.get("userType").toString()));
						status.setTableId(tableId);
						service.save(status);
					} catch (Exception e) {
						System.out.println("e " + e.getMessage());
					}
				}

				if (row > 0) {
					message.getEnterByStatus(userType, Integer.parseInt(formId), applicationNo);
					return message.respondWithMessage("Success");
				}
			} else {
				String[] cols = columns.substring(1).split(",");
				String[] vals = values.toString().substring(1).split(",");
				String updateSql = "";
				for (int j = 0; j < cols.length; j++) {
					updateSql = updateSql + "," + cols[j] + "=" + vals[j] + " ";
				}
				sql = "UPDATE " + tableName + " SET " + updateSql.substring(1) + " WHERE " + map.get("primaryKey") + "=" + applicationNo;
				System.out.println("final sql " + sql);
				row = dao.execute(sql);
				msg = dao.getMsg();

				if (row > 0) {
					return message.respondWithMessage("Success");
				}
			}

		} catch (Exception e) {
			msg = e.getMessage();
		}
		return message.respondWithError(msg);
	}

	@Override
	public Object approve(Long applicationNo, ApplicationApproved obj, String Authorization, String formId) {
		return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, formId);
	}
}

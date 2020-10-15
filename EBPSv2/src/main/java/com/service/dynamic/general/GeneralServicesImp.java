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
		Properties props = HibernateUtil.getProps();
		String tableSchema = props.getProperty("hibernate.default_schema");
		
		List l1 = new ArrayList<>();
		Map m1 = new HashMap<>(), returnMap = new HashMap<>();
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithMessage("Invalid Authorization");
		}

		Long rTableId, rColumnId;
		String columnName, tableName, formTableName,primaryKey;

		sql = "SELECT table_name \"tableName\" FROM ebps_tables WHERE id=(SELECT table_id FROM form_name_master WHERE id=" + formId + ")";
		list = dao.getRecords(sql);
		if(list.isEmpty()) {
			return message.respondWithError("Invalid Form Id.");
		}
		map = (Map) list.get(0);
		formTableName = map.get("tableName").toString();
		
		// This case covers only tables with single primary key.
		sql = "SELECT c.column_name as \"primaryKey\" FROM information_schema.key_column_usage AS c LEFT JOIN information_schema.table_constraints AS t ON t.constraint_name = c.constraint_name WHERE t.table_name = '"+formTableName+"' AND t.constraint_type = 'PRIMARY KEY' AND t.table_schema='"+tableSchema+"' AND c.table_schema='"+tableSchema+"'";
		list = dao.getRecords(sql);
		if(list.isEmpty()) {
			return message.respondWithError("Columns not found.");
		}
		map = (Map) list.get(0);
		try {
			primaryKey = map.get("primaryKey").toString();
		} catch (Exception e) {
			primaryKey = "applicationNo";
		}
		
		sql = "SELECT coalesce(referenced_table_id,0) \"rTableId\","
				+ "coalesce(referenced_column_id,0) \"rColumnId\" from form_fields where form_id=" + formId;
		try {
			list = dao.getRecords(sql);
			if(list.isEmpty()) {
				return message.respondWithError("Fields not availabe for this form id.");
			}
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				rTableId = Long.parseLong(map.get("rTableId").toString());
				rColumnId = Long.parseLong(map.get("rColumnId").toString());
				if(rColumnId == 0) continue;
//				sql = "SELECT column_name \"columnName\",(select table_name from ebps_tables where id=table_id) \"tableName\" FROM ebps_columns WHERE id='"+rColumnId+"' AND table_id='"+rTableId+"' ";
				sql = "SELECT column_name \"columnName\",(select table_name from ebps_tables where id=table_id) \"tableName\" FROM ebps_columns WHERE id='"
						+ rColumnId + "' ";
				m1 = (Map) dao.getRecords(sql).get(0);
				columnName = m1.get("columnName").toString();
				tableName = m1.get("tableName").toString();
				sql = "SELECT c.column_name as \"primaryKey\" FROM information_schema.key_column_usage AS c LEFT JOIN information_schema.table_constraints AS t ON t.constraint_name = c.constraint_name WHERE t.table_name = '"+tableName+"' AND t.constraint_type = 'PRIMARY KEY' AND t.table_schema='"+tableSchema+"' AND c.table_schema='"+tableSchema+"'";
				m1 = (Map) dao.getRecords(sql).get(0);
				sql = "SELECT \"" + columnName + "\" AS \"" + columnName + "\" FROM " + tableName + " WHERE \""+m1.get("primaryKey").toString()+"\"=" + applicationNo;
				m1 = (Map) dao.getRecords(sql).get(0);
				returnMap.put(columnName, m1.get(columnName));
			}
		} catch (Exception e) {
			msg = Message.exceptionMsg(e);
			System.out.println("Error " + e.getMessage());
		}

		sql = "SELECT a.*,b.form_id \"formId\",b.user_type \"userType\",b.date \"date\",b.name \"name\",b.status \"status\" FROM " + formTableName + " a left join status b ON a.\""+primaryKey+"\"=b.application_no WHERE a.\""+primaryKey+"\"=" + applicationNo + " AND b.form_id="+formId+" AND b.user_type='"+td.getUserType()+"'";
		message.list = dao.getRecords(sql);
		msg = dao.getMsg();
		if (msg.contains("does not exist")) {
			return message.respondWithError("Something went wrong.");
		}

		/*
		 * if (message.list.isEmpty()) { return
		 * message.respondWithError("Record Not Found"); }
		 */
		message.map = new HashMap();
		message.map.put("referencedData", returnMap);
		message.map.put("formData", message.list);
		message.map.put("comment", message.getComment("" + applicationNo, formId));
		message.map.put("history", message.getHistory(applicationNo, formId));
		return message.map;

	}

	/**
	 * Single method for all forms (through a single controller)
	 * 
	 * @param obj Contains data to be saved to table.
	 * @param applicationNo To identify it as primary key and also save status table.
	 * @param Authorization Is the authorization token.
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
			// This case covers only tables with single primary key.
			sql = "SELECT c.column_name as \"primaryKey\" FROM information_schema.key_column_usage AS c LEFT JOIN information_schema.table_constraints AS t ON t.constraint_name = c.constraint_name WHERE t.table_name = '"
					+ tableName + "' AND t.constraint_type = 'PRIMARY KEY' AND t.table_schema='" + tableSchema + "' AND c.table_schema='" + tableSchema + "'";
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

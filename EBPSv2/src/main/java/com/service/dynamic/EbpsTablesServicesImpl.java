package com.service.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.dynamic.DaoEbpsTables;
import com.model.dynamic.EbpsColumns;
import com.model.dynamic.EbpsTables;

import model.HibernateUtil;
import model.Message;

@Service
public class EbpsTablesServicesImpl implements EbpsTablesServices {

	@Autowired
	DaoEbpsTables dao;
	Message message = new Message();
	String msg = "", sql;
	int row;

	List<Object> list = new ArrayList<>();
	Map map = new HashMap<>();

	public String dataTypeMappingForDb(String dataType) {
		switch (dataType) {
		case "string":
			dataType = "character varying";
			break;
		case "text":
			dataType = "text";
			break;
		case "number":
			dataType = "bigint";
			break;
		case "date":
			dataType = "date";
			break;
		default:
			dataType = "character varying";
		}
		return dataType;
	}

	public String dataTypeMappingForSetup(String dataType) {
		switch (dataType) {
		case "character varying":
			dataType = "string";
			break;
		case "text":
			dataType = "text";
			break;
		case "bigint":
			dataType = "number";
			break;
		}
		return dataType;
	}

	@Override
	public Object getAll(String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}
		return dao.getAll("FROM EbpsTables ORDER BY id");
	}

	@Override
	public Object synchronizeTable(Long tableId) {
		Properties props = HibernateUtil.getProps();
		String tableSchema = props.getProperty("hibernate.default_schema");
		System.out.println("Current targeted schema = " + tableSchema);

		Map m = new HashMap<>();
		String tableName = "", sqlColAdd = "";
		Long columnId;
		sql = "SELECT column_name \"columnName\",reference \"dataType\",coalesce(is_pk,'N') \"isPrimaryKey\" FROM ebps_columns WHERE table_id=" + tableId;
		final List<Object> setupList = dao.getRecord(sql);
		int numberOfSetupSavedColumns = setupList.size();
//		sql = "SELECT column_name \"columnName\",data_type \"dataType\" FROM information_schema.columns WHERE table_name=(SELECT table_name FROM ebps_tables WHERE id="
//				+ tableId + ") AND table_schema= '" + tableSchema + "' ";
		sql = "select c.column_name \"columnName\",c.data_type \"dataType\",(CASE when d.constraint_type='PRIMARY KEY' then 'Y' else 'N' end) \"isPrimaryKey\" FROM information_schema.columns c left join (select * from information_schema.key_column_usage k,information_schema.table_constraints t where k.constraint_name=t.constraint_name and k.table_name=(SELECT table_name FROM ebps_tables WHERE id='"+tableId+"') and t.table_name=(SELECT table_name FROM ebps_tables WHERE id='"+tableId+"') and k.table_schema='"+tableSchema+"' and t.table_schema='"+tableSchema+"') d on c.column_name=d.column_name where c.table_name=(SELECT table_name FROM ebps_tables WHERE id='"+tableId+"') and c.table_schema='"+tableSchema+"'";
		final List<Object> individualTableListDb = dao.getRecord(sql);
		int numberOfColumnsInDb = individualTableListDb.size();
		msg = "Already synced.";
		if (numberOfSetupSavedColumns != numberOfColumnsInDb) {
			if (numberOfSetupSavedColumns > numberOfColumnsInDb) {
				// Alter add
				setupList.removeIf(data -> {
					Map col = (Map) data;
					List colName = (List) individualTableListDb.stream().map(d -> {
						Map mm = (Map) d;
						return mm.get("columnName").toString();
					}).collect(Collectors.toList());
					return colName.contains(col.get("columnName").toString());
				});

				System.out.println("setup list " + setupList);

				sql = "SELECT table_name AS \"tableName\" FROM ebps_tables WHERE id=" + tableId;
				list = dao.getRecord(sql);
				map = (Map) list.get(0);
				tableName = map.get("tableName").toString();
				sql = "ALTER TABLE " + tableName;
				for (int i = 0; i < setupList.size(); i++) {
					map = (Map) setupList.get(i);
					if (i == setupList.size() - 1) {
						if(map.get("isPrimaryKey").toString().equalsIgnoreCase("Y")) {
							sqlColAdd = sqlColAdd + " ADD COLUMN \"" + map.get("columnName").toString() + "\" "+dataTypeMappingForDb(map.get("dataType").toString())+" PRIMARY KEY;";
						} else {
							sqlColAdd = sqlColAdd + " ADD COLUMN \"" + map.get("columnName").toString() + "\" " + dataTypeMappingForDb(map.get("dataType").toString())
							+ ";";
						}
					} else {
						if(map.get("isPrimaryKey").toString().equalsIgnoreCase("Y")) {
							sqlColAdd = sqlColAdd + " ADD COLUMN \"" + map.get("columnName").toString() + "\" " + dataTypeMappingForDb(map.get("dataType").toString())
							+ " PRIMARY KEY, ";
						} else {
							sqlColAdd = sqlColAdd + " ADD COLUMN \"" + map.get("columnName").toString() + "\" " + dataTypeMappingForDb(map.get("dataType").toString())
							+ ", ";
						}
					}
				}
				sql = sql + sqlColAdd;
				row = dao.execute(sql);
				if (dao.getMsg().equalsIgnoreCase("")) {
					msg = "Columns added to " + tableName + " in the database.";
				} else {
					msg = dao.getMsg();
				}
			} else if (numberOfColumnsInDb > numberOfSetupSavedColumns) {
				// insert
				individualTableListDb.removeIf(data -> {
					Map col = (Map) data;
					List colName = setupList.stream().map(d -> {
						Map mm = (Map) d;
						return mm.get("columnName").toString();
					}).collect(Collectors.toList());
					return colName.contains(col.get("columnName").toString());
				});
				System.out.println("individual list is " + individualTableListDb);

				sql = "SELECT coalesce(MAX(ID),0)+1 as id FROM ebps_columns";
				map = (Map) dao.getRecord(sql).get(0);
				columnId = Long.parseLong(map.get("id").toString());
				sql = "INSERT INTO ebps_columns (id,column_name,name,reference,table_id,is_pk)values(";

				for (int i = 0; i < individualTableListDb.size(); i++) {
					map = (Map) individualTableListDb.get(i);
					sqlColAdd = sql + "" + columnId + ",'" + map.get("columnName").toString() + "','" + map.get("columnName").toString() + "','"
							+ dataTypeMappingForSetup(map.get("dataType").toString()) + "'," + tableId + ",'"+map.get("isPrimaryKey").toString()+"')";
					columnId++;
					System.out.println("sql " + sqlColAdd);
					row = dao.execute(sqlColAdd);
				}
				if (row > 0) {
					msg = "Successfully synced with database.";
				}
			}
		}
		return message.respondWithMessage(msg);
	}

	@Override
	public Object save(EbpsTables obj, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}

		List<EbpsColumns> columns = obj.getEbpsColumns();

		Long columnId;
		try {
			sql = "SELECT COALESCE(MAX(ID),0)+1 as id FROM ebps_columns";
			map = (Map) dao.getRecord(sql).get(0);
			columnId = Long.parseLong(map.get("id").toString());
			for (int i = 0; i < columns.size(); i++) {
				if (String.valueOf(columns.get(i).getId()).length() == 0 || String.valueOf(columns.get(i).getId()).equalsIgnoreCase("") || columns.get(i)
						.getId() == null) {
					columns.get(i).setId(columnId);
					columnId++;
				} else {
					columns.get(i).setId(columns.get(i).getId());
				}
			}
		} catch (Exception e) {
			System.out.println("e " + e.getMessage());
		}

		try {
			List l = new ArrayList<>();
			if (String.valueOf(obj.getId()).length() == 0 || String.valueOf(obj.getId()).equalsIgnoreCase("") || obj.getId() == null) {
				sql = "SELECT COALESCE(MAX(id),0)+1 as id FROM ebps_tables";
				l = dao.getRecord(sql);
				Map m = new HashMap<>();
				m = (Map) l.get(0);
				obj.setId(Long.parseLong(m.get("id").toString()));
			}
			obj.setEbpsColumns(columns);
			row = dao.save(obj);
			msg = dao.getMsg();
			if (row > 0) {
				return message.respondWithMessage("Success");
			}
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return message.respondWithError(msg);
	}

	@Override
	public Object update(EbpsTables obj, String Authorization, long id) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}
		row = dao.update(obj);
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
			return message.respondWithError("Invalid Authorization");
		}
		List<EbpsTables> l = new ArrayList<EbpsTables>();
		EbpsTables obj = new EbpsTables();
		String[] ids = id.split(",");
		for (String i : ids) {
			try {
				l = dao.getAll("FROM EbpsTables WHERE id=" + i);
				obj = l.get(0);
				row = dao.delete(obj);
				msg = dao.getMsg();
				if (row != 0) {
					row++;
				}
			} catch (Exception e) {
				msg = e.getMessage();
			}
		}

		if (row > 0) {
			return message.respondWithMessage("Success");
		} else if (msg.contains("foreign key")) {
			msg = "Current records are being referenced from other tables.Could not delete.";
		}
		return message.respondWithError(msg);
	}

	@Override
	public Object deleteColumns(String id, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}

		List<EbpsColumns> l = new ArrayList<EbpsColumns>();
		EbpsColumns obj = new EbpsColumns();
		String[] ids = id.split(",");
		for (String i : ids) {
			try {
				l = dao.getAllData("FROM EbpsColumns WHERE id=" + i);
				obj = l.get(0);
				row = dao.deleteColumns(obj);
				msg = dao.getMsg();
				if (row != 0) {
					row++;
				}
			} catch (Exception e) {
				msg = e.getMessage();
			}
		}
		if (row > 0) {
			return message.respondWithMessage("Success");
		} else if (msg.contains("foreign key")) {
			msg = "Current records are being referenced from other tables.Could not delete.";
		}
		return message.respondWithError(msg);
	}

}

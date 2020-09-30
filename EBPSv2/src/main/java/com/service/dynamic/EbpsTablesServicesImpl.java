package com.service.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.dynamic.DaoEbpsTables;
import com.model.dynamic.EbpsColumns;
import com.model.dynamic.EbpsTables;

import model.Message;

@Service
public class EbpsTablesServicesImpl implements EbpsTablesServices {

	@Autowired
	DaoEbpsTables dao;
	Message message = new Message();
	String msg = "", sql;
	int row;

	List list = new ArrayList<>();
	Map map = new HashMap<>();

	@Override
	public Object getAll(String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}
		return dao.getAll("FROM EbpsTables");
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
		} else if(msg.contains("foreign key")) {
			msg = "Current records are being referenced from other tables.Could not delete.";
		}
		return message.respondWithError(msg);
	}

}

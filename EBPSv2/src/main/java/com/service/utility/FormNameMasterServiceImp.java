/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoFormNameMaster;
import com.model.dynamic.FormFields;
import com.model.utility.FormNameMaster;

import model.Message;

@Service
public class FormNameMasterServiceImp implements FormNameMasterService {

	@Autowired
	DaoFormNameMaster da;
	model.Message message = new model.Message();
	String msg = "", sql;
	int row;

	@Override
	public Object getAll() {
		return da.getAll("from FormNameMaster order by id");
	}

	@Override
	public Object save(FormNameMaster obj, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isValid()) {
			return new Message().respondWithError("Authorization Error");
		}
		try {
			try {
				sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM form_name_master";
				message.map = (Map) da.getRecord(sql).get(0);
				obj.setId(Integer.parseInt(message.map.get("id").toString()));
			} catch (Exception e) {
				return message.respondWithError("connection error or invalid table name");
			}
			row = da.save(obj);
			msg = da.getMsg();
			if (row > 0) {
				return message.respondWithMessage("Success");
			} else if (msg.contains("Duplicate entry")) {
				msg = "This record already exist";
			}
			return message.respondWithError(msg);

		} catch (Exception e) {
			return message.respondWithError(e.getMessage());
		}
	}

	@Override
	public Object update(FormNameMaster obj, long id, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isValid()) {
			return new Message().respondWithError("Authorization Error");
		}
		obj.setId(Integer.parseInt(id + ""));
		row = da.update(obj);
		msg = da.getMsg();
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
	public Object deleteFields(String id, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}
		List<FormFields> l = new ArrayList<FormFields>();
		FormFields obj = new FormFields();
		String[] ids = id.split(",");
		for (String i : ids) {
			try {
				l = da.getAllFields("FROM FormFields WHERE id=" + i);
				obj = l.get(0);
				row = da.deleteFields(obj);
				msg = da.getMsg();
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

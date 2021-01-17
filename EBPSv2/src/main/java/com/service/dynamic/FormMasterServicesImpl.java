package com.service.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.dynamic.DaoFormMaster;
import com.model.dynamic.FormFields;
import com.model.dynamic.FormPermissions;
import com.model.utility.FormNameMaster;

import model.Message;

@Service
public class FormMasterServicesImpl implements FormMasterServices {

	@Autowired
	DaoFormMaster dao;
	Message message = new Message();
	String msg = "", sql;
	int row = 1;

	List list = new ArrayList<>();
	Map map = new HashMap<>();

	@Override
	public Object getAll(String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}
		return dao.getAll("FROM FormNameMaster ORDER BY id");
	}

	@Override
	public Object getById(String Authorization, String formId) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}
		sql = "SELECT name,id FROM form_fields WHERE form_id=" + formId;
		list = dao.getRecord(sql);
		return list;
	}

	@Override
	public Object save(FormNameMaster obj, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}

		List<FormFields> columns = obj.getFormFieldsList();
		sql = "SELECT COALESCE(MAX(ID),0)+1 as id FROM form_fields";
		map = (Map) dao.getRecord(sql).get(0);
		Long columnId = Long.parseLong(map.get("id").toString());
		try {
			for (int i = 0; i < columns.size(); i++) {
				if (String.valueOf(columns.get(i).getId()).length() == 0
						|| String.valueOf(columns.get(i).getId()).equalsIgnoreCase("")
						|| columns.get(i).getId() == null) {
					columns.get(i).setId(columnId);
					columnId++;
				}
			}
		} catch (Exception e) {
			System.out.println("e " + e.getMessage());
		}

		List<FormPermissions> permissions = obj.getFormPermissions();
		sql = "SELECT COALESCE(MAX(ID),0)+1 as id FROM form_permissions";
		map = (Map) dao.getRecord(sql).get(0);
		Long permissionId = Long.parseLong(map.get("id").toString());
		try {
			for (FormPermissions f : permissions) {
				if (String.valueOf(f.getId()).length() == 0 || String.valueOf(f.getId()).equalsIgnoreCase("")
						|| f.getId() == null) {
					f.setId(permissionId);
					permissionId++;
				}
			}
		} catch (Exception e) {
			System.out.println("exc is " + e.getMessage());
		}

		sql = "SELECT COALESCE(MAX(id),0)+1 as id FROM form_name_master";
		try {
			List l = new ArrayList<>();
			if (String.valueOf(obj.getId()).length() == 0 || String.valueOf(obj.getId()).equalsIgnoreCase("")
					|| obj.getId() == null) {
				l = dao.getRecord(sql);
				Map m = new HashMap<>();
				m = (Map) l.get(0);
				obj.setId(Integer.parseInt(m.get("id").toString()));
			}
			obj.setFormFieldsList(columns);
			obj.setFormPermissions(permissions);
			row = dao.save(obj);
			msg = dao.getMsg();
			if (row > 0) {
				return message.respondWithMessage("Success");
			} else if (msg.contains("duplicate")) {
				msg = "This record already exists.";
			}
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return message.respondWithError(msg);
	}

	@Override
	public Object update(FormNameMaster obj, String Authorization, long id) {
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

		String[] ids = id.split(",");
		List<FormNameMaster> l = new ArrayList<FormNameMaster>();
		FormNameMaster obj = new FormNameMaster();
		for (String i : ids) {
			try {
				l = dao.getAll("FROM FormNameMaster WHERE id=" + i);
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
		} else if (msg.contains("foreign")) {
			msg = "Current records are being referenced from other tables.Could not delete.";
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
				l = dao.getAllFields("FROM FormFields WHERE id=" + i);
				obj = l.get(0);
				row = dao.deleteFields(obj);
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
	public Object deleteFormPermissions(String id, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}
		List<FormPermissions> l = new ArrayList<FormPermissions>();
		FormPermissions obj = new FormPermissions();
		String[] ids = id.split(",");
		for (String i : ids) {
			try {
				l = dao.getFormPermission("FROM FormPermissions WHERE id=" + i);
				obj = l.get(0);
				row = dao.deleteFormPermission(obj);
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

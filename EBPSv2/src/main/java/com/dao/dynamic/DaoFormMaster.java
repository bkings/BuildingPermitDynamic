package com.dao.dynamic;

import java.util.List;

import com.model.dynamic.FormFields;
import com.model.dynamic.FormPermissions;
import com.model.utility.FormNameMaster;

public interface DaoFormMaster {

	public List<FormNameMaster> getAll(String hql);

	public List<FormFields> getAllFields(String hql);
	
	public List<FormPermissions> getFormPermission(String hql);

	public int save(FormNameMaster obj);

	public List getRecord(String sql);

	public String getMsg();

	public int update(FormNameMaster obj);

	public int delete(FormNameMaster obj);

	public int deleteFields(FormFields obj);
	
	public int deleteFormPermission(FormPermissions obj);

}

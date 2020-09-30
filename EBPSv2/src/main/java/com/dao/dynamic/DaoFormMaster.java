package com.dao.dynamic;

import java.util.List;

import com.model.utility.FormNameMaster;

public interface DaoFormMaster {

	public List<FormNameMaster> getAll(String hql);

	public int save(FormNameMaster obj);

	public List getRecord(String sql);

	public String getMsg();

	public int update(FormNameMaster obj);
	
	public int delete(FormNameMaster obj);

}

package com.dao.dynamic.general;

import java.util.List;

public interface DaoGeneral {

	public int execute(String sql);
	
	public List getRecords(String sql);
	
	public String getMsg();

}

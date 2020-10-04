package com.dao.dynamic;

import java.util.List;

import com.model.dynamic.EbpsColumns;
import com.model.dynamic.EbpsTables;

public interface DaoEbpsTables {

	public int execute(String sql);
	
	public List<EbpsTables> getAll(String hql);
	
	public List<EbpsColumns> getAllData(String hql);

	public int save(EbpsTables obj);

	public List getRecord(String sql);

	public String getMsg();
	
	public int update(EbpsTables obj);
	
	public int delete(EbpsTables id);
	
	public int deleteColumns(EbpsColumns obj);

}

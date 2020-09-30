package com.dao.dynamic;

import java.util.List;

import com.model.dynamic.EbpsTables;

public interface DaoEbpsTables {

	public List<EbpsTables> getAll(String hql);

	public int save(EbpsTables obj);

	public List getRecord(String sql);

	public String getMsg();
	
	public int update(EbpsTables obj);
	
	public int delete(EbpsTables id);

}

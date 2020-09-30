package com.dao.processing;

import java.util.List;

import com.model.processing.SthalgatNiirikshyanGariPesh;
import com.model.processing.SthalgatNiirikshyanGariPeshDetails;

public interface DaoSthalgatNiirikshyanGariPesh {

	public int save(SthalgatNiirikshyanGariPesh obj);

	public int save(SthalgatNiirikshyanGariPeshDetails obj);

	public List<SthalgatNiirikshyanGariPesh> getAll(String hql);

	public String getMsg();

}

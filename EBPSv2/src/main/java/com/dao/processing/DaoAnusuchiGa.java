package com.dao.processing;

import java.util.List;

import com.model.processing.AnusuchiGa;

public interface DaoAnusuchiGa {

public int save(AnusuchiGa obj);

public List<AnusuchiGa> getAll(String hql);

public int update(AnusuchiGa obj);

public int delete(AnusuchiGa obj);

public int delete(String sql);

public List getRecord(String sql);

public String getMsg();
}

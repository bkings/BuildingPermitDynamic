package com.dao.processing;

import java.util.List;

import com.model.processing.AnusuchiGha;

public interface DaoAnusuchiGha {

    public int save(AnusuchiGha obj);

    public int update(AnusuchiGha obj);

    public int delete(AnusuchiGha obj);

    public int delete(String sql);

    public List<AnusuchiGha> getAll(String hql);

    public List getRecord(String sql);

    public String getMsg();
}

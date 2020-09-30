package com.dao.processing;

import java.util.List;

import com.model.processing.Waresnama;

public interface DaoWaresnama {

    public List<Waresnama> getAll(String hql);

    public int save(Waresnama obj);

    public List getRecord(String sql);

    public String getMsg();
}

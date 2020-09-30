package com.dao.processing;

import java.util.List;

import com.model.processing.Kabuliyatnama;

public interface DaoKabuliyatnama {

    public List<Kabuliyatnama> getAll(String hql);

    public int save(Kabuliyatnama obj);

    public List getRecord(String sql);

    public String getMsg();
}

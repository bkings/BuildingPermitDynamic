package com.dao.processing;

import java.util.List;

import com.model.processing.AnusuchiKa;

public interface DaoAnusuchiKa {

    public int save(AnusuchiKa obj);

    public List getAll(String hql);

    public String getMsg();
}

package com.dao.processing;

import java.util.List;

import com.model.processing.StructuralDesignerManjuriPatra;

public interface DaoStructuralDesignerManjuriPatra {

    public int save(StructuralDesignerManjuriPatra obj);

    public int update(StructuralDesignerManjuriPatra obj);

    public int delete(StructuralDesignerManjuriPatra obj);

    public int delete(String sql);

    public List<StructuralDesignerManjuriPatra> getAll(String hql);

    public List getRecord(String sql);

    public String getMsg();
}

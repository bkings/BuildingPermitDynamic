package com.dao.processing;

import java.util.List;

import com.model.processing.ArchitectureDesignerManjuriPatra;

public interface DaoArchitectureDesignerManjuriPatra {

    public int save(ArchitectureDesignerManjuriPatra obj);

    public int update(ArchitectureDesignerManjuriPatra obj);

    public int delete(ArchitectureDesignerManjuriPatra obj);

    public int delete(String sql);

    public List<ArchitectureDesignerManjuriPatra> getAll(String hql);

    public List getRecord(String sql);

    public String getMsg();
}

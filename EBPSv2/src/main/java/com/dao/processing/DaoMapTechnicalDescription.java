package com.dao.processing;

import java.util.List;

import com.model.processing.MapTechnicalDescription;

public interface DaoMapTechnicalDescription {

    public int save(MapTechnicalDescription obj);

    public int update(MapTechnicalDescription obj);

    public int delete(MapTechnicalDescription obj);

    public int delete(String sql);

    public List<MapTechnicalDescription> getAll(String hql);

    public List getRecord(String sql);

    public String getMsg();
}

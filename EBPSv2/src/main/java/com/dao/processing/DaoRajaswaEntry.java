package com.dao.processing;


import java.util.List;

import com.model.processing.RajaswaEntry;

public interface DaoRajaswaEntry {

public List<RajaswaEntry> getAll(String hql);

public int save(RajaswaEntry obj);


public int update(RajaswaEntry obj);

public List getRecord(String sql);

public String getMsg();
}

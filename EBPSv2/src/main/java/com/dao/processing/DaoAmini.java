package com.dao.processing;

import java.util.List;

import com.model.processing.AminiInquiry;
import com.model.processing.AminiKabuliyatnama;
import com.model.processing.GharCompoundWall;

public interface DaoAmini {

public List<AminiInquiry> getAll(String hql);

public int save(AminiInquiry obj);

public int save(GharCompoundWall obj);

public int save(AminiKabuliyatnama obj);

public List getRecord(String sql);

public String getMsg();
}

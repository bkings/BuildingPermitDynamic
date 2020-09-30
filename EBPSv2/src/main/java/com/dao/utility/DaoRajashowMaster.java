package com.dao.utility;

import java.util.List;

import com.model.utility.RajashowMaster;

public interface DaoRajashowMaster {

    public List<RajashowMaster> getAll(String hql);

    public int save(RajashowMaster obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

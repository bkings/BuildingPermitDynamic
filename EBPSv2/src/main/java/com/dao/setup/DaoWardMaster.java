
package com.dao.setup;

import java.util.List;

import com.model.setup.WardMaster;

public interface DaoWardMaster {

    public List<WardMaster> getAll(String hql);

    public int save(WardMaster obj);

    public int update(WardMaster obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

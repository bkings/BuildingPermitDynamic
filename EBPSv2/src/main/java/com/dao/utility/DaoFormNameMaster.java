
package com.dao.utility;

import java.util.List;

import com.model.utility.FormNameMaster;

public interface DaoFormNameMaster {

    public List<FormNameMaster> getAll(String hql);

    public int save(FormNameMaster obj);

    public int update(FormNameMaster obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

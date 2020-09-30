
package com.dao.utility;

import java.util.List;

import com.model.utility.FormGroupMaster;

public interface DaoFormGroupMaster {

    public List<FormGroupMaster> getAll(String hql);

    public int save(FormGroupMaster obj);

    public int update(FormGroupMaster obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}


package com.dao.utility;

import java.util.List;

import com.model.utility.ApplicationForwardingSetup;

public interface DaoApplicationForwardingSetup {

    public List<ApplicationForwardingSetup> getAll(String hql);

    public int save(ApplicationForwardingSetup obj);

    public int update(ApplicationForwardingSetup obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

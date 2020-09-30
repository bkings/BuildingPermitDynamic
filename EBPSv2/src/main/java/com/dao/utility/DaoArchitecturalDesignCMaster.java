
package com.dao.utility;

import java.util.List;

import com.model.utility.ArchitecturalDesignCMaster;

public interface DaoArchitecturalDesignCMaster {

    public List<ArchitecturalDesignCMaster> getAll(String hql);

    public int save(ArchitecturalDesignCMaster obj);

    public int update(ArchitecturalDesignCMaster obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

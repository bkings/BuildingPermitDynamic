
package com.dao.utility;

import java.util.List;

import com.model.utility.ArchitecturalDesignBMaster;

public interface DaoArchitecturalDesignBMaster {

    public List<ArchitecturalDesignBMaster> getAll(String hql);

    public int save(ArchitecturalDesignBMaster obj);

    public int update(ArchitecturalDesignBMaster obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

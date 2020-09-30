
package com.dao.utility;

import java.util.List;

import com.model.utility.ElectricalDesignRequirementMaster;

public interface DaoElectricalDesignRequirementMaster {

    public List<ElectricalDesignRequirementMaster> getAll(String hql);

    public int save(ElectricalDesignRequirementMaster obj);

    public int update(ElectricalDesignRequirementMaster obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

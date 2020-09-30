
package com.dao.utility;

import java.util.List;

import com.model.utility.UnitConversion;

public interface DaoUnitConversion {

    public List<UnitConversion> getAll(String hql);

    public int save(UnitConversion obj);

    public int update(UnitConversion obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

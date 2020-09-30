
package com.dao.utility;

import java.util.List;

import com.model.utility.MapCheckReportMaster;

public interface DaoMapCheckReportMaster {

    public List<MapCheckReportMaster> getAll(String hql);

    public int save(MapCheckReportMaster obj);

    public int update(MapCheckReportMaster obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

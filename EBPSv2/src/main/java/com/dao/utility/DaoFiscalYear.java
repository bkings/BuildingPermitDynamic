
package com.dao.utility;

import java.util.List;

import com.model.utility.FiscalYear;

public interface DaoFiscalYear {

    public List<FiscalYear> getAll(String hql);

    public int save(FiscalYear obj);

    public int update(FiscalYear obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

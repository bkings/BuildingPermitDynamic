
package com.dao.processing;

import java.util.List;

import com.model.processing.RajaswaVoucher;

public interface DaoRajaswaVoucher {

    public List<RajaswaVoucher> getAll(String hql);

    public int save(RajaswaVoucher obj);

    public int update(RajaswaVoucher obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

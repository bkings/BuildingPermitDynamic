
package com.dao.processing;

import java.util.List;

import com.model.processing.AminKoSthalgatPratibedan;

public interface DaoAminKoSthalgatPratibedan {

    public List<AminKoSthalgatPratibedan> getAll(String hql);

    public int save(AminKoSthalgatPratibedan obj);

    public int update(AminKoSthalgatPratibedan obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

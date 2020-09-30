
package com.dao.processing;

import java.util.List;

import com.model.processing.NoObjectionSheet;

public interface DaoNoObjectionSheet {

    public List<NoObjectionSheet> getAll(String hql);

    public int save(NoObjectionSheet obj);

    public int update(NoObjectionSheet obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

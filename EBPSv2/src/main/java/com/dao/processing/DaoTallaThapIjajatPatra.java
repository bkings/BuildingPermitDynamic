
package com.dao.processing;

import java.util.List;

import com.model.processing.TallaThapIjajatPatra;

public interface DaoTallaThapIjajatPatra {

    public List<TallaThapIjajatPatra> getAll(String hql);

    public int save(TallaThapIjajatPatra obj);

    public int update(TallaThapIjajatPatra obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

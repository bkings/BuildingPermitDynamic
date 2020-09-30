
package com.dao.processing;

import java.util.List;

import com.model.processing.TallaThapIjajatTippaniades;

public interface DaoTallaThapIjajatTippaniades {

    public List<TallaThapIjajatTippaniades> getAll(String hql);

    public int save(TallaThapIjajatTippaniades obj);

    public int update(TallaThapIjajatTippaniades obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

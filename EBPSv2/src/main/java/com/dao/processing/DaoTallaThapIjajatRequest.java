
package com.dao.processing;

import java.util.List;

import com.model.processing.TallaThapIjajatRequest;

public interface DaoTallaThapIjajatRequest {

    public List<TallaThapIjajatRequest> getAll(String hql);

    public int save(TallaThapIjajatRequest obj);

    public int update(TallaThapIjajatRequest obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

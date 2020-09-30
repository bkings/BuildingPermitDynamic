package com.dao.utility;

import java.util.List;

import com.model.utility.MenuUserAccess;

public interface DaoMenuUserAccess {

    public List<MenuUserAccess> getAll(String hql);

    public int save(MenuUserAccess obj);

    public List getRecord(String sql);

    public String getMsg();
}

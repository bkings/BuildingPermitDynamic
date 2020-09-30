
package com.dao.utility;

import java.util.List;

import com.model.utility.MenuMaster;

public interface DaoMenuMaster {

    public List<MenuMaster> getAll(String hql);

    public int save(MenuMaster obj);

    public int update(MenuMaster obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

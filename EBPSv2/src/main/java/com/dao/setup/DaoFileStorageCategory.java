
package com.dao.setup;

import java.util.List;

import com.model.utility.FileStorageCategory;

public interface DaoFileStorageCategory {

    public List<FileStorageCategory> getAll(String hql);

    public int save(FileStorageCategory obj);

    public int update(FileStorageCategory obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}

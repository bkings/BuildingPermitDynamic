package com.service.utility;

import com.model.utility.FileStorageCategory ;

public interface FileStorageCategoryService {

    public Object getAll();

    public Object save(FileStorageCategory obj, String Authorization);

    public Object update(FileStorageCategory obj,long id, String Authorization);

    public Object delete(String id,String Authorization);

}
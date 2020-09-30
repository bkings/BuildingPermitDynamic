package com.service.utility;

import com.model.utility.MenuMaster ;

public interface MenuMasterService {

    public Object getAll();

    public Object save(MenuMaster obj, String Authorization);

    public Object update(MenuMaster obj,long id, String Authorization);

    public Object delete(String id,String Authorization);

}
package com.service.utility;

import java.util.List;

import com.model.utility.MenuUserAccess;

public interface MenuUserAccessService {

    public Object getAll(String userType);

    public Object save(List<MenuUserAccess> list, String Authorization);

}

package com.dao.utility;

import java.util.List;

import com.model.utility.UserTypeMaster;

public interface DaoUserTypeMaster {

    public List<UserTypeMaster> getAll(String hql);

    public int save(UserTypeMaster obj);

    public String getMsg();
}

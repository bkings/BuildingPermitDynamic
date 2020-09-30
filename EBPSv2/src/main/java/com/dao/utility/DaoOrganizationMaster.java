package com.dao.utility;

import java.util.List;

import com.model.utility.OrganizationMaster;
import com.model.utility.OrganizationUser;
import com.model.utility.UserTypeMaster;

public interface DaoOrganizationMaster {

public int save(OrganizationMaster obj);

public int update(OrganizationMaster obj);

public int save(OrganizationUser obj);

public int save(UserTypeMaster obj);

public List<OrganizationMaster> getAll(String hql);

public String getMsg();
}

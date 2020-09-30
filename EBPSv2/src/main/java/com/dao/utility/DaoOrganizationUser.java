package com.dao.utility;

import java.util.List;

import com.model.utility.DesignerRenewStatus;
import com.model.utility.OrganizationUser;
import com.model.utility.OrganizationUserRequest;

public interface DaoOrganizationUser {

    public List getAll(String hql);

    public int save(OrganizationUser obj);

    public int save(DesignerRenewStatus obj);

    public int update(OrganizationUser obj);

    public int save(OrganizationUserRequest obj);

    public int update(OrganizationUserRequest obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();

//    public Object getAllRenew(String from_DesignerRenewStatus);
}

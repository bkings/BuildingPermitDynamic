package com.dao.application;

import java.util.List;

import com.model.application.ApplicationAction;
import com.model.application.ApplicationsComments;
import com.model.application.BuildingMemberDetails;
import com.model.application.BuildingPermitApplication;
import com.model.application.BuildingPermitApplicationNameTransafer;
import com.model.application.BuildingPermitFloor;
import com.model.application.BuildingPermitSurrounding;

public interface DaoBuildingPermitApplication {

    public int save(BuildingPermitApplication obj);

    public int save(BuildingPermitApplicationNameTransafer obj);

    public int save(ApplicationAction obj);

    public int delete(BuildingPermitSurrounding obj);

    public int delete(BuildingMemberDetails obj);

    public int delete(BuildingPermitFloor obj);

    public int delete(String sql);

    public List<BuildingPermitApplication> getAll(String hql);

    public List<ApplicationsComments> getApplicationsComments(String hql);

    public List getRecord(String sql);

    public String getMsg();

    public int save(BuildingPermitSurrounding obj);

    public int save(BuildingMemberDetails obj);

    public int save(BuildingPermitFloor obj);
}

package com.service.utility;

import com.model.utility.OrganizationMaster;

public interface OrganizationMasterService {

public Object applicationConfiguration();

public Object getRecord();

public Object Save(OrganizationMaster obj, String Authorization);

public Object update(OrganizationMaster obj, String Authorization);
}

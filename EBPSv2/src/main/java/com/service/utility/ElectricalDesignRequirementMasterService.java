package com.service.utility;

import com.model.utility.ElectricalDesignRequirementMaster;

public interface ElectricalDesignRequirementMasterService {

public Object getAll();

public Object save(ElectricalDesignRequirementMaster obj, String Authorization);

public Object update(ElectricalDesignRequirementMaster obj, String Authorization);

public Object delete(String id, String Authorization);

}

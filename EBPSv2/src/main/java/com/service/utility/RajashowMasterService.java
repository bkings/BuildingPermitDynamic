package com.service.utility;

import java.util.List;

import com.model.utility.RajashowMaster;

public interface RajashowMasterService {

    public Object getAll(String wardNo);

    public Object save(List<RajashowMaster> obj, String Authorization);

    public Object delete(String id, String Authorization);

}

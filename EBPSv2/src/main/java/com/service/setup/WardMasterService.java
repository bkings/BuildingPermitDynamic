package com.service.setup;

import com.model.setup.WardMaster ;

public interface WardMasterService {

    public Object getAll();

    public Object save(WardMaster obj, String Authorization);

    public Object update(WardMaster obj,long id, String Authorization);

    public Object delete(String id,String Authorization);

}
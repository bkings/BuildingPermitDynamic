package com.service.utility;

import com.model.utility.FormGroupMaster ;

public interface FormGroupMasterService {

    public Object getAll();

    public Object save(FormGroupMaster obj, String Authorization);

    public Object update(FormGroupMaster obj,long id, String Authorization);

    public Object delete(String id,String Authorization);

}
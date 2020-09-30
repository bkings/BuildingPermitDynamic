package com.service.utility;

import com.model.utility.FormNameMaster ;

public interface FormNameMasterService {

    public Object getAll();

    public Object save(FormNameMaster obj, String Authorization);

    public Object update(FormNameMaster obj,long id, String Authorization);

   

}
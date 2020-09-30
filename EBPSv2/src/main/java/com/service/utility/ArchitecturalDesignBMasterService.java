package com.service.utility;

import com.model.utility.ArchitecturalDesignBMaster ;

public interface ArchitecturalDesignBMasterService {

    public Object getAll();

    public Object save(ArchitecturalDesignBMaster obj, String Authorization);

    public Object update(ArchitecturalDesignBMaster obj,long id, String Authorization);

    public Object delete(String id,String Authorization);

}
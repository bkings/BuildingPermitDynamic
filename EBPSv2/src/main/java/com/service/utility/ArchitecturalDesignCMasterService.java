package com.service.utility;

import com.model.utility.ArchitecturalDesignCMaster ;

public interface ArchitecturalDesignCMasterService {

    public Object getAll();

    public Object save(ArchitecturalDesignCMaster obj, String Authorization);

    public Object update(ArchitecturalDesignCMaster obj,long id, String Authorization);

    public Object delete(String id,String Authorization);

}
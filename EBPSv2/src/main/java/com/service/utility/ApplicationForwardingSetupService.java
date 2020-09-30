package com.service.utility;

import com.model.utility.ApplicationForwardingSetup ;

public interface ApplicationForwardingSetupService {

    public Object getAll( String Authorization,String forwardBy,Long formGroup);

    public Object save(ApplicationForwardingSetup obj, String Authorization);

    public Object update(ApplicationForwardingSetup obj,long id, String Authorization);

    public Object delete(String id,String Authorization);

}
package com.service.utility;

import com.model.utility.FormGroup ;
public interface FormGroupService {

    public Object getAll( String id);

    public Object save(FormGroup obj, String Authorization);

    public Object update(FormGroup obj,String id, String Authorization);

    public Object delete(String id,String Authorization);

}
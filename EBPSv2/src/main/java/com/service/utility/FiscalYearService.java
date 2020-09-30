package com.service.utility;

import com.model.utility.FiscalYear ;

public interface FiscalYearService {

    public Object getAll();

    public Object save(FiscalYear obj, String Authorization);

    public Object update(FiscalYear obj,long id, String Authorization);

    public Object delete(String id,String Authorization);

}
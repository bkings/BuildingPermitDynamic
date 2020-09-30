package com.service.utility;

import com.model.utility.UnitConversion ;


public interface UnitConversionService {

    public Object getAll();

    public Object save(UnitConversion obj, String Authorization);

    public Object update(UnitConversion obj,int id, String Authorization);

    public Object delete(String id,String Authorization);

}
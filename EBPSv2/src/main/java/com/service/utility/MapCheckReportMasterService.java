package com.service.utility;

import com.model.utility.MapCheckReportMaster ;

public interface MapCheckReportMasterService {

    public Object getAll();

    public Object save(MapCheckReportMaster obj, String Authorization);

    public Object update(MapCheckReportMaster obj,long id, String Authorization);

    public Object delete(String id,String Authorization);

}
package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.MapTechnicalDescription;

public interface MapTechnicalDescriptionService {

public Object getindex(String id);

public Object buildingDetails(String unit, String id);

public Object save(MapTechnicalDescription obj, String Authorization);

public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);
}

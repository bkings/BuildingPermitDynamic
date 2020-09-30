package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.HighRisedBuildings;

public interface HighRisedBuildingsService {

	public Object getAll(long id);

	public Object save(HighRisedBuildings obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);

}

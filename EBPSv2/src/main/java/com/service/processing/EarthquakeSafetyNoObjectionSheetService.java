package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.EarthquakeSafetyNoObjectionSheet;

public interface EarthquakeSafetyNoObjectionSheetService {

	public Object getAll(long id);

	public Object save(EarthquakeSafetyNoObjectionSheet obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);
	
}

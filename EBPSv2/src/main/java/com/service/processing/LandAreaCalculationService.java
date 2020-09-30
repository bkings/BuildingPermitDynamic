package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.LandAreaCalculation;

public interface LandAreaCalculationService {
	
	public Object getAll(long id);

	public Object save(LandAreaCalculation obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);
}

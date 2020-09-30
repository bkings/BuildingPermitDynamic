package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.PrathamCharanKaamGarneAnumati;

public interface PrathamCharanKaamGarneAnumatiService {

	public Object getAll(long id);

	public Object save(PrathamCharanKaamGarneAnumati obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);
	
}

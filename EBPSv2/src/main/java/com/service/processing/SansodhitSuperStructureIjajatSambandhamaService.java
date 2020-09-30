package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.SansodhitSuperStructureIjajatSambandhama;

public interface SansodhitSuperStructureIjajatSambandhamaService {

	public Object getAll(long id);
	
	public Object save(SansodhitSuperStructureIjajatSambandhama obj,String Authorization);
	
	public Object doApprove(long applicationNo,ApplicationApproved approved,String Authorization);
	
}

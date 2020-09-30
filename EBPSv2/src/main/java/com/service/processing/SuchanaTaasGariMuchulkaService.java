package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.SuchanaTaasGariMuchulka;

public interface SuchanaTaasGariMuchulkaService {
	
	public Object getAll(long id);

	public Object save(SuchanaTaasGariMuchulka obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);

}

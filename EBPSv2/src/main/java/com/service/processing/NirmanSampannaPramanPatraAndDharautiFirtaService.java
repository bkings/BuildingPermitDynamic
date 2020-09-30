package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.NirmanSampannaPramanPatraAndDharautiFirta;

public interface NirmanSampannaPramanPatraAndDharautiFirtaService {

	public Object getAll(long id);

	public Object save(NirmanSampannaPramanPatraAndDharautiFirta obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);
	
}

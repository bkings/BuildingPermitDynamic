package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.SuperStructureSupervisionReport;

public interface SuperStructureSupervisionReportService {

	public Object getAll(long id);
	
	public Object save(SuperStructureSupervisionReport obj,String Authorization);
	
	public Object doApprove(long applicationNo,ApplicationApproved approved,String Authorization);
	
}

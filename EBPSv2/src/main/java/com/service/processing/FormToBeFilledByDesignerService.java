package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.FormToBeFilledByDesigner;

public interface FormToBeFilledByDesignerService {

	public Object getAll(long id);

	public Object save(FormToBeFilledByDesigner obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);
	
}

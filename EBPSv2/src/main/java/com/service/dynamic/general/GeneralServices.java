package com.service.dynamic.general;

import com.model.application.ApplicationApproved;

public interface GeneralServices {

	public Object getAll(Long applicationNo, String Authorization, String formId);

	public Object save(Object obj, Long applicationNo, String formId, String Authorization);

	public Object approve(Long applicationNo, ApplicationApproved obj, String Authorization, String formId);

}

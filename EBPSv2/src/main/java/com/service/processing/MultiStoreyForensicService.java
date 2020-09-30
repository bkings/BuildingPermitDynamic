package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.MultiStoreyForensic;

public interface MultiStoreyForensicService {

	public Object getAll(long id);

	public Object save(MultiStoreyForensic obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);

}

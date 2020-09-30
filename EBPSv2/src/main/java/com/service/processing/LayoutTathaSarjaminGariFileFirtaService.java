package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.LayoutTathaSarjaminGariFileFirta;

public interface LayoutTathaSarjaminGariFileFirtaService {

	public Object getAll(long id);

	public Object save(LayoutTathaSarjaminGariFileFirta obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);

}

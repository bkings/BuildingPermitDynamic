package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.KaryalayaPrayojan15DaysNotice;

public interface KaryalayaPrayojan15DaysNoticeService {

	public Object getAll(long id);

	public Object save(KaryalayaPrayojan15DaysNotice obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);
	
}

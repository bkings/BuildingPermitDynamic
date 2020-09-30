package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.PratibeydanPeshGarekoBare;

public interface PratibeydanPeshGarekoBareService {

	public Object getAll(long id);

	public Object save(PratibeydanPeshGarekoBare obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);

}

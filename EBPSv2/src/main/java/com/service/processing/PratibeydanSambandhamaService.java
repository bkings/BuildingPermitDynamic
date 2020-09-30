package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.PratibeydanSambandhama;

public interface PratibeydanSambandhamaService {

	public Object getAll(long id);

	public Object save(PratibeydanSambandhama obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);

}

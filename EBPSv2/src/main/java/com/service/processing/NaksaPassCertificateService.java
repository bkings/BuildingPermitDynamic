package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.NaksaPassCertificate;

public interface NaksaPassCertificateService {

	public Object getAll(long id);

	public Object save(NaksaPassCertificate obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);

}

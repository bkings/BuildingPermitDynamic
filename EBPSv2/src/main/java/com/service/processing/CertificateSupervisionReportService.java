package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.CertificateSupervisionReport;

public interface CertificateSupervisionReportService {

	public Object getAll(long id);

	public Object save(CertificateSupervisionReport obj, String Authorization);

	public Object doApprove(long applicationNo, ApplicationApproved approved, String Authorization);

}

package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.CertificateNote;


public interface CertificateNoteService {

    public Object getAll(Long applicationNo);

    public Object save(CertificateNote obj, String Authorization);

    public Object doApprove(Long applicationNo, ApplicationApproved approved, String Authorization);
    
}
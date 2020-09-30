package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.NamsariTippaniAdes;


public interface NamsariTippaniAdesService {

    public Object getAll(Long applicationNo);

    public Object doApprove(Long applicationNo, ApplicationApproved approved, String Authorization);

    public Object save(NamsariTippaniAdes obj, String Authorization);
    
}

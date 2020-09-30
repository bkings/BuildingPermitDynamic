package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.ArchitectureDesignerManjuriPatra;

public interface ArchitectureDesignerManjuriPatraService {

public Object getAll(String Authorization, String applicationNo);

public Object save(ArchitectureDesignerManjuriPatra obj, String Authorization);

public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}

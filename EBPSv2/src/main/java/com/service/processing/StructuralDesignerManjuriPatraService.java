package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.StructuralDesignerManjuriPatra;

public interface StructuralDesignerManjuriPatraService {

public Object getAll(String Authorization, String applicationNo);

public Object save(StructuralDesignerManjuriPatra obj, String Authorization);

public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}

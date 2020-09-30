package com.service.processing;

import com.model.application.ApplicationApproved;

public interface ArchitecturalDesignBService {

public Object getindex(String applicationNo);

public Object save(long applicationNo, String jsonData, String Authorization);

public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);
}

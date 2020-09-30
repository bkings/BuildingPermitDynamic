package com.service.processing;

import com.model.application.ApplicationApproved;

public interface StructureDesignBService {

public Object index(Long applicationNo);

public Object save(Long applicationNo, String jsonData, String Authorization);

public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);
}

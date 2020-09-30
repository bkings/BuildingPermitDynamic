package com.service.processing;

import com.model.application.ApplicationApproved;

public interface AnusuchiGaService {

public Object getIndex(String Authorization, String applicationNo);

public Object save(long applicationNo, String Authorization);

public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}

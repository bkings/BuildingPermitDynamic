package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.RajaswaEntry;

public interface RajaswaEntryService {

    public Object getAll(long id);

    public Object save(RajaswaEntry obj, String Authorization);

    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}

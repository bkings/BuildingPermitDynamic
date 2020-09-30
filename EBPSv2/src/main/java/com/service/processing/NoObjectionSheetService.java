package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.NoObjectionSheet;

public interface NoObjectionSheetService {

    public Object getAll(String Authorization, long id);

    public Object save(NoObjectionSheet obj, String Authorization);

    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}

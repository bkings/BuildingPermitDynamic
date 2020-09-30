package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.AnusuchiKa;

public interface AnusuchiKaService {

    public Object getData(String Authorization, String applicationNo);

    public Object Save(AnusuchiKa obj, String Authorization);

    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}

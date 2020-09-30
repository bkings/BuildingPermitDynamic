package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.NamsariBillVuktani;

public interface NamsariBillVuktaniService {

    public Object getAll(Long applicationNo);

    public Object doApprove(Long applicationNo, ApplicationApproved approved, String Authorization);

    public Object save(NamsariBillVuktani obj, String Authorization);

}

package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.Waresnama;

public interface WaresnamaService {

    public Object getAll(long applicationNo);

    public Object save(Waresnama obj, String Authorization);

    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);
}

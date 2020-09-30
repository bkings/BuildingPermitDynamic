package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.RajaswaVoucher;

public interface RajaswaVoucherService {

    public Object getAll(long id);

    public Object save(RajaswaVoucher obj, String Authorization);

    public Object update(Long applicationNo, ApplicationApproved obj, String Authorization);

}

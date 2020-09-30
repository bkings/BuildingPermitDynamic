package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.AminKoSthalgatPratibedan ;


public interface AminKoSthalgatPratibedanService {

    public Object getAll(String Authorization,long id);

    public Object save(AminKoSthalgatPratibedan obj, String Authorization);

     public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}
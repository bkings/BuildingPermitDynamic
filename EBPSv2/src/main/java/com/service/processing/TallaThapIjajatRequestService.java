package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.TallaThapIjajatRequest ;


public interface TallaThapIjajatRequestService {

    public Object getAll(String Authorization,long id);

    public Object save(TallaThapIjajatRequest obj, String Authorization);

         public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}
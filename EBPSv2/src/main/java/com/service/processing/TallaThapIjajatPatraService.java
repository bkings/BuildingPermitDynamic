package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.TallaThapIjajatPatra ;

public interface TallaThapIjajatPatraService {

    public Object getAll(String Authorization,long id);

    public Object save(TallaThapIjajatPatra obj, String Authorization);

       public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}
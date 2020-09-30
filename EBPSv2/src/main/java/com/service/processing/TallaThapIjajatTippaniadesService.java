package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.TallaThapIjajatTippaniades;

public interface TallaThapIjajatTippaniadesService {

    public Object getAll(String Authorization, long id);

    public Object save(TallaThapIjajatTippaniades obj, String Authorization);

    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}

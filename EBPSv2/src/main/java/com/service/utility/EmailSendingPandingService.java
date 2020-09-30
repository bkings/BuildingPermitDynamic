package com.service.utility;

import com.model.utility.EmailSendingPanding;

public interface EmailSendingPandingService {

    public Object getAll();

    public Object save(EmailSendingPanding obj, String Authorization);

    public Object update(String jsonData, String Authorization);
}

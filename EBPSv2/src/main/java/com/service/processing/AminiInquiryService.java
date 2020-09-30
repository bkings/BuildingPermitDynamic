package com.service.processing;

import com.model.processing.AminiInquiry;

public interface AminiInquiryService {

public Object getAll(long id);

public Object save(AminiInquiry obj, String Authorization);

public Object update(AminiInquiry obj, long id, String Authorization);



}

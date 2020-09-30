
package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.GharCompoundwallkoNaap;


public interface GharCompoundwallkoNaapService {
    
    public Object getAll(long applicationNo);

    public Object save(GharCompoundwallkoNaap obj, String Authorization);
    
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}

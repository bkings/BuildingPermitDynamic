/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.PuranoGharSampannaPramanPatra;

public interface PuranoGharSampannaPramanPatraService {

    public Object getAll(Long applicationNo);

    public Object save(PuranoGharSampannaPramanPatra obj, String Authorization);

    public Object doApprove(Long applicationNo, ApplicationApproved approved, String Authorization);
    
}

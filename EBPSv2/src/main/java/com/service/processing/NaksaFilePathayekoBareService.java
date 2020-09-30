/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.NaksaFilePathayekoBare;

public interface NaksaFilePathayekoBareService {

    public Object getAll(String Authorization, long id);

    public Object save(NaksaFilePathayekoBare obj, String Authorization);

    public Object doApprove(Long applicationNo, ApplicationApproved approved, String Authorization);
    
}

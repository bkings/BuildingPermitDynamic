/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import com.model.utility.OtherSetBackMaster;

public interface OtherSetBackMasterService {

    public Object getAll();

    public Object save(OtherSetBackMaster obj, String Authorization);

    public Object update(OtherSetBackMaster obj, long id, String Authorization);

    public Object delete(String id, String Authorization);
    
}

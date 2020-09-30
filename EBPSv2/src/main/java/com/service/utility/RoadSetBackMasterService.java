/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import com.model.utility.RoadSetBackMaster;

public interface RoadSetBackMasterService {

    public Object getAll();

    public Object save(RoadSetBackMaster obj, String Authorization);

    public Object update(RoadSetBackMaster obj, long id, String Authorization);

    public Object delete(String id, String Authorization);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import com.model.utility.CheckClassGroup;

public interface CheckGroupClassService {

    public Object getAll(String Authorization);

    public Object save(String obj, String Authorization);

    public Object update(CheckClassGroup obj, long id, String Authorization);

    public Object delete(String id, String Authorization);
    
}

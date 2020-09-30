/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.utility;

import java.util.List;

import com.model.utility.ChangeDesigner;

public interface DaoChangeDesigner {

    int save(ChangeDesigner obj);

    public List getAll(String hql);
    
}

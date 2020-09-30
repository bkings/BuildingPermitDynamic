/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.application;

import java.util.List;

import com.model.application.Contacts;

public interface DaoContacts {

    public Object getAll(String sql);

    public List getById(String string);

    public int save(Contacts obj);
    
}

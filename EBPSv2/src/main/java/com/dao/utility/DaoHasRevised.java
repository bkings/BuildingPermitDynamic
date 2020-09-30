/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.utility;

import java.util.List;

import com.model.utility.HasRevised;

public interface DaoHasRevised {

    public Object getAll(String hql);

    public List getRecord(String sql);

    public int save(HasRevised obj);

    public String getMsg();

    public int update(HasRevised obj);

    public int delete(String sql);
    
}

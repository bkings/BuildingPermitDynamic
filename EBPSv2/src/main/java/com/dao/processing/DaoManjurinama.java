/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.processing;

import java.util.List;

import com.model.processing.Manjurinama;

public interface DaoManjurinama {

    public List<Manjurinama> getAll(String hql);

    public int save(Manjurinama obj);

    public List getRecord(String sql);

    public String getMsg();
}

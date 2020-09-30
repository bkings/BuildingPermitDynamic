/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.processing;

import java.util.List;

import com.model.processing.LayoutGariPratibedanIjajat;

public interface DaoLayoutGariPratibedanIjajat {

     public List<LayoutGariPratibedanIjajat> getAll(String hql);

    public int save(LayoutGariPratibedanIjajat obj);

    public int update(LayoutGariPratibedanIjajat obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
    
}

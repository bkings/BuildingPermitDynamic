/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.utility;

import java.util.List;

import com.model.utility.CheckClassGroup;

public interface DaoCheckGroupClass {

    public List<CheckClassGroup> getAll(String hql);

    public int delete(String sql);

    public String getMsg();

    public int update(CheckClassGroup obj);

    public List getRecord(String sql);

    public int save(CheckClassGroup obj);

}

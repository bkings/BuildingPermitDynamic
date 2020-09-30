/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.utility;

import java.util.List;

import com.model.utility.MasonList;

public interface DaoMasonList {

    public List<MasonList> getAll(String from_MasonList);

    public List getRecord(String sql);

    public int save(MasonList obj);

    public String getMsg();

    public int update(MasonList obj);

    public int delete(String sql);
    
}

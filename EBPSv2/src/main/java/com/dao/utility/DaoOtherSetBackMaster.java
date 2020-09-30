/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.utility;

import java.util.List;

import com.model.utility.OtherSetBackMaster;

public interface DaoOtherSetBackMaster {

   List<OtherSetBackMaster> getAll(String hql);

   List<OtherSetBackMaster> getRecord(String sql);

   int save(OtherSetBackMaster obj);

    String getMsg();

    int update(OtherSetBackMaster obj);

    int delete(String sql);
    
}

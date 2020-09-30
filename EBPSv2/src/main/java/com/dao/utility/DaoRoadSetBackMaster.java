/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.utility;

import java.util.List;

import com.model.utility.RoadSetBackMaster;

public interface DaoRoadSetBackMaster {

    List<RoadSetBackMaster> getAll(String hql);

     List<RoadSetBackMaster> getRecord(String sql);

    int save(RoadSetBackMaster obj);

    String getMsg();

    int update(RoadSetBackMaster obj);

    int delete(String sql);
    
}

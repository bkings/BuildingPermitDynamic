/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.processing;

import java.util.List;

import com.model.processing.ElectricalDesign;
import com.model.processing.ElectricalDesignDetails;

/**
 *
 * @author kamal
 */
public interface DaoElectricalDesign {

public int save(ElectricalDesign obj);

public int save(ElectricalDesignDetails obj);

public List getAll(String hql);

public String getMsg();
}

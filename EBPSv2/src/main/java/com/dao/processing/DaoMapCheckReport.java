/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.processing;

import java.util.List;

import com.model.processing.MapCheckReport;
import com.model.processing.MapCheckReportDetails;

/**
 *
 * @author Crazziee
 */
public interface DaoMapCheckReport {

    public int save(MapCheckReport obj);

    public int save(MapCheckReportDetails obj);

    public List<MapCheckReport> getAll(String hql);

    public String getMsg();
}

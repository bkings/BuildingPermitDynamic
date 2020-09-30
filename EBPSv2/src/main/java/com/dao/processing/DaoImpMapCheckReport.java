/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.processing;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.processing.MapCheckReport;
import com.model.processing.MapCheckReportDetails;

@Component
public class DaoImpMapCheckReport implements DaoMapCheckReport {

    String msg = "";
    int row = 1;

    @Override
    public int save(MapCheckReport obj) {
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        msg = "";
        row = 1;
        try {
            session.saveOrUpdate(obj);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            msg = model.Message.exceptionMsg(e);
            row = 0;
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return row;
    }

    @Override
    public int save(MapCheckReportDetails obj) {
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        msg = "";
        row = 1;
        try {
            session.saveOrUpdate(obj);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            msg = model.Message.exceptionMsg(e);
            row = 0;
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return row;
    }

    @Override
    public List<MapCheckReport> getAll(String hql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        List<MapCheckReport> list = new ArrayList<MapCheckReport>();
        try {
            list = session.createQuery(hql).list();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            msg = model.Message.exceptionMsg(e);
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}

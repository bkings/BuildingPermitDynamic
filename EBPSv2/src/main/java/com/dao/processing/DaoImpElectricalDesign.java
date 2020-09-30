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

import com.model.processing.ElectricalDesign;
import com.model.processing.ElectricalDesignDetails;

/**
 *
 * @author kamal
 */
public class DaoImpElectricalDesign implements DaoElectricalDesign {

String msg = "";
int row = 1;

@Override
public int save(ElectricalDesign obj) {

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
public int save(ElectricalDesignDetails obj) {

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
public List getAll(String hql) {
    msg = "";
    Session session = model.HibernateUtil.getSession();
    Transaction tr = session.beginTransaction();
    List list = new ArrayList();
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

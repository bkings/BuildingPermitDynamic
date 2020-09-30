/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.utility;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.utility.ChangeDesigner;

@Component
public class DaoImpChangeDesigner implements DaoChangeDesigner{
    String msg = "";
    int row = 1;
    
    
    @Override
    public int save(ChangeDesigner obj) {
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        msg = "";
        row = 1;
        try {
            session.save(obj);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            msg = model.Message.exceptionMsg(e);
            row = 0;
        }
        try {
            session.close();
        } catch (HibernateException e) {
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
        System.err.println(msg);
    }
    try {
        session.close();
    } catch (Exception e) {
    }
    return list;
    }
    
}

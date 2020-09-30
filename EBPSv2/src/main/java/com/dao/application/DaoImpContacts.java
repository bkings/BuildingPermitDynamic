/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.application;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.application.Contacts;

@Component
public class DaoImpContacts implements DaoContacts{
    String msg = "";
    int row = 1;
    
    @Override
    public Object getAll(String sql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        List<Contacts> list = new ArrayList<>();
        try {
            list = session.createQuery(sql).list();
        } catch (HibernateException e) {
            msg = model.Message.exceptionMsg(e);
        }
        try {
            session.close();
        } catch (HibernateException e) {
        }
        //System.out.println(list);
        return list;
    
    }

    @Override
    public List getById(String hql) {
    msg = "";
    Session session = model.HibernateUtil.getSession();
    Transaction tr = session.beginTransaction();
    List<Contacts> list = new ArrayList();
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
    public int save(Contacts obj) {
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
    
    
}

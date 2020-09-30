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

import com.model.utility.MasonList;

@Component
public class DaoImpMasonList implements DaoMasonList {

    String msg = "";
    int row = 1;
    
    @Override
    public List<MasonList> getAll(String hql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        List<MasonList> list = new ArrayList<>();
        Transaction tr = session.beginTransaction();
        try {
            list = session.createQuery(hql).list();
            tr.commit();
        } catch (HibernateException e) {
            msg = model.Message.exceptionMsg(e);
            tr.rollback();
        }
        try {
            session.close();
        } catch (HibernateException e) {
        }
        return list;
    }
    
    @Override
    public List getRecord(String sql) {
        String msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        List list = new ArrayList();
        try {
            list = session.createSQLQuery(sql).setResultTransformer(org.hibernate.Criteria.ALIAS_TO_ENTITY_MAP).list();
            tr.commit();
        } catch (HibernateException e) {
            tr.rollback();
            msg = model.Message.exceptionMsg(e);
        }
        try {
            session.close();
        } catch (HibernateException e) {
        }
        return list;
    }
    
    @Override
    public int save(MasonList obj) {
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
        } catch (HibernateException e) {
        }
        return row;
    }
    
    @Override
    public String getMsg() {
        return msg;
    }
    
    @Override
    public int update(MasonList obj) {
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        row = 1;
        msg = "";
        try {
            session.saveOrUpdate(obj);
            tr.commit();
        } catch (HibernateException e) {
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
    public int delete(String sql) {
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        msg = "";
        row = 0;
        try {
            row = session.createSQLQuery(sql).executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            msg = model.Message.exceptionMsg(e);
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return row;
    }
    
}

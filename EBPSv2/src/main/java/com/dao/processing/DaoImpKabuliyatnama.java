/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.processing;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.processing.Kabuliyatnama;

import model.HibernateUtil;

@Component
public class DaoImpKabuliyatnama implements DaoKabuliyatnama {

    String msg = "";
    int row = 1;

    @Override
    public List<Kabuliyatnama> getAll(String hql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        List<Kabuliyatnama> list = new ArrayList<>();
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
    public int save(Kabuliyatnama obj) {
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
    public List getRecord(String sql) {
        List list = new ArrayList();
        Session session = HibernateUtil.getSession();
        try {
            Transaction tr = session.beginTransaction();
            list = session.createSQLQuery(sql).setResultTransformer(org.hibernate.Criteria.ALIAS_TO_ENTITY_MAP).list();
            tr.commit();
        } catch (Exception e) {
            msg = model.Message.exceptionMsg(e);
            System.err.println(msg);
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

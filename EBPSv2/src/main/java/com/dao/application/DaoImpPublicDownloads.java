/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.application;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.application.PublicDownloads;

@Component

public class DaoImpPublicDownloads implements DaoPublicDownloads{
    String msg = "";
    int row = 1;

    
    @Override
    public int update(PublicDownloads obj) {
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
    public String getMsg() {
        return msg;
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

package com.dao.processing;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.processing.AnusuchiGa;

@Component
public class DaoImpAnusuchiGa implements DaoAnusuchiGa {

    String msg = "";
    int row = 1;

    @Override
    public int save(AnusuchiGa obj) {
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
    public List<AnusuchiGa> getAll(String hql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        List<AnusuchiGa> list = new ArrayList<AnusuchiGa>();
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
    public List getRecord(String sql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        List list = new ArrayList();
        try {
            list = session.createSQLQuery(sql).setResultTransformer(org.hibernate.Criteria.ALIAS_TO_ENTITY_MAP).list();
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

    @Override
    public int update(AnusuchiGa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(AnusuchiGa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

package com.dao.processing;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.processing.FileStorageApplication;
import com.model.processing.NoticePaymentApplication;

@Component
public class DaoImpFileStorageApplication implements DaoFileStorageApplication {

    String msg = "";
    int row = 1;

    @Override
    public List<FileStorageApplication> getAll(String hql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        List<FileStorageApplication> list = new ArrayList<>();
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
    public int save(FileStorageApplication obj) {
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

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public List<NoticePaymentApplication> getNoticePaymentApplication(String hql) {

        msg = "";
        Session session = model.HibernateUtil.getSession();
        List<NoticePaymentApplication> list = new ArrayList();
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
    public int save(NoticePaymentApplication obj) {

        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        msg = "";
        row = 1;
        try {
            session.update(obj);
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

}

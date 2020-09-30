package com.dao.processing;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.processing.AminiInquiry;
import com.model.processing.AminiKabuliyatnama;
import com.model.processing.GharCompoundWall;

@Component
public class DaoImpAmini implements DaoAmini {

String msg = "";
int row = 1;

@Override
public List<AminiInquiry> getAll(String hql) {
    msg = "";
    Session session = model.HibernateUtil.getSession();
    List<AminiInquiry> list = new ArrayList<>();
    try {
        list = session.createQuery(hql).list();
    } catch (HibernateException e) {
        msg = model.Message.exceptionMsg(e);
    }
    try {
        session.close();
    } catch (HibernateException e) {
    }
    return list;
}

@Override
public int save(AminiInquiry obj) {
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
public List getRecord(String sql) {
    msg = "";
    Session session = model.HibernateUtil.getSession();
    List list = new ArrayList();
    try {
        list = session.createSQLQuery(sql).setResultTransformer(org.hibernate.Criteria.ALIAS_TO_ENTITY_MAP).list();
    } catch (HibernateException e) {
        msg = model.Message.exceptionMsg(e);
    }
    try {
        session.close();
    } catch (HibernateException e) {
    }
    return list;
}

@Override
public String getMsg() {
    return msg;
}

@Override
public int save(GharCompoundWall obj) {

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
public int save(AminiKabuliyatnama obj) {

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

}

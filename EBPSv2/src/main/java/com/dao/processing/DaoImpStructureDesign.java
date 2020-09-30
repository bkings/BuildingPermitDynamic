package com.dao.processing;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.processing.NoticePeriodFor15Days;
import com.model.processing.SanitaryDesign;
import com.model.processing.StructureDesignB;
import com.model.processing.StructureDesignC;

public class DaoImpStructureDesign implements DaoStructureDesign {

String msg = "";
int row = 1;

@Override
public int save(StructureDesignC obj) {

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

@Override
public int save(StructureDesignB obj) {

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
public int save(NoticePeriodFor15Days obj) {

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
public int save(SanitaryDesign obj) {

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

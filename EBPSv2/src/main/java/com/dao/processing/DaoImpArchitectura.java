package com.dao.processing;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.processing.ArchitecturalDesignB;
import com.model.processing.ArchitecturalDesignBDetails;
import com.model.processing.ArchitecturalDesignC;
import com.model.processing.ArchitecturalDesignCDetails;

public class DaoImpArchitectura implements DaoArchitectural {

String msg = "";
int row = 1;

@Override
public int save(ArchitecturalDesignB obj) {

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
public int save(ArchitecturalDesignBDetails obj) {

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
public int save(ArchitecturalDesignC obj) {

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
public int save(ArchitecturalDesignCDetails obj) {

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

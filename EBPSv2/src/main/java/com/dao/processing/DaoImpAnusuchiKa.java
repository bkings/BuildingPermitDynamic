package com.dao.processing;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.processing.AnusuchiKa;

@Component
public class DaoImpAnusuchiKa implements DaoAnusuchiKa {

String msg = "";
int row = 1;

@Override
public int save(AnusuchiKa obj) {
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
//
//    try {
//        session.close();
//    } catch (Exception e) {
////>>>>>>> 9eeaa33c4f92c888f51678b8edd929ec5e6d2c1e
//    }
    //return row;
}

//@Override
//public List getAll(String hql) {
//    msg = "";
//    Session session = model.HibernateUtil.getSession();
//    
//    List list = new ArrayList();
//    try {
//        Transaction tr = session.beginTransaction();
//        list = session.createQuery(hql).list();
//        tr.commit();
//    } catch (Exception e) {
//        msg = model.Message.exceptionMsg(e);
//    }
//    try {
//        session.close();
//    } catch (Exception e) {
//    }
//    return list;
//}
@Override
public String getMsg() {
    return msg;
}

}

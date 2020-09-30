package com.dao.utility;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.utility.UserTypeMaster;

@Component
public class DaoImpUserTypeMaster implements DaoUserTypeMaster {
    
    String msg = "";
    int row = 1;
    
    @Override
    public List<UserTypeMaster> getAll(String hql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        List<UserTypeMaster> list = new ArrayList<UserTypeMaster>();
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
    public int save(UserTypeMaster obj) {
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
        } catch (Exception e) {
        }
        return row;
        
    }
    
    @Override
    public String getMsg() {
        return msg;
    }
    
}

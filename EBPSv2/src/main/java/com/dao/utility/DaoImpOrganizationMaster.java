package com.dao.utility;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.utility.OrganizationMaster;
import com.model.utility.OrganizationUser;
import com.model.utility.UserTypeMaster;

@Component
public class DaoImpOrganizationMaster implements DaoOrganizationMaster {

    String msg = "";
    int row = 1;

    @Override
    public int save(OrganizationMaster obj) {
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
    public List<OrganizationMaster> getAll(String hql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        List<OrganizationMaster> list = new ArrayList<OrganizationMaster>();
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
    public int save(OrganizationUser obj) {

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
    public int save(UserTypeMaster obj) {

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
    public int update(OrganizationMaster obj) {
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

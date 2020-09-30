package com.dao.application;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.application.ApplicationAction;
import com.model.application.ApplicationsComments;
import com.model.application.BuildingMemberDetails;
import com.model.application.BuildingPermitApplication;
import com.model.application.BuildingPermitApplicationNameTransafer;
import com.model.application.BuildingPermitFloor;
import com.model.application.BuildingPermitSurrounding;

@Component
public class DaoImpBuildingPermitApplication implements DaoBuildingPermitApplication {

    String msg = "";
    int row = 1;

    @Override
    public int save(BuildingPermitApplication obj) {
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

//    @Override
//    public int update(BuildingPermitApplication obj) {
//        Session session = model.HibernateUtil.getSession();
//        Transaction tr = session.beginTransaction();
//        row = 1;
//        msg = "";
//        try {
//            session.update(obj);
//            tr.commit();
//        } catch (Exception e) {
//            tr.rollback();
//            msg = model.Message.exceptionMsg(e);
//            row = 0;
//        }
//        try {
//            session.close();
//        } catch (Exception e) {
//        }
//        return row;
//    }
//    @Override
//    public int delete(BuildingPermitApplication obj) {
//
//        row = 1;
//        msg = "";
//        Session session = model.HibernateUtil.getSession();
//        Transaction tr = session.beginTransaction();
//        try {
//            session.delete(obj);
//            tr.commit();
//        } catch (Exception e) {
//            tr.rollback();
//            msg = model.Message.exceptionMsg(e);
//            row = 0;
//        }
//        try {
//            session.close();
//        } catch (Exception e) {
//        }
//        return row;
//    }
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
    public List<BuildingPermitApplication> getAll(String hql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        List<BuildingPermitApplication> list = new ArrayList();
        try {
            list = session.createQuery(hql).list();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            msg = model.Message.exceptionMsg(e);
            System.out.println(msg);
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public List<BuildingPermitApplication> getRecord(String sql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        List<BuildingPermitApplication> list = new ArrayList();
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
    public int save(BuildingPermitSurrounding obj) {

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
    public int save(BuildingMemberDetails obj) {

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
    public int save(BuildingPermitFloor obj) {
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
    public int delete(BuildingPermitSurrounding obj) {
        row = 1;
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.delete(obj);
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
    public int delete(BuildingMemberDetails obj) {
        row = 1;
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.delete(obj);
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
    public int delete(BuildingPermitFloor obj) {
        row = 1;
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.delete(obj);
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
    public List<ApplicationsComments> getApplicationsComments(String hql) {
        msg = "";
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        List<ApplicationsComments> list = new ArrayList<ApplicationsComments>();
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
    public int save(ApplicationAction obj) {

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
    public int save(BuildingPermitApplicationNameTransafer obj) {
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
        } catch (Exception e) {
        }
        return row;
    }

}

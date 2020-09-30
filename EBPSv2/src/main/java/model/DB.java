package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DB {

    String msg = "";

    public int save(String sql) {
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        int a = 0;
        try {
            a = session.createSQLQuery(sql).executeUpdate();
            tr.commit();
            if (a != 0) {
                setMsg("Success");
            } else {
                setMsg("Reccord Not saved");
            }
        } catch (Exception e) {
            setMsg(model.Message.exceptionMsg(e));
            tr.rollback();
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return a;
    }
    
    public int saveMultiple(String[] sql) {
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        int a = 0;
        try {
        	for(String i: sql) {
        		a = session.createSQLQuery(i).executeUpdate();
        	}
            tr.commit();
            if (a != 0) {
                setMsg("Success");
            } else {
                setMsg("Reccord Not saved");
            }
        } catch (Exception e) {
            setMsg(model.Message.exceptionMsg(e));
            tr.rollback();
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return a;
    }

    public int save(String sql, String parameterValue) {
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        int a = 0;
        try {
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, parameterValue);
            a = query.executeUpdate();
            tr.commit();
            setMsg("Success");
        } catch (Exception e) {
            setMsg(model.Message.exceptionMsg(e));
            tr.rollback();
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return a;
    }

    public int save(String sql, String parameter1, String parameter2) {
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        int a = 0;
        try {
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, parameter1);
            query.setParameter(1, parameter2);
            a = query.executeUpdate();
            tr.commit();
            setMsg("Success");
        } catch (Exception e) {
            setMsg(model.Message.exceptionMsg(e));
            tr.rollback();
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return a;
    }

    public int save(String sql, String[] parameterValue) {
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        int a = 0;
        try {
            Query query = session.createSQLQuery(sql);
            for (int i = 0; i < parameterValue.length; i++) {
                query.setParameter(i, parameterValue[i]);
            }
            a = query.executeUpdate();
            tr.commit();
            setMsg("Success");
        } catch (Exception e) {
            setMsg(model.Message.exceptionMsg(e));
            tr.rollback();
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return a;
    }

    public int save(String sql, String[] parameter, String[] parameterValue) {
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        int a = 0;
        try {
            Query query = session.createSQLQuery(sql);
            for (int i = 0; i < parameter.length; i++) {
                query.setParameter(parameter[i], parameterValue[i]);
            }
            a = query.executeUpdate();
            tr.commit();
            setMsg("Success");
        } catch (Exception e) {
            setMsg(model.Message.exceptionMsg(e));
            tr.rollback();
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return a;
    }

    public int delete(String sql) {
        int a = 0;
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        try {
            a = session.createSQLQuery(sql).executeUpdate();
            tr.commit();
            setMsg("Success");
        } catch (Exception e) {
            setMsg(model.Message.exceptionMsg(e));
            tr.rollback();
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return a;
    }

    public List getRecord(String sql) {
        List list = new ArrayList();
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        try {
            
            list = session.createSQLQuery(sql).setResultTransformer(org.hibernate.Criteria.ALIAS_TO_ENTITY_MAP).list();
            tr.commit();
        } catch (Exception e) {
        	tr.rollback();
            msg = model.Message.exceptionMsg(e);
            System.out.println("error " + e.getMessage());
            System.err.println(msg);
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        return list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

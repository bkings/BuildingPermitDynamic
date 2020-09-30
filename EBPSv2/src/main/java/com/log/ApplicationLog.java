package com.log;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ApplicationLog {

    public static void save(String api, String method, String loginBy, String activity) {
        ApplicationActivity obj = new ApplicationActivity(new ApplicationActivityPK(api, method, loginBy), activity);
        Session session = model.HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.save(obj);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        }
        try {
            session.close();
        } catch (Exception e) {
        }

    }

}

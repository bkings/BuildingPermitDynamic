package com.dao.dynamic;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.dynamic.ApplicationStatus;

@Component
public class DaoApplicationStatusImpl implements DaoApplicationStatus {
	String msg = "";
	int row = 1;

	@Override
	public int save(ApplicationStatus status) {
		Session session = model.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		msg = "";
		row = 1;
		try {
			session.saveOrUpdate(status);
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

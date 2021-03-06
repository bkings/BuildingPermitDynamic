package com.dao.dynamic;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.dynamic.HasRevisedFormSetup;

@Component
public class DaoHasRevisedFormSetupImpl implements DaoHasRevisedFormSetup {

	String msg = "";
	int row = 1;

	@Override
	public int save(HasRevisedFormSetup status) {
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

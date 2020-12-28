package com.dao.dynamic.general;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import model.HibernateUtil;
import model.Message;

@Component
public class DaoImpGeneral implements DaoGeneral {

	Message message = new Message();
	String msg = "";
	int row = 1;
	List list = new ArrayList<>();

	@Override
	public int execute(String sql) {
		Session session = HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		msg = "";
		row = 1;
		try {
			row = session.createSQLQuery(sql).executeUpdate();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			msg = message.exceptionMsg(e);
			row = 0;
			System.out.println("Localized msg " + e.getLocalizedMessage());
		}
		try {
			session.close();
		} catch (HibernateException e) {
		}
		return row;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public List getRecords(String sql) {
		Session session = HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		msg = "";
		try {
			list = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			msg = Message.exceptionMsg(e);
		}
		try {
			session.close();
		} catch (HibernateException e) {
		}
		return list;
	}

	@Override
	public List getRecordsAsList(String sql) {
		Session session = HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		msg = "";
		try {
			list = session.createSQLQuery(sql).list();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			msg = Message.exceptionMsg(e);
		}
		try {
			session.close();
		} catch (HibernateException e) {
		}
		return list;
	}

}

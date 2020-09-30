package com.dao.processing;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.processing.SthalgatNiirikshyanGariPesh;
import com.model.processing.SthalgatNiirikshyanGariPeshDetails;

@Component
public class DaoImpSthalgatNiirikshyanGariPesh implements DaoSthalgatNiirikshyanGariPesh {

	String msg = "";
	int row = 1;

	@Override
	public int save(SthalgatNiirikshyanGariPesh obj) {
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
	public int save(SthalgatNiirikshyanGariPeshDetails obj) {
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
	public List<SthalgatNiirikshyanGariPesh> getAll(String hql) {
		msg = "";
		Session session = model.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		List<SthalgatNiirikshyanGariPesh> list = new ArrayList<SthalgatNiirikshyanGariPesh>();
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

}

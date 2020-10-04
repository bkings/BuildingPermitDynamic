package com.dao.dynamic;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.dynamic.EbpsColumns;
import com.model.dynamic.EbpsTables;

import model.HibernateUtil;
import model.Message;

@Component
public class DaoEbpsTablesImpl implements DaoEbpsTables {

	String msg = "";
	int row = 1;

	Message message = new Message();

	@Override
	public List<EbpsTables> getAll(String hql) {
		msg = "";
		Session session = model.HibernateUtil.getSession();
		List<EbpsTables> list = new ArrayList<>();
		try {
			list = session.createQuery(hql).list();
		} catch (HibernateException e) {
			msg = model.Message.exceptionMsg(e);
		}
		try {
			session.close();
		} catch (HibernateException e) {
		}
		return list;
	}

	@Override
	public int save(EbpsTables obj) {
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
		} catch (HibernateException e) {
		}
		return row;
	}

	@Override
	public List getRecord(String sql) {
		msg = "";
		Session session = model.HibernateUtil.getSession();
		List list = new ArrayList();
		try {
			list = session.createSQLQuery(sql).setResultTransformer(org.hibernate.Criteria.ALIAS_TO_ENTITY_MAP).list();
		} catch (HibernateException e) {
			msg = model.Message.exceptionMsg(e);
		}
		try {
			session.close();
		} catch (HibernateException e) {
		}
		return list;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public int update(EbpsTables obj) {
		Session session = model.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		row = 1;
		msg = "";
		try {
			session.update(obj);
			tr.commit();
		} catch (HibernateException e) {
			tr.rollback();
			msg = model.Message.exceptionMsg(e);
			row = 0;
		}
		try {
			session.close();
		} catch (HibernateException e) {
		}
		return row;
	}

	@Override
	public int delete(EbpsTables obj) {
		Session session = HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		row = 1;
		msg = "";
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
		} catch (HibernateException e) {
		}
		return row;
	}

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
		}
		try {
			session.close();
		} catch (HibernateException e) {
		}
		return row;
	}

	@Override
	public int deleteColumns(EbpsColumns obj) {
		Session session = HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		row = 1;
		msg = "";
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
		} catch (HibernateException e) {
		}
		return row;
	}

	@Override
	public List<EbpsColumns> getAllData(String hql) {
		msg = "";
		Session session = model.HibernateUtil.getSession();
		List<EbpsColumns> list = new ArrayList<>();
		try {
			list = session.createQuery(hql).list();
		} catch (HibernateException e) {
			msg = model.Message.exceptionMsg(e);
		}
		try {
			session.close();
		} catch (HibernateException e) {
		}
		return list;
	}
}

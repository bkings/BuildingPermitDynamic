package com.dao.dynamic;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.dynamic.FormFields;
import com.model.utility.FormNameMaster;

import model.HibernateUtil;

@Component
public class DaoFormMasterImpl implements DaoFormMaster {

	String msg = "";
	int row = 1;

	@Override
	public List<FormNameMaster> getAll(String hql) {
		msg = "";
		Session session = model.HibernateUtil.getSession();
		List<FormNameMaster> list = new ArrayList<>();
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
	public int save(FormNameMaster obj) {
		Session session = model.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		msg = "";
		row = 1;
		try {
			session.saveOrUpdate(obj);
			;
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
	public int update(FormNameMaster obj) {
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
	public int delete(FormNameMaster obj) {
		Session session = model.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		row = 1;
		msg = "";
		try {
			session.delete(obj);
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
	public List<FormFields> getAllFields(String hql) {
		msg = "";
		Session session = model.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		List<FormFields> list = new ArrayList<FormFields>();
		try {
			list = session.createQuery(hql).list();
			tr.commit();
		} catch (HibernateException e) {
			tr.rollback();
			msg = model.Message.exceptionMsg(e);
		}
		try {
			session.close();
		} catch (HibernateException e) {
		}
		return list;
	}

	@Override
	public int deleteFields(FormFields obj) {
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

}

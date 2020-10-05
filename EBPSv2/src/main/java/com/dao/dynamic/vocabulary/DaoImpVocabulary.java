package com.dao.dynamic.vocabulary;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.model.dynamic.EbpsTables;
import com.model.dynamic.vocabulary.Vocabulary;
import com.model.dynamic.vocabulary.VocabularyDetails;

import model.HibernateUtil;
import model.Message;

@Component
public class DaoImpVocabulary implements DaoVocabulary {

	String msg = "";
	int row = 1;

	Message message = new Message();

	@Override
	public int execute(String sql) {
		msg = "";
		row = 1;
		Session session = HibernateUtil.getSession();
		Transaction tr = session.getTransaction();
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
		} catch (Exception e) {
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
	public List<Vocabulary> getAll(String hql) {
		msg = "";
		Session session = model.HibernateUtil.getSession();
		List<Vocabulary> list = new ArrayList<Vocabulary>();
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
	public List<VocabularyDetails> getAllDetails(String hql) {
		msg = "";
		Session session = model.HibernateUtil.getSession();
		List<VocabularyDetails> list = new ArrayList<VocabularyDetails>();
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
	public int save(Vocabulary obj) {
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
	public int delete(Vocabulary obj) {
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
	public int deleteDetaisl(VocabularyDetails obj) {
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
	public String getMsg() {
		return msg;
	}

}

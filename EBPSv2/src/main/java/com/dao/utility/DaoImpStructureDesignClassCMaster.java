package com.dao.utility;

//package dao.utility;
//import java.util.List;
//import java.util.ArrayList;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import model.utility.StructureDesignClassCMaster;
//public class DaoImpStructureDesignClassCMaster implements DaoStructureDesignClassCMaster{
//String msg="";int row=1;
//
//@Override
//public int save(StructureDesignClassCMaster obj) {
//Session session=model.HibernateUtil.getSession();
//Transaction tr=session.beginTransaction();
//msg="";row=1;
//try{
//session.save(obj);
//tr.commit();
//}catch(Exception e){tr.rollback();msg=model.Message.exceptionMsg(e);row=0;}
//try{session.close();}catch(Exception e){}
// return row;
//}
//
//@Override
//public int update(StructureDesignClassCMaster  obj) {
//Session session=model.HibernateUtil.getSession();
//Transaction tr=session.beginTransaction();
// row=1;msg="";
//try{
//session.update(obj);
//tr.commit();
//}catch(Exception e){tr.rollback();msg=model.Message.exceptionMsg(e);row=0;}
//try{session.close();}catch(Exception e){}
// return row;
//}
//
//@Override
//
//public int delete(StructureDesignClassCMaster obj) 
//{
//
// row=1;msg="";
//Session session=model.HibernateUtil.getSession();
//Transaction tr=session.beginTransaction();
//try{
// session.delete(obj);
//tr.commit();
//}catch(Exception e){tr.rollback();msg=model.Message.exceptionMsg(e);row=0;}
//try{session.close();}catch(Exception e){} return row;
//}
//
//@Override
//public int delete(String sql)
//{
//Session session=model.HibernateUtil.getSession();
//Transaction tr=session.beginTransaction();
//msg=""; row=0;
//try{ 
//row= session.createSQLQuery(sql).executeUpdate();
//tr.commit();
//}catch(Exception e){ tr.rollback();msg=model.Message.exceptionMsg(e); }
//try{session.close();}catch(Exception e){}
//return row;
//}
//
//@Override
//public List<StructureDesignClassCMaster> getAll(String hql){
// msg="";
//Session session=model.HibernateUtil.getSession();
//Transaction tr = session.beginTransaction();
//List<StructureDesignClassCMaster> list=new  ArrayList<StructureDesignClassCMaster>();
//try{
//list=session.createQuery(hql).list();
//tr.commit();
//}catch(Exception e){
//tr.rollback();
//msg=model.Message.exceptionMsg(e);}
//try{session.close();}catch(Exception e){}
//return list;}
//
//@Override
//public List getRecord(String sql)
//{
//msg="";
//Session session=model.HibernateUtil.getSession();
//Transaction tr = session.beginTransaction();
//List list=new ArrayList();
//try{ 
//Transaction tr = session.beginTransaction();
// list=session.createSQLQuery(sql).setResultTransformer(org.hibernate.Criteria.ALIAS_TO_ENTITY_MAP).list();
//tr.commit();
//}catch(Exception e){ 
//tr.rollback();
//msg=model.Message.exceptionMsg(e);}
//try{session.close();}catch(Exception e){}return list;
//}
//
// @Override
// public String getMsg(){return msg;}
//
//}

package com.tthg.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.IObjectionDAO;
import com.tthg.entity.Objection;
@Repository
public class ObjectionDAO implements IObjectionDAO {
	
	@Autowired
	private SessionFactory sessionFactory;	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(Objection objection) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();		
		session.save(objection);
		trans.commit();
		session.close();
	}

	@Override
	public void delete(int[] ids) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		for(int i=0;i<ids.length;i++){
			Objection vc=(Objection)session.get(Objection.class, ids[i]);
			session.delete(vc);
		}
		trans.commit();
		session.close();
	}

	@Override
	public void update(Objection objection) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();		
		session.update(objection);
		trans.commit();
		session.close();
	}

	@Override
	public List<Objection> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from Objection";
		Query query=session.createQuery(hql);
		List<Objection> list=query.list();
		session.close();		
		return list;
	}

	@Override
	public List<Objection> searchObjection(Objection objection) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Objection o where 1=1 ");
		Map map=new HashMap();
		if(!(objection.getCustomerName()==null||"".equals(objection.getCustomerName()))){
			map.put("CustomerName", "%"+objection.getCustomerName()+"%");
			hql.append(" and o.customerName like:CustomerName");
		}
		if(objection.getObTime()!=null){
			map.put("ObTime",objection.getObTime());
			hql.append(" and o.obTime=:ObTime");
		}
		String hqlStr=hql.toString();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hqlStr);		
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));
		}
		List<Objection> list=query.list();
		session.close();
		return list;
	}

}

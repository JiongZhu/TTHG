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

import com.tthg.dao.ISubscriptionDAO;
import com.tthg.entity.Order;
import com.tthg.entity.Subscription;
import com.tthg.entity.Vehicle;
@Repository
public class SubscriptionDAO implements ISubscriptionDAO {
	
	@Autowired
	private SessionFactory sessionFactory;	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(Subscription subscription) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		Vehicle vehicle=(Vehicle)session.get(Vehicle.class,subscription.getCarStyleId());
		subscription.setVehicle(vehicle);
		session.save(subscription);
		trans.commit();
		session.close();
	}

	@Override
	public void delete(int[] ids) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		for(int i=0;i<ids.length;i++){
			Subscription sp=(Subscription)session.get(Subscription.class, ids[i]);
			session.delete(sp);
		}
		trans.commit();
		session.close();
	}

	@Override
	public void update(Subscription subscription) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		Vehicle vehicle=(Vehicle)session.get(Vehicle.class,subscription.getCarStyleId());
		subscription.setVehicle(vehicle);
		session.update(subscription);
		trans.commit();
		session.close();
	}

	@Override
	public List<Subscription> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from Subscription";
		Query query=session.createQuery(hql);
		List<Subscription> list=query.list();
		for(int i=0;i<list.size();i++){
			Subscription subscription=list.get(i);
			subscription.setCname(subscription.getVehicle().getVehicleName());
		}
		session.close();		
		return list;
	}

	@Override
	public List<Subscription> searchSubscription(Subscription subscription) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Subscription s where 1=1 ");
		Map map=new HashMap();
		if(!(subscription.getCustomerName()==null||"".equals(subscription.getCustomerName()))){
			map.put("CustomerName", "%"+subscription.getCustomerName()+"%");
			hql.append(" and s.customerName like:CustomerName");
		}
		if(!(subscription.getCardId()==null||"".equals(subscription.getCardId()))){
			map.put("CardId", "%"+subscription.getCardId()+"%");
			hql.append(" and s.cardId like:CardId");
		}
		if(!(subscription.getSubTime()==null)){
			map.put("SubTime",subscription.getSubTime());
			hql.append(" and s.subTime=:SubTime");
		}
		String hqlStr=hql.toString();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hqlStr);		
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));
		}
		List<Subscription> list=query.list();
		for(int i=0;i<list.size();i++){
			Subscription sub=list.get(i);
			sub.setCname(sub.getVehicle().getVehicleName());
		}
		session.close();
		return list;
	}

}

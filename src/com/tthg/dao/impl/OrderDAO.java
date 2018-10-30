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

import com.tthg.dao.IOrderDAO;
import com.tthg.entity.Order;
import com.tthg.entity.Subscription;
@Repository
public class OrderDAO implements IOrderDAO {
	
	@Autowired
	private SessionFactory sessionFactory;	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(Order order) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		Subscription subscription=(Subscription)session.get(Subscription.class,order.getSubscriptionId());
		try {
			order.setSubscription(subscription);
			order=(Order)session.merge(order);
			session.save(order);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		session.close();
	}

	@Override
	public void delete(int[] ids) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		for(int i=0;i<ids.length;i++){
			Order sp=(Order)session.get(Order.class, ids[i]);
			session.delete(sp);
		}
		trans.commit();
		session.close();
	}

	@Override
	public void update(Order order) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		Subscription subscription=(Subscription)session.get(Subscription.class,order.getSubscriptionId());
		try {
			order.setSubscription(subscription);
			order=(Order)session.merge(order);
			session.update(order);
			trans.commit();	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		session.close();
	}

	@Override
	public List<Order> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from Order";
		Query query=session.createQuery(hql);
		List<Order> list=query.list();
		for(int i=0;i<list.size();i++){
			Order order=list.get(i);
			order.setSid(order.getSubscription().getId());
		}		
		session.close();		
		return list;
	}

	@Override
	public List<Order> searchOrder(Order order) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Order as o where 1=1 ");
		Map map=new HashMap();
		if(!(order.getCustomerName()==null||"".equals(order.getCustomerName()))){
			map.put("CustomerName", "%"+order.getCustomerName()+"%");
			hql.append(" and o.customerName like:CustomerName");
		}
		if(order.getOrTime()!=null){
			map.put("OrTime", order.getOrTime());
			hql.append(" and o.orTime=:OrTime");
		}
		if(!(order.getState()==null||"".equals(order.getState()))){
			map.put("State",order.getState());
			hql.append(" and o.state=:State");
		}
		String hqlStr=hql.toString();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hqlStr);
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));
		}
		List<Order> list=query.list();
		for(int i=0;i<list.size();i++){
			Order ord=list.get(i);
			ord.setSid(ord.getSubscription().getId());
		}
		session.close();
		return list;
	}

}

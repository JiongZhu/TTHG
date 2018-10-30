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

import com.tthg.dao.ICallbackDAO;
import com.tthg.entity.Callback;
import com.tthg.entity.Order;
@Repository
public class CallbackDAO implements ICallbackDAO {
	@Autowired
	private SessionFactory sessionFactory;	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(Callback callback) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();	
        Order order=(Order)session.get(Order.class,callback.getOrderId());
        callback.setOrder(order);
        callback=(Callback)session.merge(callback);
		session.save(callback);
		trans.commit();
		session.close();
	}

	@Override
	public void delete(int[] ids) {//前台支持多选删除，将待删除记录的id放到整型数组传递到后台
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		for(int i=0;i<ids.length;i++){
			Callback callback=(Callback)session.get(Callback.class, ids[i]);
			session.delete(callback);
		}
		trans.commit();
		session.close();	
	}

	@Override
	public void update(Callback callback) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
        Order order=(Order)session.get(Order.class,callback.getOrderId());
        callback.setOrder(order);
        callback=(Callback)session.merge(callback);
		session.update(callback);
		trans.commit();	
		session.close();	
	}

	@Override
	public List<Callback> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from Callback";
		Query query=session.createQuery(hql);
		List<Callback> list=query.list();
		for(int i=0;i<list.size();i++){
			Callback callback=list.get(i);
			callback.setOid(callback.getOrder().getId());
		}
		session.close();		
		return list;
	}

	@Override
	public List<Callback> searchCallback(Callback callback) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Callback c where 1=1 ");
		Map map=new HashMap();
		if(!(callback.getCustomerName()==null||"".equals(callback.getCustomerName()))){
			map.put("CustomerName", "%"+callback.getCustomerName()+"%");
			hql.append(" and c.customerName like:CustomerName");
		}
		if(callback.getCallbackTime()!=null){
			map.put("CallbackTime", callback.getCallbackTime());
			hql.append(" and c.callbackTime=:CallbackTime");
		}

		String hqlStr=hql.toString();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hqlStr);		
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));
		}
		List<Callback> list=query.list();
		for(int i=0;i<list.size();i++){
			Callback cal=list.get(i);
			cal.setOid(cal.getOrder().getId());
		}
		session.close();
		return list;
	}

}

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

import com.tthg.dao.IIntentionCustomerDAO;
import com.tthg.entity.IntentionCustomer;
import com.tthg.entity.Vehicle;

@Repository
public class IntentionCustomerDAO implements IIntentionCustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(IntentionCustomer customer) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		System.out.println(customer.getCname());
        Vehicle vehicle=(Vehicle)session.get(Vehicle.class,Integer.parseInt(customer.getCname()));
        customer.setVehicle(vehicle);
		session.save(customer);	
		trans.commit();
		session.close();
	}

	@Override
	public void delete(int[] ids) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		for(int i=0;i<ids.length;i++){
			IntentionCustomer ic=(IntentionCustomer)session.get(IntentionCustomer.class, ids[i]);
			session.delete(ic);
		}
		trans.commit();
		session.close();
	}

	@Override
	public void update(IntentionCustomer customer) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
//		当你在Hmb映射文件中(*.hbm.xml)的<property>标记内使用了not-null(默认为false)属性，
//		并且值等于true那么你在把对象从VO变成PO的过程中(也就是调用Session类的save方法时)，
//		必须使用setX()方法给这个属性赋值，则hibernate会抛出PropertyValueException：
//		not-null property references a null or transient value ，引用的属性不能为空值。
        Vehicle vehicle=(Vehicle)session.get(Vehicle.class,Integer.parseInt(customer.getCname()));
        customer.setVehicle(vehicle);
    	session.update(customer);	
    	trans.commit();		
		session.close();
	}

	@Override
	public List<IntentionCustomer> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from IntentionCustomer";
		Query query=session.createQuery(hql);
		List<IntentionCustomer> list=query.list();
		for(int i=0;i<list.size();i++){
			IntentionCustomer customer=list.get(i);
			customer.setCname(customer.getVehicle().getVehicleName());
		}
		session.close();		
		return list;
	}

	@Override
	public List<IntentionCustomer> searchCustomer(IntentionCustomer customer) {	
		StringBuffer hql=new StringBuffer();
		hql.append("from IntentionCustomer c left join fetch c.vehicle v where 1=1 ");
		Map map=new HashMap();
		if(!(customer.getCustomerName()==null||"".equals(customer.getCustomerName()))){
			map.put("CustomerName", "%"+customer.getCustomerName()+"%");
			hql.append(" and c.customerName like:CustomerName");
		}
		if(!(customer.getCardId()==null||"".equals(customer.getCardId()))){
			map.put("CardId", "%"+customer.getCardId()+"%");
			hql.append(" and c.cardId like:CardId");
		}
		if(customer.getCarStyleId()!=null){
			map.put("CarStyleId", customer.getCarStyleId());
			hql.append(" and v.id=:CarStyleId");
		}
		String hqlStr=hql.toString();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hqlStr);		
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));
		}
		List<IntentionCustomer> list=query.list();
		for(int i=0;i<list.size();i++){
			IntentionCustomer cus=list.get(i);
			cus.setCname(cus.getVehicle().getVehicleName());
		}
		session.close();
		return list;
	}	
}

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

import com.tthg.dao.IVehicleCustomerDAO;
import com.tthg.entity.VehicleCustomer;
@Repository
public class VehicleCustomerDAO implements IVehicleCustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(VehicleCustomer customer) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();		
		session.save(customer);
		trans.commit();
		session.close();
	}

	@Override
	public void delete(int[] ids) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		for(int i=0;i<ids.length;i++){
			VehicleCustomer vc=(VehicleCustomer)session.get(VehicleCustomer.class, ids[i]);
			session.delete(vc);
		}
		trans.commit();
		session.close();
	}

	@Override
	public void update(VehicleCustomer customer) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		session.update(customer);
		trans.commit();
		session.close();
	}

	@Override
	public List<VehicleCustomer> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from VehicleCustomer";
		Query query=session.createQuery(hql);
		List<VehicleCustomer> list=query.list();
		session.close();		
		return list;
	}

	@Override
	public List<VehicleCustomer> searchCustomer(VehicleCustomer customer) {
		StringBuffer hql=new StringBuffer();
		hql.append("from VehicleCustomer as c where 1=1 ");
		Map map=new HashMap();
		if(!(customer.getCustomerName()==null||"".equals(customer.getCustomerName()))){
			map.put("CustomerName", "%"+customer.getCustomerName()+"%");
			hql.append(" and c.customerName like:CustomerName");
		}
		if(!(customer.getCardId()==null||"".equals(customer.getCardId()))){
			map.put("CardId", "%"+customer.getCardId()+"%");
			hql.append(" and c.cardId like:CardId");
		}
		if(!(customer.getConsultantName()==null||"".equals(customer.getConsultantName()))){
			map.put("ConsultantName","%"+customer.getConsultantName()+"%");
			hql.append(" and c.consultantName like:ConsultantName");
		}
		String hqlStr=hql.toString();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hqlStr);
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));
		}
		List<VehicleCustomer> list=query.list();
		session.close();
		return list;
	}
}

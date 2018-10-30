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

import com.tthg.dao.IReceptionDAO;
import com.tthg.entity.Reception;
import com.tthg.entity.Vehicle;
@Repository
public class ReceptionDAO implements IReceptionDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	@Override
	public void add(Reception reception) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
        Vehicle vehicle=(Vehicle)session.get(Vehicle.class,reception.getCarStyleId());
        reception.setVehicle(vehicle);
        reception=(Reception)session.merge(reception);
		session.save(reception);
		trans.commit();
		session.close();
	}

	@Override
	public void delete(int[] ids) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		for(int i=0;i<ids.length;i++){
			Reception re=(Reception)session.get(Reception.class, ids[i]);
			session.delete(re);
		}
		trans.commit();
		session.close();
	}

	@Override
	public void update(Reception reception) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
        Vehicle vehicle=(Vehicle)session.get(Vehicle.class,reception.getCarStyleId());
        reception.setVehicle(vehicle);
        reception=(Reception)session.merge(reception);
    	session.update(reception);
    	trans.commit();			
		session.close();
	}

	@Override
	public List<Reception> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from Reception";
		Query query=session.createQuery(hql);
		List<Reception> list=query.list();
		for(int i=0;i<list.size();i++){
			Reception reception=list.get(i);
			reception.setCname(reception.getVehicle().getVehicleName());
		}
		session.close();		
		return list;
	}

	@Override
	public List<Reception> searchReception(Reception reception) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Reception as r where 1=1 ");
		Map map=new HashMap();
		if(!(reception.getCustomerName()==null||"".equals(reception.getCustomerName()))){
			map.put("CustomerName", "%"+reception.getCustomerName()+"%");
			hql.append(" and r.customerName like:CustomerName");
		}
		if(!(reception.getReceiver()==null||"".equals(reception.getReceiver()))){
			map.put("Receiver", "%"+reception.getReceiver()+"%");
			hql.append(" and r.receiver like:Receiver");
		}
		if(!(reception.getReceptionTime()==null)){
			map.put("ReceptionTime",reception.getReceptionTime());
			hql.append(" and r.receptionTime=:ReceptionTime");
		}
		if(!(reception.getState()==null||"".equals(reception.getState()))){
			map.put("State",reception.getState());
			hql.append(" and r.state=:State");
		}
		String hqlStr=hql.toString();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hqlStr);
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));
		}
		List<Reception> list=query.list();
		for(int i=0;i<list.size();i++){
			Reception rec=list.get(i);
			rec.setCname(rec.getVehicle().getVehicleName());
		}
		session.close();
		return list;
	}
}

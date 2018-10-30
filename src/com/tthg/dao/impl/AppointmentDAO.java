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

import com.tthg.dao.IAppointmentDAO;
import com.tthg.entity.Appointment;
import com.tthg.entity.IntentionCustomer;
import com.tthg.entity.Vehicle;
@Repository
public class AppointmentDAO implements IAppointmentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(Appointment appointment) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Vehicle vehicle = (Vehicle) session.get(Vehicle.class,
					appointment.getCarStyleId());
		IntentionCustomer customer = (IntentionCustomer) session.get(
					IntentionCustomer.class, appointment.getIntentionId());
		appointment.setVehicle(vehicle);
		appointment.setCustomer(customer);
		appointment=(Appointment)session.merge(appointment);
		session.save(appointment);
		trans.commit();		
		session.close();
	}

	@Override
	public void delete(int[] ids) {//前台支持多选删除，将待删除记录的id放到整型数组传递到后台
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		for(int i=0;i<ids.length;i++){
			Appointment app=(Appointment)session.get(Appointment.class, ids[i]);
			session.delete(app);
		}
		trans.commit();
		session.close();
	}

	@Override
	public void update(Appointment appointment) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		Vehicle vehicle=(Vehicle)session.get(Vehicle.class,appointment.getCarStyleId());
		IntentionCustomer customer = (IntentionCustomer) session.get(IntentionCustomer.class, appointment.getIntentionId());
		appointment.setVehicle(vehicle);
		appointment.setCustomer(customer);
		appointment=(Appointment)session.merge(appointment);
		session.update(appointment);
		trans.commit();		
		session.close();
	}

	@Override
	public List<Appointment> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from Appointment";
		Query query=session.createQuery(hql);
		List<Appointment> list=query.list();
		for(int i=0;i<list.size();i++){
			Appointment appointment=list.get(i);
			appointment.setCname(appointment.getVehicle().getVehicleName());
			appointment.setIid(appointment.getCustomer().getId());
		}
		session.close();		
		return list;
	}

	@Override
	public List<Appointment> searchAppointment(Appointment appointment) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Appointment a left join fetch a.vehicle v where 1=1 ");
		Map map=new HashMap();
		if(!(appointment.getCustomerName()==null||"".equals(appointment.getCustomerName()))){
			map.put("CustomerName", "%"+appointment.getCustomerName()+"%");
			hql.append(" and a.customerName like:CustomerName");
		}
		if(!(appointment.getConsultantName()==null||"".equals(appointment.getConsultantName()))){
			map.put("ConsultantName", "%"+appointment.getConsultantName()+"%");
			hql.append(" and a.consultantName like:ConsultantName");
		}
		if(appointment.getCarStyleId()!=null){
			map.put("CarStyleId", appointment.getCarStyleId());
			hql.append(" and v.id=:CarStyleId");
		}
		if(!(appointment.getScheduledTime()==null)){
			map.put("ScheduledTime",appointment.getScheduledTime());
			hql.append(" and a.scheduledTime=:ScheduledTime");
		}
		if(!(appointment.getState()==null||"".equals(appointment.getState()))){
			map.put("State",appointment.getState());
			hql.append(" and a.state=:State");
		}
		String hqlStr=hql.toString();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hqlStr);
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));
		}
		List<Appointment> list=query.list();
		for(int i=0;i<list.size();i++){
			Appointment app=list.get(i);
			app.setCname(app.getVehicle().getVehicleName());
			app.setIid(app.getCustomer().getId());
		}
		session.close();
		return list;
	}
}

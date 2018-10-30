package com.tthg.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.OrderDao;
import com.tthg.entity.Appointment;
import com.tthg.entity.IntentionCustomer;
import com.tthg.entity.Order;
import com.tthg.entity.Vehicle;
@Repository
public class OrderDaoImp implements OrderDao{
	/**
	 * 前台预约dao层实现类
	 * @author 吴玉双  编写者
	 * @since 2016-12-18 编写时间
	 */
@Autowired
private SessionFactory sessionFactory;

public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

@Override
public void addOrder(Appointment app) {
	// TODO Auto-generated method stub
	System.out.println("进入order中的dao层addorder方法");
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	System.out.println("app.getCarStyleId():"+app.getCarStyleId());
	Vehicle vehicle=(Vehicle)session.get(Vehicle.class, app.getCarStyleId());
	IntentionCustomer customer=(IntentionCustomer)session.get(IntentionCustomer.class,app.getIntentionId());
	app.setVehicle(vehicle);
	app.setCustomer(customer);
	app=(Appointment)session.merge(app);
	session.save(app);
	tx.commit();
	session.close();	
}

}

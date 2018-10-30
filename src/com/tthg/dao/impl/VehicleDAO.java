package com.tthg.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.IVehicleDAO;
import com.tthg.entity.IntentionCustomer;
import com.tthg.entity.Vehicle;
@Repository
public class VehicleDAO implements IVehicleDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void add(Vehicle vehicle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Vehicle vehicle) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Vehicle> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from Vehicle";
		Query query=session.createQuery(hql);
		List<Vehicle> list=query.list();
		session.close();		
		return list;
	}

	@Override
	public List<Vehicle> searchVehicle(Vehicle vehicle) {
		return null;
	}

	@Override
	public int getIdByVehicleName(String vname) {
		// TODO Auto-generated method stube
		
		Session session=sessionFactory.openSession();
		String hql="from Vehicle v where v.VehicleName=?";
		Query query=session.createQuery(hql);
		query.setString(0, vname);
		List<Vehicle> list=query.list();
		int id=0;
        if(list.size()==1){
			Vehicle v=list.get(0);
			id=v.getId();					
		}
        return  id;
	}

}

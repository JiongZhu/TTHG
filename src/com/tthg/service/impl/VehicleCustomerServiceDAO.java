package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IVehicleCustomerDAO;
import com.tthg.entity.VehicleCustomer;
import com.tthg.service.IVehicleCustomerServiceDAO;
@Service
public class VehicleCustomerServiceDAO implements IVehicleCustomerServiceDAO {
	@Autowired
	private IVehicleCustomerDAO customerDAO;
	
	public IVehicleCustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(IVehicleCustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public void add(VehicleCustomer customer) {
		customerDAO.add(customer);
	}

	@Override
	public void delete(int[] ids) {
		customerDAO.delete(ids);
	}

	@Override
	public void update(VehicleCustomer customer) {
		customerDAO.update(customer);
	}

	@Override
	public List<VehicleCustomer> searchAll() {
		return customerDAO.searchAll();
	}

	@Override
	public List<VehicleCustomer> searchCustomer(VehicleCustomer customer) {
		return customerDAO.searchCustomer(customer);
	}
}

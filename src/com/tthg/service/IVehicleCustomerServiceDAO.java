package com.tthg.service;

import java.util.List;

import com.tthg.entity.VehicleCustomer;

public interface IVehicleCustomerServiceDAO {
	void add(VehicleCustomer customer);
	void delete(int[] ids);
	void update(VehicleCustomer customer);
	List<VehicleCustomer> searchAll();
	List<VehicleCustomer> searchCustomer(VehicleCustomer customer);
}

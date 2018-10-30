package com.tthg.service;

import java.util.List;

import com.tthg.entity.Vehicle;

public interface IVehicleServiceDAO {
	void add(Vehicle vehicle);
	void delete(int[] ids);
	void update(Vehicle vehicle);
	List<Vehicle> searchAll();
	List<Vehicle> searchVehicle(Vehicle vehicle);
	int getIdByVehicleName(String vname);
}

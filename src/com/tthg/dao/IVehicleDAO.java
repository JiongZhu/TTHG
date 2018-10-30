package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Vehicle;

public interface IVehicleDAO {
	void add(Vehicle vehicle);
	void delete(int[] ids);
	void update(Vehicle vehicle);
	List<Vehicle> searchAll();
	List<Vehicle> searchVehicle(Vehicle vehicle);
	int getIdByVehicleName(String vname);
}

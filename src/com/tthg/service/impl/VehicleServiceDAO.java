package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IVehicleDAO;
import com.tthg.entity.Vehicle;
import com.tthg.service.IVehicleServiceDAO;
@Service
public class VehicleServiceDAO implements IVehicleServiceDAO {
	@Autowired
	private IVehicleDAO ivd;
	@Override
	public void add(Vehicle vehicle) {
		ivd.add(vehicle);
	}

	@Override
	public void delete(int[] ids) {
		ivd.delete(ids);
	}

	@Override
	public void update(Vehicle vehicle) {
		ivd.update(vehicle);
	}

	@Override
	public List<Vehicle> searchAll() {
		return ivd.searchAll();
	}

	@Override
	public List<Vehicle> searchVehicle(Vehicle vehicle) {
		return ivd.searchVehicle(vehicle);
	}

	public IVehicleDAO getIvd() {
		return ivd;
	}

	public void setIvd(IVehicleDAO ivd) {
		this.ivd = ivd;
	}

	@Override
	public int getIdByVehicleName(String vname) {
		// TODO Auto-generated method stub
		return ivd.getIdByVehicleName(vname);
	}

}

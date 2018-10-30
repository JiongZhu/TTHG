package com.tthg.service;

import java.util.List;

import com.tthg.entity.Vehiclemanger;

public interface IVehiclemangerServiceDAO {
	void add(Vehiclemanger  vehiclemanger);
	void delete(int[] ids);
	void update(Vehiclemanger vehiclemanger);
	List<Vehiclemanger> searchAll();
	List<Vehiclemanger> seachVehiclemanger(Vehiclemanger vehiclemanger); 
}

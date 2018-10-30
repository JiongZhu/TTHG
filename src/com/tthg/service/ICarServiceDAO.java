package com.tthg.service;

import java.util.List;

import com.tthg.entity.Car;

public interface ICarServiceDAO {
	void add(Car car);
	void delete(int[] ids);
	void update(Car car);
	List<Car>  searchAll();
	List<Car> searchCar(Car car);
}

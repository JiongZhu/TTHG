package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.ICarDAO;
import com.tthg.entity.Car;
import com.tthg.service.ICarServiceDAO;
@Service
public class CarServiceDAO implements ICarServiceDAO{
    @Autowired
    private ICarDAO  carDAO;
  //定义借口对象，用来调用方法
	public ICarDAO getCarDAO() {
		return carDAO;
	}

	public void setCarDAO(ICarDAO carDAO) {
		this.carDAO = carDAO;
	}
    
	@Override
	public void add(Car car) {
		// TODO Auto-generated method stub
		carDAO.add(car);
	}

	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		carDAO.delete(ids);//增加
	}

	@Override
	public List<Car> searchAll() {
		// TODO Auto-generated method stub
		return carDAO.searchAll();//全查
	}

	@Override
	public List<Car> searchCar(Car car) {
		// TODO Auto-generated method stub
		return carDAO.searchCar(car);//按条件查询
	}

	@Override
	public void update(Car car) {
		// TODO Auto-generated method stub
		carDAO.update(car);//更新
	}

}

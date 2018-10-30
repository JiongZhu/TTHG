package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IVehiclemangerDAO;
import com.tthg.entity.Vehiclemanger;
import com.tthg.service.IVehiclemangerServiceDAO;
@Service
public class VehiclemangerServiceDAO implements IVehiclemangerServiceDAO{
    @Autowired
    private IVehiclemangerDAO vehiclemangerDAO;
  //定义借口对象，用来调用方法
	public IVehiclemangerDAO getVehiclemangerDAO() {
		return vehiclemangerDAO;
	}

	public void setVehiclemangerDAO(IVehiclemangerDAO vehiclemangerDAO) {
		this.vehiclemangerDAO = vehiclemangerDAO;
	}

	@Override
	public void add(Vehiclemanger vehiclemanger) {
		// TODO Auto-generated method stub
		vehiclemangerDAO.add(vehiclemanger);//增加
	}

	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		vehiclemangerDAO.delete(ids);//删除
	}

	@Override
	public List<Vehiclemanger> seachVehiclemanger(Vehiclemanger vehiclemanger) {
		// TODO Auto-generated method stub
		return vehiclemangerDAO.seachVehiclemanger(vehiclemanger);//按条件查询
	}

	@Override
	public List<Vehiclemanger> searchAll() {
		// TODO Auto-generated method stub
		return vehiclemangerDAO.searchAll();//全查
	}

	@Override
	public void update(Vehiclemanger vehiclemanger) {
		// TODO Auto-generated method stub
		vehiclemangerDAO.update(vehiclemanger);
	}

}

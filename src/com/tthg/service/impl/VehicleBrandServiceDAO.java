package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IVehicleBrandDAO;
import com.tthg.entity.VehicleBrand;
import com.tthg.service.IVehicleBrandServiceDAO;
@Service
public class VehicleBrandServiceDAO implements IVehicleBrandServiceDAO
{
	
	@Autowired
	private IVehicleBrandDAO vehiclebrandDAO;
	//定义借口对象，用来调用方法

	public IVehicleBrandDAO getVehiclebrandDAO() {
		return vehiclebrandDAO;
	}

	public void setVehiclebrandDAO(IVehicleBrandDAO vehiclebrandDAO) {
		this.vehiclebrandDAO = vehiclebrandDAO;
	}

	@Override
	public void add(VehicleBrand vehiclebrand) {
		// TODO Auto-generated method stub
		vehiclebrandDAO.add(vehiclebrand);//增加
	}

	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		vehiclebrandDAO.delete(ids);//删除
	}

	@Override
	public List<VehicleBrand> searchAll() {
		// TODO Auto-generated method stub
		return vehiclebrandDAO.searchAll();//全查
	}

	@Override
	public List<VehicleBrand> searchVehicleBrand(VehicleBrand vehiclebrand) {
		// TODO Auto-generated method stub
		return vehiclebrandDAO.searchVehicleBrand(vehiclebrand);//按条件查询
	}

	@Override
	public void update(VehicleBrand vehiclebrand) {
		// TODO Auto-generated method stub
		vehiclebrandDAO.update(vehiclebrand);//更新
		
	}

}

package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IVendorDAO;
import com.tthg.entity.Vendor;
import com.tthg.service.IVendorServiceDAO;
@Service
public class VendorServiceDAO implements IVendorServiceDAO{
@Autowired
private IVendorDAO vendorDao;
//定义借口对象，用来调用方法
	public IVendorDAO getVendorDao() {
	return vendorDao;
}

public void setVendorDao(IVendorDAO vendorDao) {
	this.vendorDao = vendorDao;
}

	@Override
	public void add(Vendor vendor) {
		// TODO Auto-generated method stub
		vendorDao.add(vendor);//增加
	}

	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		vendorDao.delete(ids);//删除
		
	}

	@Override
	public List<Vendor> searchAll() {
		// TODO Auto-generated method stub
		return vendorDao.searchAll();//全查
	}

	@Override
	public List<Vendor> searchVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		return vendorDao.searchVendor(vendor);//按条件查询
	}

	@Override
	public void update(Vendor vendor) {
		// TODO Auto-generated method stub
		vendorDao.update(vendor);//更新
	}

}

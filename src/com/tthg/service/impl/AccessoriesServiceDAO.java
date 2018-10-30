package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IAccessoriesDAO;
import com.tthg.entity.Accessories;
import com.tthg.service.IAccessoriesServiceDAO;
@Service
public class AccessoriesServiceDAO implements IAccessoriesServiceDAO{
@Autowired
   private IAccessoriesDAO iaccessoriesDAO;
//定义借口对象，用来调用方法
	public IAccessoriesDAO getIaccessoriesDAO() {
	return iaccessoriesDAO;
}

public void setIaccessoriesDAO(IAccessoriesDAO iaccessoriesDAO) {
	this.iaccessoriesDAO = iaccessoriesDAO;
}

	@Override
	public void add(Accessories accessories) {
		// TODO Auto-generated method stub
		iaccessoriesDAO.add(accessories);//增加
	}

	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		iaccessoriesDAO.delete(ids);//删除
	}

	@Override
	public List<Accessories> searchAccessories(Accessories accessories) {
		// TODO Auto-generated method stub
		return iaccessoriesDAO.searchAccessories(accessories);//按条件查询
	}

	@Override
	public List<Accessories> searchAll() {
		// TODO Auto-generated method stub
		return iaccessoriesDAO.searchAll();//全查
	}

	@Override
	public void update(Accessories accessories) {
		// TODO Auto-generated method stub
		iaccessoriesDAO.update(accessories);//更新
	}

}

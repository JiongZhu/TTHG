package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IAccessoriesBrandDAO;
import com.tthg.entity.AccessoriesBrand;
import com.tthg.service.IAccessoriesBrandServiceDAO;
@Service
public class AccessoriesBrandServiceDAO implements IAccessoriesBrandServiceDAO{
  @Autowired
  private IAccessoriesBrandDAO  accbrandDAO;
  //定义借口对象，用来调用方法
	public IAccessoriesBrandDAO getAccbrandDAO() {
	return accbrandDAO;
}

public void setAccbrandDAO(IAccessoriesBrandDAO accbrandDAO) {
	this.accbrandDAO = accbrandDAO;
}

	@Override
	public void add(AccessoriesBrand accebd) {
		// TODO Auto-generated method stub
		accbrandDAO.add(accebd);//增加
	}

	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		accbrandDAO.delete(ids);//删除
	}

	@Override
	public List<AccessoriesBrand> searchAccessoriesBrand(AccessoriesBrand accebd) {
		// TODO Auto-generated method stub
		return accbrandDAO.searchAccessoriesBrand(accebd);//按条件查
	}

	@Override
	public List<AccessoriesBrand> searchAll() {
		// TODO Auto-generated method stub
		return accbrandDAO.searchAll();//全查
	}

	@Override
	public void update(AccessoriesBrand accebd) {
		// TODO Auto-generated method stub
		accbrandDAO.update(accebd);//更新
	}

}

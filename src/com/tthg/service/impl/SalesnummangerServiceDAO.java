package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.ISalesnummangerDAO;
import com.tthg.entity.Salesnum;
import com.tthg.service.ISalesnummangerServiceDAO;
@Service
public class SalesnummangerServiceDAO implements ISalesnummangerServiceDAO{
@Autowired	
private ISalesnummangerDAO  salesmanDAO;
//定义借口对象，用来调用方法
public ISalesnummangerDAO getSalesmanDAO() {
	return salesmanDAO;
}

public void setSalesmanDAO(ISalesnummangerDAO salesmanDAO) {
	this.salesmanDAO = salesmanDAO;
}

@Override
public void add(Salesnum sanum) {
	// TODO Auto-generated method stub
	salesmanDAO.add(sanum);//增加
}

@Override
public void delete(int[] ids) {
	// TODO Auto-generated method stub
	salesmanDAO.delete(ids);//删除
}

@Override
public List<Salesnum> searchAll() {
	// TODO Auto-generated method stub
	return salesmanDAO.searchAll();//全查
}

@Override
public void update(Salesnum sanum) {
	// TODO Auto-generated method stub
	salesmanDAO.update(sanum);//更新
}

@Override
public List<Salesnum> searchSalesnum(Salesnum salesnum) {
	// TODO Auto-generated method stub
	return salesmanDAO.searchSalesnum(salesnum);
}

}

package com.tthg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.ISalesnumDAO;
import com.tthg.entity.Salesnum;
import com.tthg.service.ISalesServiceDAO;
@Service
public class SalesServiceDAO implements ISalesServiceDAO{
	@Autowired
    private ISalesnumDAO  salesnumDAO;
	//定义借口对象，用来调用方法
	public ISalesnumDAO getSalesnumDAO() {
		return salesnumDAO;
	}

	public void setSalesnumDAO(ISalesnumDAO salesnumDAO) {
		this.salesnumDAO = salesnumDAO;
	}

	@Override
	public Salesnum getnum(int selectyear) {
		// TODO Auto-generated method stub
		return salesnumDAO.getnum(selectyear);//得到数据
	}

}

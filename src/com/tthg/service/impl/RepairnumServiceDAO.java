package com.tthg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IRepairnumDAO;
import com.tthg.entity.Repairnum;
import com.tthg.service.IRepairnumServiceDAO;
@Service
public class RepairnumServiceDAO implements IRepairnumServiceDAO{
	@Autowired
    private IRepairnumDAO repairnumDAO;
	//定义借口对象，用来调用方法
	public IRepairnumDAO getRepairnumDAO() {
		return repairnumDAO;
	}

	public void setRepairnumDAO(IRepairnumDAO repairnumDAO) {
		this.repairnumDAO = repairnumDAO;
	}

	@Override
	public Repairnum getnum(int selectyear) {
		// TODO Auto-generated method stub
		return repairnumDAO.getnum(selectyear);//得到数据
	}

}

package com.tthg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.ITurnoverDAO;
import com.tthg.entity.Turnover;
import com.tthg.service.ITurnoverServiceDAO;
@Service
public class TurnoverServiceDAO implements  ITurnoverServiceDAO{
	@Autowired
    private ITurnoverDAO  turnoverDAO;
	
	public ITurnoverDAO getTurnoverDAO() {
		return turnoverDAO;
	}

	public void setTurnoverDAO(ITurnoverDAO turnoverDAO) {
		this.turnoverDAO = turnoverDAO;
	}

	@Override
	public Turnover getnum(int selectyear) {
		// TODO Auto-generated method stub
		return turnoverDAO.getnum(selectyear);
	}

}

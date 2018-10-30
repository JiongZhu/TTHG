package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.ITurnmangerDAO;
import com.tthg.entity.Turnover;
import com.tthg.service.ITurnovermangerServiceDAO;
@Service
public class TurnovermangerServiceDAO implements ITurnovermangerServiceDAO{
    @Autowired
    private ITurnmangerDAO  itmd;
    
	public ITurnmangerDAO getItmd() {
		return itmd;
	}

	public void setItmd(ITurnmangerDAO itmd) {
		this.itmd = itmd;
	}

	@Override
	public void add(Turnover turnover) {
		// TODO Auto-generated method stub
		itmd.add(turnover);
	}

	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		itmd.delete(ids);
	}

	@Override
	public List<Turnover> searchAll() {
		// TODO Auto-generated method stub
		return itmd.searchAll();
	}

	@Override
	public List<Turnover> searchTurnover(Turnover turnover) {
		// TODO Auto-generated method stub
		return itmd.searchTurnover(turnover);
	}

	@Override
	public void update(Turnover turnover) {
		// TODO Auto-generated method stub
		itmd.update(turnover);
	}

}

package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IReceptionDAO;
import com.tthg.entity.Reception;
import com.tthg.service.IReceptionServiceDAO;
@Service
public class ReceptionServiceDAO implements IReceptionServiceDAO {
	@Autowired
	private IReceptionDAO receptionDAO;

	public IReceptionDAO getReceptionDAO() {
		return receptionDAO;
	}

	public void setReceptionDAO(IReceptionDAO receptionDAO) {
		this.receptionDAO = receptionDAO;
	}
	
	@Override
	public void add(Reception reception) {
		receptionDAO.add(reception);
	}

	@Override
	public void delete(int[] ids) {
		receptionDAO.delete(ids);
	}

	@Override
	public void update(Reception reception) {
		receptionDAO.update(reception);
	}

	@Override
	public List<Reception> searchAll() {
		return receptionDAO.searchAll();
	}

	@Override
	public List<Reception> searchReception(Reception reception) {
		return receptionDAO.searchReception(reception);
	}
}

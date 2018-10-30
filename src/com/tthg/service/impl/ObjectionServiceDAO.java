package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IObjectionDAO;
import com.tthg.entity.Objection;
import com.tthg.service.IObjectionServiceDAO;
@Service
public class ObjectionServiceDAO implements IObjectionServiceDAO {

	@Autowired
	private IObjectionDAO iod;
	
	public IObjectionDAO getIod() {
		return iod;
	}

	public void setIod(IObjectionDAO iod) {
		this.iod = iod;
	}

	@Override
	public void add(Objection objection) {
		iod.add(objection);
	}

	@Override
	public void delete(int[] ids) {
		iod.delete(ids);
	}

	@Override
	public void update(Objection objection) {
		iod.update(objection);
	}

	@Override
	public List<Objection> searchAll() {
		return iod.searchAll();
	}

	@Override
	public List<Objection> searchObjection(Objection objection) {
		return iod.searchObjection(objection);
	}

}

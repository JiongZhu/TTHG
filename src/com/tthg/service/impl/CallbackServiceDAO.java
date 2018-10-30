package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.ICallbackDAO;
import com.tthg.entity.Callback;
import com.tthg.service.ICallbackServiceDAO;
@Service
public class CallbackServiceDAO implements ICallbackServiceDAO {
	@Autowired
	private ICallbackDAO icd;
	@Override
	public void add(Callback callback) {	
		icd.add(callback);
	}

	@Override
	public void delete(int[] ids) {
		icd.delete(ids);
	}

	@Override
	public void update(Callback callback) {
		icd.update(callback);
	}

	@Override
	public List<Callback> searchAll() {
		return icd.searchAll();
	}

	@Override
	public List<Callback> searchCallback(Callback callback) {
		return icd.searchCallback(callback);
	}

}

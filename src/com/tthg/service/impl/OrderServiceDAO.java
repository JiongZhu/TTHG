package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IOrderDAO;
import com.tthg.entity.Order;
import com.tthg.service.IOrderServiceDAO;
@Service
public class OrderServiceDAO implements IOrderServiceDAO{
	
	@Autowired
	private IOrderDAO iod;
	
	public IOrderDAO getIod() {
		return iod;
	}

	public void setIod(IOrderDAO iod) {
		this.iod = iod;
	}

	@Override
	public void add(Order order) {
		iod.add(order);
	}

	@Override
	public void delete(int[] ids) {
		iod.delete(ids);
	}

	@Override
	public void update(Order order) {
		iod.update(order);
	}

	@Override
	public List<Order> searchAll() {
		return iod.searchAll();
	}

	@Override
	public List<Order> searchOrder(Order order) {
		return iod.searchOrder(order);
	}

}

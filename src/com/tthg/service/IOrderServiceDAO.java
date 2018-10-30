package com.tthg.service;

import java.util.List;

import com.tthg.entity.Order;

public interface IOrderServiceDAO {
	void add(Order order);
	void delete(int[] ids);
	void update(Order order);
	List<Order> searchAll();
	List<Order> searchOrder(Order order);
}

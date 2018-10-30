package com.tthg.service;

import java.util.List;

import com.tthg.entity.Accessories;

public interface IAccessoriesServiceDAO {
	void add(Accessories accessories);
	void delete(int[] ids);
	void update(Accessories accessories);
	List<Accessories> searchAll();
	List<Accessories> searchAccessories(Accessories accessories);
}

package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Accessories;

public interface IAccessoriesDAO {
	void add(Accessories accessories);
	void delete(int[] ids);
	void update(Accessories accessories);
	List<Accessories> searchAll();
	List<Accessories> searchAccessories(Accessories accessories);
}

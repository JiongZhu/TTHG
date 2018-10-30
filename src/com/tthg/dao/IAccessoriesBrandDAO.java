package com.tthg.dao;

import java.util.List;

import com.tthg.entity.AccessoriesBrand;

public interface IAccessoriesBrandDAO {
	void add(AccessoriesBrand accebd);
	void delete(int[] ids);
	void update(AccessoriesBrand accebd);
	List<AccessoriesBrand> searchAll();
	List<AccessoriesBrand> searchAccessoriesBrand(AccessoriesBrand  accebd);
}

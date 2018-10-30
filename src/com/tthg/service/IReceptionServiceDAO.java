package com.tthg.service;

import java.util.List;

import com.tthg.entity.Reception;

public interface IReceptionServiceDAO {
	void add(Reception reception);
	void delete(int[] ids);
	void update(Reception reception);
	List<Reception> searchAll();
	List<Reception> searchReception(Reception reception);
}

package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Turnover;

public interface ITurnmangerDAO {
	void add(Turnover  turnover);
	void delete(int[] ids);
	void update(Turnover turnover);
	List<Turnover> searchAll();
	List<Turnover> searchTurnover(Turnover turnover);
}

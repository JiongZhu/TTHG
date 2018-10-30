package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Repairnum;

public interface IRepairnummangerDAO {
	void add(Repairnum repainum);
	void delete(int[] ids);
	void update(Repairnum repairnum);
	List<Repairnum> searchAll();
	List<Repairnum>  searchRepairnum(Repairnum repairnum);
}

package com.tthg.service;

import java.util.List;

import com.tthg.entity.Repairnum;

public interface IRepairnummangerServiceDAO {
	void add(Repairnum repainum);
	void delete(int[] ids);
	void update(Repairnum repairnum);
	List<Repairnum> searchAll();
	List<Repairnum>  searchRepairnum(Repairnum repairnum);
}

package com.tthg.service;

import java.util.List;

import com.tthg.entity.Contract;

public interface IContractionServiceDAO {
	void add(Contract contract);
	void delete(int[] ids);
	void update(Contract contract);
	List<Contract> searchAll();
	List<Contract> searchContract(Contract contract);
}

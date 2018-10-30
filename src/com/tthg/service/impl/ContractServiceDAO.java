package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IContractDAO;
import com.tthg.entity.Contract;
import com.tthg.service.IContractionServiceDAO;
@Service
public class ContractServiceDAO implements IContractionServiceDAO{

	@Autowired
	private IContractDAO icd;
	
	public IContractDAO getIcd() {
		return icd;
	}

	public void setIcd(IContractDAO icd) {
		this.icd = icd;
	}

	@Override
	public void add(Contract contract) {
		icd.add(contract);
	}

	@Override
	public void delete(int[] ids) {
		icd.delete(ids);
	}

	@Override
	public void update(Contract contract) {
		icd.update(contract);
	}

	@Override
	public List<Contract> searchAll() {
		return icd.searchAll();
	}

	@Override
	public List<Contract> searchContract(Contract contract) {
		return icd.searchContract(contract);
	}

}

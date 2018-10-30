package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IIntentionCustomerDAO;
import com.tthg.entity.IntentionCustomer;
import com.tthg.service.IIntentionCustomerServiceDAO;
@Service
public class IntentionCustomerServiceDAO implements
		IIntentionCustomerServiceDAO {
	@Autowired
	private IIntentionCustomerDAO customerDAO;
	
	public IIntentionCustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(IIntentionCustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public void add(IntentionCustomer customer) {
		customerDAO.add(customer);
	}

	@Override
	public void delete(int[] ids) {
		customerDAO.delete(ids);
	}

	@Override
	public void update(IntentionCustomer customer) {
		customerDAO.update(customer);
	}

	@Override
	public List<IntentionCustomer> searchAll() {
		return customerDAO.searchAll();
	}

	@Override
	public List<IntentionCustomer> searchCustomer(IntentionCustomer customer) {
		return customerDAO.searchCustomer(customer);
	}
}

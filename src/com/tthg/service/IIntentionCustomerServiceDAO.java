package com.tthg.service;

import java.util.List;

import com.tthg.entity.IntentionCustomer;

public interface IIntentionCustomerServiceDAO {
	void add(IntentionCustomer customer);
	void delete(int[] ids);
	void update(IntentionCustomer customer);
	List<IntentionCustomer> searchAll();
	List<IntentionCustomer> searchCustomer(IntentionCustomer customer);
}

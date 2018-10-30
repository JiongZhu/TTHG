package com.tthg.service;

import java.util.List;

import com.tthg.entity.Subscription;

public interface ISubscriptionServiceDAO {
	void add(Subscription subscription);
	void delete(int[] ids);
	void update(Subscription subscription);
	List<Subscription> searchAll();
	List<Subscription> searchSubscription(Subscription subscription);
}

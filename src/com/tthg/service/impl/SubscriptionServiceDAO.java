package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.ISubscriptionDAO;
import com.tthg.entity.Subscription;
import com.tthg.service.ISubscriptionServiceDAO;
@Service
public class SubscriptionServiceDAO implements ISubscriptionServiceDAO {

	@Autowired
	private ISubscriptionDAO isd;

	public ISubscriptionDAO getIsd() {
		return isd;
	}

	public void setIsd(ISubscriptionDAO isd) {
		this.isd = isd;
	}
	
	@Override
	public void add(Subscription subscription) {
		isd.add(subscription);
	}

	@Override
	public void delete(int[] ids) {
		isd.delete(ids);
	}

	@Override
	public void update(Subscription subscription) {
		isd.update(subscription);
	}

	@Override
	public List<Subscription> searchAll() {
		return isd.searchAll();
	}

	@Override
	public List<Subscription> searchSubscription(Subscription subscription) {
		return isd.searchSubscription(subscription);
	}
}

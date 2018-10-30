package com.tthg.service;

import java.util.List;

import com.tthg.entity.Callback;

public interface ICallbackServiceDAO {
	void add(Callback callback);
	void delete(int[] ids);
	void update(Callback callback);
	List<Callback> searchAll();
	List<Callback> searchCallback(Callback callback);
}

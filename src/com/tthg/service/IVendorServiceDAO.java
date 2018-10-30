package com.tthg.service;

import java.util.List;

import com.tthg.entity.Vendor;

public interface IVendorServiceDAO {
	void add(Vendor vendor);
	void delete(int[] ids);
	void update(Vendor vendor);
	List<Vendor> searchAll();
	List<Vendor> searchVendor(Vendor  vendor);
}

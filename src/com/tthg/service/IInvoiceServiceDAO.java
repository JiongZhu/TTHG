package com.tthg.service;

import java.util.List;

import com.tthg.entity.Invoice;

public interface IInvoiceServiceDAO {
	void add(Invoice invoice);
	void delete(int[] ids);
	void update(Invoice invoice);
	List<Invoice> searchAll();
	List<Invoice> searchInvoice(Invoice invoice);
}

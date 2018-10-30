package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IInvoiceDAO;
import com.tthg.entity.Invoice;
import com.tthg.service.IInvoiceServiceDAO;
@Service
public class InvoiceServiceDAO implements IInvoiceServiceDAO {

	@Autowired
	private IInvoiceDAO iid;
	
	public IInvoiceDAO getIid() {
		return iid;
	}

	public void setIid(IInvoiceDAO iid) {
		this.iid = iid;
	}

	@Override
	public void add(Invoice invoice) {
		iid.add(invoice);
	}

	@Override
	public void delete(int[] ids) {
		iid.delete(ids);
	}

	@Override
	public void update(Invoice invoice) {
		iid.update(invoice);
	}

	@Override
	public List<Invoice> searchAll() {
		return iid.searchAll();
	}

	@Override
	public List<Invoice> searchInvoice(Invoice invoice) {
		return iid.searchInvoice(invoice);
	}

}

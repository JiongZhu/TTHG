package com.tthg.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.IInvoiceDAO;
import com.tthg.entity.Contract;
import com.tthg.entity.Invoice;
import com.tthg.entity.Order;
@Repository
public class InvoiceDAO implements IInvoiceDAO {
	
	@Autowired
	private SessionFactory sessionFactory;	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(Invoice invoice) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
        Order order=(Order)session.get(Order.class,invoice.getOrderId());
        invoice.setOrder(order);
        invoice=(Invoice)session.merge(invoice);
		session.save(invoice);	
		trans.commit();
		session.close();
	}

	@Override
	public void delete(int[] ids) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		for(int i=0;i<ids.length;i++){
			Order ic=(Order)session.get(Order.class, ids[i]);
			session.delete(ic);
		}
		trans.commit();
		session.close();
	}

	@Override
	public void update(Invoice invoice) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
        Order order=(Order)session.get(Order.class,invoice.getOrderId());
        invoice.setOrder(order);
        invoice=(Invoice)session.merge(invoice);
		session.update(invoice);	
		trans.commit();
	}

	@Override
	public List<Invoice> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from Invoice";
		Query query=session.createQuery(hql);
		List<Invoice> list=query.list();
		for(int i=0;i<list.size();i++){
			Invoice invoice=list.get(i);
			invoice.setOid(invoice.getOrder().getId());
		}
		session.close();		
		return list;
	}

	@Override
	public List<Invoice> searchInvoice(Invoice invoice) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Invoice i left join fetch i.order o where 1=1 ");
		Map map=new HashMap();
		if(!(invoice.getInvoiceNo()==null||"".equals(invoice.getInvoiceNo()))){
			map.put("InvoiceNo", "%"+invoice.getInvoiceNo()+"%");
			hql.append(" and i.invoiceNo like:InvoiceNo");
		}
		if(invoice.getOrderId()!=null){
			map.put("OrderId", invoice.getOrderId());
			hql.append(" and o.id=:OrderId");
		}
		if(invoice.getInvTime()!=null){
			map.put("InvTime", invoice.getInvTime());
			hql.append(" and i.invTime=:InvTime");
		}
		String hqlStr=hql.toString();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hqlStr);		
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));
		}
		List<Invoice> list=query.list();
		for(int i=0;i<list.size();i++){
			Invoice inv=list.get(i);
			inv.setOid(inv.getOrder().getId());
		}
		session.close();
		return list;
	}

}

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

import com.tthg.dao.IContractDAO;
import com.tthg.entity.Contract;
import com.tthg.entity.Order;
@Repository
public class ContractDAO implements IContractDAO {
	
	@Autowired
	private SessionFactory sessionFactory;	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(Contract contract) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
        Order order=(Order)session.get(Order.class,contract.getOrderId());
        contract.setOrder(order);
        contract=(Contract)session.merge(contract);
		session.save(contract);	
		trans.commit();
		session.close();
	}

	@Override
	public void delete(int[] ids) {//前台支持多选删除，将待删除记录的id放到整型数组传递到后台
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		for(int i=0;i<ids.length;i++){
			Contract ic=(Contract)session.get(Contract.class, ids[i]);
			session.delete(ic);
		}
		trans.commit();
		session.close();
	}

	@Override
	public void update(Contract contract) {
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
        Order order=(Order)session.get(Order.class,contract.getOrderId());
        contract.setOrder(order);
        contract=(Contract)session.merge(contract);
    	session.update(contract);	
    	trans.commit();
		session.close();
	}

	@Override
	public List<Contract> searchAll() {
		Session session=sessionFactory.openSession();
		String hql="from Contract";
		Query query=session.createQuery(hql);
		List<Contract> list=query.list();
		for(int i=0;i<list.size();i++){
			Contract contract=list.get(i);
			contract.setOid(contract.getOrder().getId());
		}
		session.close();		
		return list;
	}

	@Override
	public List<Contract> searchContract(Contract contract) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Contract c where 1=1 ");
		Map map=new HashMap();
		if(contract.getContractNo()!=null){
			map.put("ContractNo", "%"+contract.getContractNo()+"%");
			hql.append(" and c.contractNo like:ContractNo");
		}
		if(contract.getContractTime()!=null){
			map.put("ContractTime", contract.getContractTime());
			hql.append(" and c.contractTime=:ContractTime");
		}
		String hqlStr=hql.toString();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hqlStr);		
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));
		}
		List<Contract> list=query.list();
		for(int i=0;i<list.size();i++){
			Contract con=list.get(i);
			con.setOid(con.getOrder().getId());
		}
		session.close();
		return list;
	}

}

package com.tthg.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.ITreeDAO;
import com.tthg.entity.Order;
import com.tthg.entity.Tree;
@Repository
public class TreeDAO implements ITreeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Tree> getAllTree() {
		Session session=sessionFactory.openSession();
		String hql="from Tree";
		Query query=session.createQuery(hql);
		List<Tree> list=query.list();
		session.close();
		return list;
	}

	@Override
	public List<Tree> getById(int[] ids) {
		Session session=sessionFactory.openSession();
		String hql="from Tree t where t.id=?";
		Query query=session.createQuery(hql);
		List<Tree> list=new ArrayList<Tree>();
		for(int i=0;i<ids.length;i++){
			query.setInteger(0, ids[i]);
			list.add((Tree)query.list().get(0));
		}		
		session.close();
		return list;
	}
	
}

package com.tthg.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.ITurnoverDAO;
import com.tthg.entity.Turnover;

@Repository
public class TurnoverDAO implements  ITurnoverDAO{
	@Autowired
	private SessionFactory  sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Turnover getnum(int selectyear) {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.openSession();//开启一个Session
		String hql="from Turnover tu where tu.id=?";//定义查询语句
		Query query=session.createQuery(hql);//使用Query 类中调用session执行查询语句
		query.setInteger(0, selectyear);
		List<Turnover> list=query.list();//把查询出来的数据放入list中
		Turnover turno=new Turnover();//定义一个实体对象
		turno=(Turnover)list.get(0);//得到实体对象
		session.close();//关闭sesison
		return turno;//返回对象
	}
	
}

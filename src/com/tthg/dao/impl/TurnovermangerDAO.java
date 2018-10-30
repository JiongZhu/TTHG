package com.tthg.dao.impl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.ITurnmangerDAO;
import com.tthg.entity.Turnover;
@Repository
public class TurnovermangerDAO implements ITurnmangerDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(Turnover turnover) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//开始一个事物		
		session.save(turnover);//执行保存操作
		trans.commit();//提交事物执行成功
		session.close();//关闭session
	}

	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//开始一个事物	
		for(int i=0;i<ids.length;i++){//遍历数组
			Turnover turnov=(Turnover)session.get(Turnover.class, ids[i]);//得到含有对象的数组
			session.delete(turnov);//执行删除操作
		}
		trans.commit();//提交事物
		session.close();//关闭session
	}

	@Override
	public List<Turnover> searchAll() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		String hql="from Turnover";//定义全查语句，Turnover代表的是实体对象
		Query query=session.createQuery(hql);//用Query对象进行执行hql语句
		List<Turnover> list=query.list();//执行list
		session.close();//关闭session		
		return list;//返回的list列表是放入了查询出来对象的值
	}

	@Override
	public List<Turnover> searchTurnover(Turnover turnover) {
		// TODO Auto-generated method stub
		String hql="from Turnover as tu where tu.id=?";
		Session session=sessionFactory.openSession();
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setInteger(0, turnover.getId());
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		return list;
	
	}

	@Override
	public void update(Turnover turnover) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//开始一个事物	
		session.update(turnover);//执行更新操作
		trans.commit();//提交事物
		session.close();//关闭session
	}

}

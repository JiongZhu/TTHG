package com.tthg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.ISalesnummangerDAO;
import com.tthg.entity.Salesnum;
@Repository
public class SalesnummangerDAO implements ISalesnummangerDAO{
	@Autowired
	private SessionFactory sessionFactory;	
    //定义一个SessionFactory类的对象，并且写出get和set方法
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//Dao层的增加方法
	@Override
	public void add(Salesnum sanum) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//用session开启一个事物	
		session.save(sanum);//执行保存操作
		trans.commit();//提交事物才能实现功能
		session.close();//关闭session
	}
	//Dao层的删除方法  此方法是把对象作为数组元素放入数组中，进行批量删除
	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//用session开启一个事物	
		for(int i=0;i<ids.length;i++){//遍历数组
			Salesnum ss=(Salesnum)session.get(Salesnum.class, ids[i]);//得到含有对象的数组
			session.delete(ss);//执行删除操作
		}
		trans.commit();//提交事物才能实现功能
		session.close();//关闭Session
	}
	//全查方法  把查询出来的对象放入List列表中
	@Override
	public List<Salesnum> searchAll() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		String hql="from Salesnum";//定义全查语句，Salesnum代表的是实体对象
		Query query=session.createQuery(hql);//用Query对象进行执行hql语句
		List<Salesnum> list=query.list();//执行list方法
		session.close();//关闭Session		
		return list;//返回的list列表是放入了查询出来对象的值
	}
	//更新方法，通过更新实体对象的形式进行更新数据
	@Override
	public void update(Salesnum sanum) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//用session开启一个事物	
		session.update(sanum);//执行更新操作
	    trans.commit();//提交事物才能实现功能
	    session.close();//关闭Session		
	}

	@Override
	public List<Salesnum> searchSalesnum(Salesnum salesnum) {
		// TODO Auto-generated method stub
		String hql="from Salesnum as s where s.id=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setInteger(0, salesnum.getId());
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}
}

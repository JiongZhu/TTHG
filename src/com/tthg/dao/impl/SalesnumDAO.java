package com.tthg.dao.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionContext;
import com.tthg.dao.ISalesnumDAO;
import com.tthg.entity.Salesnum;
import com.tthg.util.HibernateSessionFactory;
@Repository
public class SalesnumDAO implements  ISalesnumDAO{
	@Autowired
	private SessionFactory  sessionFactory;
	   //定义一个SessionFactory类的对象，并且写出get和set方法
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//这个方法是用来显示柱形图的查询方法
	@Override
	public Salesnum getnum(int selectyear) {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.openSession();//开启一个Session
		String hql="from Salesnum sal where sal.id=?";//定义查询语句
		Query query=session.createQuery(hql);//使用Query 类中调用session执行查询语句
		query.setInteger(0, selectyear);
		List<Salesnum> list=query.list();//把查询出来的数据放入list中
		Salesnum sa=new Salesnum();//定义一个实体对象
		sa=(Salesnum)list.get(0);//关闭sesison
		session.close();//关闭sesison
		return sa;//返回对象
	}
}

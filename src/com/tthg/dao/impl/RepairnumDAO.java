package com.tthg.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.IRepairnumDAO;
import com.tthg.entity.Repairnum;
import com.tthg.entity.Salesnum;
@Repository
public class RepairnumDAO implements IRepairnumDAO{
@Autowired	
private SessionFactory  sessionFactory;
	//定义SessionFactory类并设置get和set方法
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//这个方法是用来显示柱形图的查询方法
	@Override
	public Repairnum getnum(int selectyear) {
		// TODO Auto-generated method stub
		Session  session=sessionFactory.openSession();//开启一个Session
		String hql="from Repairnum rep where rep.id=?";//定义查询语句
		Query query=session.createQuery(hql);//使用Query 类中调用session执行查询语句
		query.setInteger(0, selectyear);
		List<Repairnum> list=query.list();//把查询出来的数据放入list中
		Repairnum ranum=new Repairnum();//定义一个实体对象
		ranum=(Repairnum)list.get(0);//得到实体对象
		session.close();//关闭sesison
		return ranum;//返回对象
	}

}

package com.tthg.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.IRepairnummangerDAO;
import com.tthg.entity.Repairnum;
@Repository
public class RepairnummangerDAO implements IRepairnummangerDAO{
    @Autowired
    private SessionFactory sessionFactory;
  //定义SessionFactory类并设置get和set方法
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
//增加维修表中的数据
	@Override
	public void add(Repairnum repainum) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//开始一个事物		
		session.save(repainum);//执行保存操作
		trans.commit();//提交事物执行成功
		session.close();//关闭session
	}
	//Dao层的删除方法  此方法是把对象作为数组元素放入数组中，进行批量删除
	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//开始一个事物	
		for(int i=0;i<ids.length;i++){//遍历数组
			Repairnum renum=(Repairnum)session.get(Repairnum.class, ids[i]);//得到含有对象的数组
			session.delete(renum);//执行删除操作
		}
		trans.commit();//提交事物
		session.close();//关闭session
	}
	//全查方法  把查询出来的对象放入List列表中
	@Override
	public List<Repairnum> searchAll() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		String hql="from Repairnum";//定义全查语句，Repairnum代表的是实体对象
		Query query=session.createQuery(hql);//用Query对象进行执行hql语句
		List<Repairnum> list=query.list();//执行list方法
		session.close();//关闭session		
		return list;//返回的list列表是放入了查询出来对象的值
	}
	//更新方法，通过更新实体对象的形式进行更新数据
	@Override
	public void update(Repairnum repairnum) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//开始一个事物	
		session.update(repairnum);//执行更新操作
		trans.commit();//提交事物
		session.close();//关闭session
	}

	@Override
	public List<Repairnum> searchRepairnum(Repairnum repairnum) {
		// TODO Auto-generated method stub
		String hql="from Repairnum as r where r.id=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setInteger(0, repairnum.getId());
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		return list;
	}

}

package com.tthg.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.ICarDAO;
import com.tthg.entity.Car;
@Repository
public class CarDAO implements ICarDAO{
	@Autowired
	private SessionFactory sessionFactory;	
	 //定义一个SessionFactory类的对象，并且写出get和set方法
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//Dao层的增加方法车辆 
	@Override
	public void add(Car car) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//用session开启一个事物
		session.save(car);//执行保存操作
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
			Car c=(Car)session.get(Car.class, ids[i]);//得到含有对象的数组
			session.delete(c);//执行删除操作
		}
		trans.commit();//执行保存操作
		session.close();//提交事物才能实现功能
	}
	//全查方法  把查询出来的对象放入List列表中
	@Override
	public List<Car> searchAll() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		String hql="from Car";//定义全查语句，Car代表的是实体对象
		Query query=session.createQuery(hql);//用Query对象进行执行hql语句
        List<Car> list=query.list();//执行list方法
	    session.close();//关闭session	
		return list;//返回的list列表是放入了查询出来对象的值

	}
	//查询方法
	@Override
	public List<Car> searchCar(Car car) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();//定义一个StringBuffer对象
		hql.append("from Car as c where 1=1");
		Map map=new HashMap();//定义一个Map

		if(!(car.getCarNo()==null||"".equals(car.getCarNo()))){//进车辆编号为根据进行根据查询，判断是否为空
			map.put("CarNo", "%"+car.getCarNo()+"%");//放入map中
			hql.append(" and c.carNo like:CarNo");//追加查询语句
		}
		String hqlStr=hql.toString();//把hql类型转换为String类型
		Session session=sessionFactory.openSession();//开启Session
		Query query=session.createQuery(hqlStr);//用Query对象进行执行hql语句
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {//遍历map
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));//取出map中的值
		}
		List<Car> list=query.list();//执行list方法
		session.close();//关闭session
	    return list;//返回的list列表是放入了查询出来对象的值
		
	}
	//更新方法，通过更新实体对象的形式进行更新数据
	@Override
	public void update(Car car) {
		// TODO Auto-generated method stub
	    Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//开启一个事物
		session.update(car);//执行鞥更新操作
	    trans.commit();//提交事物
		session.close();//关闭session
	}

}

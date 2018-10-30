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

import com.tthg.dao.IAccessoriesBrandDAO;
import com.tthg.entity.AccessoriesBrand;
@Repository
public class AccessoriesBrandDAO implements IAccessoriesBrandDAO{
    @Autowired
    private SessionFactory sessionFactory;	
    //定义一个SessionFactory类的对象，并且写出get和set方法
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
//Dao层的增加方法 配件品牌 
	@Override
	public void add(AccessoriesBrand accebd) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//用session开启一个事物
		session.save(accebd);//执行保存操作
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
			AccessoriesBrand ab=(AccessoriesBrand)session.get(AccessoriesBrand.class, ids[i]);//得到含有对象的数组
			session.delete(ab);//执行删除操作
		}
		trans.commit();//提交事物
		session.close();//关闭session
	}
//查询方法
	@Override
	public List<AccessoriesBrand> searchAccessoriesBrand(AccessoriesBrand accebd) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();//定义一个StringBuffer对象，
		hql.append("from AccessoriesBrand as a where 1=1");
		Map map=new HashMap();//定义一个Map
		if(!(accebd.getBrandNo()==null||"".equals(accebd.getBrandNo()))){//进配件编号为根据进行根据查询，判断是否为空
			map.put("accBrandNo", "%"+accebd.getBrandNo()+"%");//放入map中
			hql.append(" and a.brandNo like:accBrandNo");//在StringBuffer中追加查询语句
		}
		if(!(accebd.getBrandName()==null||"".equals(accebd.getBrandName()))){//进配件名称为根据进行根据查询，判断是否为空
			map.put("accBrandNa", "%"+accebd.getBrandName()+"%");//放入map中
			hql.append(" and a.brandName like:accBrandNa");//在StringBuffer中追加查询语句
		}
		String hqlStr=hql.toString();//把hql类型转换为String类型
		Session session=sessionFactory.openSession();//开启Session
		Query query=session.createQuery(hqlStr);//用Query对象进行执行hql语句
		Iterator it = map.keySet().iterator();//遍历map
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));//把map中的值全取出来
		}
		List<AccessoriesBrand> list=query.list();//执行list方法
		session.close();//关闭session
		return list;//返回的list列表是放入了查询出来对象的值
		
	}
//全查方法  把查询出来的对象放入List列表中
	@Override
	public List<AccessoriesBrand> searchAll() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
    	String hql="from AccessoriesBrand";//定义全查语句，AccessoriesBrand代表的是实体对象
		Query query=session.createQuery(hql);//用Query对象进行执行hql语句
		List<AccessoriesBrand>  list=query.list();//执行list方法
		session.close();//关闭session	
		return list;//返回的list列表是放入了查询出来对象的值
	
	}
//更新方法，通过更新实体对象的形式进行更新数据
	@Override
	public void update(AccessoriesBrand accebd) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();//开启一个session
		Transaction trans=session.beginTransaction();//开启一个事物
		session.update(accebd);//执行更新操作
		trans.commit();//提交事物
		session.close();//关闭session
	}

}

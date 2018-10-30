package com.tthg.dao.impl;

import java.util.Date;
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

import com.tthg.dao.IAccessoriesDAO;
import com.tthg.entity.Accessories;

@Repository
public class AccessoriesDAO implements IAccessoriesDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 //定义一个SessionFactory类的对象，并且写出get和set方法
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//Dao层的增加方法 配件 
	@Override
	public void add(Accessories accessories) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();//开启一个session
		Transaction trans = session.beginTransaction();//用session开启一个
		session.save(accessories);//执行保存操作
		trans.commit();//提交事物才能实现功能
		session.close();//关闭session
	}
	//Dao层的删除方法  此方法是把对象作为数组元素放入数组中，进行批量删除
	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();//开启一个session
		Transaction trans = session.beginTransaction();//用session开启一个
		for (int i = 0; i < ids.length; i++) {//遍历数组
			Accessories ac = (Accessories) session.get(Accessories.class,ids[i]);//得到含有对象的数组
			session.delete(ac);//执行删除操作
		}
		trans.commit();//提交事物
		session.close();//关闭session
	}
	//查询方法
	@Override
	public List<Accessories> searchAccessories(Accessories accessories) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();//定义一个StringBuffer对象，
		hql.append("from Accessories as ac where 1=1 ");
		Map map = new HashMap();
		if (!(accessories.getAcceNo() == null || "".equals(accessories.getAcceNo()))) {//进配件编号品牌为根据进行根据查询，判断是否为空
			map.put("AcceNo", "%" + accessories.getAcceNo() + "%");//放入map中
			hql.append(" and ac.acceNo like:AcceNo");//在StringBuffer中追加查询语句
		}
		if (!(accessories.getAcceName() == null || "".equals(accessories.getAcceName()))) {//进配件编号配件品牌为根据进行根据查询，判断是否为空
			map.put("AcceName", "%" + accessories.getAcceName() + "%");//放入map中
			hql.append(" and ac.acceName like:AcceName");//在StringBuffer中追加查询语句
		}
		if (!(accessories.getCarArea() == null || "".equals(accessories.getCarArea()))) {//进配件编号配件品牌所属车辆的产地为根据进行根据查询，判断是否为空
			map.put("CarArea", "%" + accessories.getCarArea() + "%");//放入map中
			hql.append(" and ac.carArea like:CarArea");//在StringBuffer中追加查询语句
		}
		String hqlStr=hql.toString();//把hql类型转换为String类型
		Session session=sessionFactory.openSession();//开启Session
		Query query=session.createQuery(hqlStr);//用Query对象进行执行hql语句
		Iterator it = map.keySet().iterator();//遍历map
		while (it.hasNext()) {
			Object key = it.next();
			query.setParameter(key.toString(), map.get(key));//把map中的值全取出来
		}
		List<Accessories> list=query.list();//执行list方法
		session.close();//关闭session
		return list;//返回的list列表是放入了查询出来对象的值
	}
	//全查方法  把查询出来的对象放入List列表中
	@Override
	public List<Accessories> searchAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();//开启一个session
		String hql = "from Accessories";//定义全查语句，Accessories代表的是实体对象
		Query query = session.createQuery(hql);//用Query对象进行执行hql语句
		List<Accessories> list = query.list();//执行list方法
		session.close();//关闭session
		return list;//返回的list列表是放入了查询出来对象的值
	}
	//更新方法，通过更新实体对象的形式进行更新数据
	@Override
	public void update(Accessories accessories) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();//开启一个session
		Transaction trans = session.beginTransaction();//开启一个事物
		session.update(accessories);//执行更新操作
		trans.commit();//提交事物
		session.close();//关闭session
	}

}

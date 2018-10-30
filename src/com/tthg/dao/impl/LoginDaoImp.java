package com.tthg.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.LoginDao;
import com.tthg.entity.User;
/**
 * 注册登录dao层实现类
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
@Repository
public class LoginDaoImp implements LoginDao{

	@Autowired
	private SessionFactory sessionFactory;
	public LoginDaoImp(){
		
		
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean isLogin(String username,String password) {
		boolean result=false;
		System.out.println("进入dao层");
		Session session=sessionFactory.openSession();
		
		String hql="from User as u where u.uname=? and u.passward=?";
		System.out.println("session"+session);
		Query query=session.createQuery(hql);
		System.out.println("sfsdgs");
		query.setString(0,username);
		query.setString(1,password);
		List list=query.list();
		if(list.size()>0){
			System.out.println("进入list");
			result=true;	
		}
		session.close();
		return result;
	}

	@Override
	public void adduser(User u) {
	
		System.out.println("进入dao层add方法");
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.save(u);
		System.out.println("dao中username"+u.getUno());
		tx.commit();
		session.close();
		
	}

}

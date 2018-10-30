package com.tthg.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.IPageDao;
/**
 * 分页实现类
 * @author 葛康  编写者
 * @since 2016-12-10 编写时间
 *
 */
@Repository
public class PageDao implements IPageDao {
	@Autowired
	private SessionFactory sessionFactory;//配置sessionFactory

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}  
	//分页获取数据
	@Override
	public List getList(String url,String page, String rows) {
		//当为缺省值的时候进行赋值  
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "5": rows);//每页多少行  
		String hql=url;
		System.out.println(url);
		Session session=sessionFactory.openSession();

		Query query=session.createQuery(hql);
		List list=null;
		int firstNumber=(currentpage-1)*pagesize; //第pageNo页的第一条记录
		System.out.println("firstNumber="+firstNumber);
		query.setFirstResult(firstNumber); //设置第PageNo的第一条记录
		query.setMaxResults(pagesize);//获取第PageNo的最大记录数
		list=query.list(); 

		session.close();
		return list;
	}
	
}

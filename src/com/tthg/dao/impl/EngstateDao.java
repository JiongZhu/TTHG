package com.tthg.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.IEngstateDao;
import com.tthg.entity.Engstate;
import com.tthg.entity.Repair;
import com.tthg.service.IPageService;
import com.tthg.service.IRepairService;
/**
 * 工程师状态实现类
 * @author 葛康  编写者
 * @since 2016-12-29 编写时间
 *
 */
@Repository
public class EngstateDao implements IEngstateDao {
	@Autowired
	private SessionFactory sessionFactory;//装载sessionFactory

	@Autowired
	private IPageService pageService;//分页服务层接口
	@Autowired
	private IRepairService repairService;//维修登记服务层接口

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public IPageService getPageService() {
		return pageService;
	}

	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}

	public IRepairService getRepairService() {
		return repairService;
	}

	public void setRepairService(IRepairService repairService) {
		this.repairService = repairService;
	}
	//删除工程师状态
	@Override
	public boolean deleteEngstate(int[] ids) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			for(int i=0;i<ids.length;i++){
				//获取实体
				Engstate engstate=(Engstate) this.getEngstateById(ids[i]).get(0);
				session.delete(engstate);//执行删除操作
			}
			trans.commit();//提交事务
			trans=null;
		}catch(Exception e){
			e.printStackTrace();
			if(trans!=null){
				trans.rollback();
			}
		}
		session.close();//关闭session
		return result;
	}
	//全查工程师状态
	@Override
	public List<Engstate> getAllEngstate() {
		String hql="from Engstate eng";
		Session session=sessionFactory.openSession();//创建session对象
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//复合查询
	@Override
	public List getEngstateByComposition(Engstate engstate) {
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession se=request.getSession();//创建session对象
		StringBuffer hql=new StringBuffer();

		//外键查询
		hql.append("from Engstate eng left join fetch eng.repair re where 1=1");
		if((null!=engstate.getExtreNo()&&!"".equals(engstate.getExtreNo()))){
			int id=repairService.getRepairByNo(engstate.getExtreNo()).get(0).getId();
			hql.append(" and re.id ="+id);
		}
		if((null!=engstate.getEdate()&!"".equals(engstate.getEdate())&&null!=engstate.getEnDate()&&!"".equals(engstate.getEnDate()))){
			hql.append(" and eng.edate >= '"+engstate.getEdate()+"' and eng.edate <='"+engstate.getEnDate()+"'");
		}
		if((null!=engstate.getState()&&!"".equals(engstate.getState()))){
			System.out.println("eerfetregr"+engstate.getState());
			hql.append(" and eng.state = "+engstate.getState());
		}
		String hqlStr=hql.toString();
		se.setAttribute("hqlStr", hqlStr);
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hqlStr);
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		return list;
	}

	//根据id查询工程师状态
	@Override
	public List<Engstate> getEngstateById(Integer id) {
		String hql="from Engstate eng where eng.id=?";
		Session session=sessionFactory.openSession();//开启session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		query.setInteger(0, id);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//根据no查询状态
	@Override
	public List<Engstate> getEngstateByNo(String no) {
		String hql="from Engstate eng where eng.uno=?";
		Session session=sessionFactory.openSession();//创建session对象
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		query.setString(0, no);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//添加状态
	@Override
	public boolean insertEngstate(Engstate engstate) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session对象
		Transaction trans=session.beginTransaction();//开启事物
		//获取维修登记信息
		Repair repair=repairService.getRepairByNo(engstate.getExtreNo()).get(0);//根据维修编号获取部门对象
		repair.setEngstate(engstate);	
		engstate.setRepair(repair);//创建关系
		session.save(engstate);
		result=true;
		trans.commit();//提交事物
		session.close();//关闭session
		return result;
	}

	//修改状态
	@Override
	public boolean updateEngstate(Integer reid, Engstate engstate) {
		boolean result=false;
		Session session = sessionFactory.openSession();//创建session对象
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			//获取维修登记信息
			Repair repair=repairService.getRepairByNo(engstate.getExtreNo()).get(0);
			engstate.setRepair(repair);
			Engstate eng=(Engstate) session.merge(engstate);//合并
			//session.clear();//清除session缓存
			session.update(eng);
			trans.commit();//提交事物
			trans=null;
		}catch(Exception e){
			e.printStackTrace();
			if(trans!=null){
				trans.rollback();
			}
		}
		session.close();//关闭session
		return result;
	}  
}

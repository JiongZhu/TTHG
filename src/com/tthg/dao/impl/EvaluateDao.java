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

import com.tthg.dao.IEvaluateDao;
import com.tthg.entity.Evaluate;
import com.tthg.entity.Repair;
import com.tthg.service.IRepairService;
/**
 * 维修评估实现类
 * @author 葛康  编写者
 * @since 2016-12-29 编写时间
 *
 */
@Repository
public class EvaluateDao implements IEvaluateDao {
	@Autowired
	private SessionFactory sessionFactory;//装配sessionFactory

	@Autowired
	private IRepairService repairService;//维修登记服务层接口
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public IRepairService getRepairService() {
		return repairService;
	}

	public void setRepairService(IRepairService repairService) {
		this.repairService = repairService;
	}

	//根据id删除评估
	@Override
	public boolean deleteEvaluate(int[] ids) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			for(int i=0;i<ids.length;i++){
			//通过id获取评估实体
			Evaluate evaluate=(Evaluate) this.getEvaluateById(ids[i]).get(0);
//			evaluate.setRepair(null);
			session.delete(evaluate);//删除评估
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

	//全查评估
	@Override
	public List<Evaluate> getAllEvaluate() {
		String hql="from Evaluate ev";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//通过id查询获取实体
	@Override
	public List<Evaluate> getEvaluateById(Integer id) {
		String hql="from Evaluate ev where ev.id=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		query.setInteger(0, id);
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}

	//通过no查询获取实体
	@Override
	public List<Evaluate> getEvaluateByNo(String no) {
		String hql="from Evaluate eva where eva.evNo=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		query.setString(0, no);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//添加评估
	@Override
	public boolean insertEvaluate(Evaluate evaluate,Integer reid) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=session.beginTransaction();//开启事物
		Repair repair=repairService.getRepairByNo(evaluate.getReNo()).get(0);//根据维修id获取部门对象
		if (this.getEvaluateByNo(evaluate.getEvNo()).size()<1) {//判断插入的评估是否存在
			repair.setEvaluate(evaluate);	
			evaluate.setRepair(repair);//创建关系
			session.save(evaluate);
			result=true;
		}else{
			System.out.println("评估信息存在，不能添加");
		}
		trans.commit();//提交事物
		session.close();//关闭session
		return result;
	}

	//修改评估
	@Override
	public boolean updateEvaluate(Integer reid, Evaluate evaluate) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session对象
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			if (this.getEvaluateById(evaluate.getId()).size()>0) {
				//获取维修登记信息
				Repair repair=repairService.getRepairByNo(evaluate.getReNo()).get(0);
				evaluate.setRepair(repair);
				Evaluate ev=(Evaluate) session.merge(evaluate);//合并
				//session.clear();//清除session缓存
				session.update(ev);
				result=true;
			}
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

	//组合查询
	@Override
	public List getEvaluateByComposition(Evaluate evaluate) {
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		HttpSession se=request.getSession();//创建session对象
		StringBuffer hql=new StringBuffer();

		//外键查询
		hql.append("from Evaluate ev where 1=1");
		if((null!=evaluate.getUname()&&!"".equals(evaluate.getUname()))){
			hql.append(" and ev.uname like '%"+evaluate.getUname()+"%'");
		}
		if((null!=evaluate.getPlateNo()&&!"".equals(evaluate.getPlateNo()))){
			hql.append(" and ev.plateNo = "+evaluate.getPlateNo());
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
}

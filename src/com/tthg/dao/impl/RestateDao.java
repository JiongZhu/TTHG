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

import com.tthg.dao.IRestateDao;
import com.tthg.entity.Restate;
import com.tthg.entity.Trouble;
import com.tthg.service.ITroubleService;
/**
 * 维修状态实现类
 * @author 葛康  编写者
 * @since 2016-12-29 编写时间
 *
 */
@Repository
public class RestateDao implements IRestateDao {
	@Autowired
	private SessionFactory sessionFactory;//装配sessionFactory

	@Autowired
	private ITroubleService troubleService;//故障维修服务层接口

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ITroubleService getTroubleService() {
		return troubleService;
	}

	public void setTroubleService(ITroubleService troubleService) {
		this.troubleService = troubleService;
	}

	//删除状态
	@Override
	public boolean deleteRestate(int[] ids) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			for(int i=0;i<ids.length;i++){
			Restate restate=this.getRestateById(ids[i]).get(0);
//			restate.setTrouble(null);
			session.delete(restate);
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

	//全查状态
	@Override
	public List<Restate> getAllRestate() {
		String hql="from Restate res";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//根据id查询
	@Override
	public List<Restate> getRestateById(Integer id) {
		String hql="from Restate res where res.id=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//根据编号查询
	@Override
	public List<Restate> getRestateByNo(String no) {
		String hql="from Restate res where res.proNo=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setString(0, no);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//添加状态
	@Override
	public boolean insertRestate(Restate restate, Integer troid) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=session.beginTransaction();//开启事物
		//根据维修no获取部门对象
		Trouble trouble=troubleService.getTroubleByNo(restate.getTroNo()).get(0);
		if (this.getRestateByNo(restate.getProNo()).size()<1) {//判断插入的评估是否存在
			trouble.setRestate(restate);	
			restate.setTrouble(trouble);
			session.save(restate);
			result=true;
		}else{
			System.out.println("评估信息存在，不能添加");
		}
		trans.commit();//提交事物
		session.close();//关闭session
		return result;
	}

	//修改状态
	@Override
	public boolean updateRestate(Integer troid, Restate restate) {
		boolean result=false;
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
			if (this.getRestateByNo(restate.getProNo()).size()>0) {
				//根据维修no获取部门对象
				Trouble trouble=troubleService.getTroubleByNo(restate.getTroNo()).get(0);//根据维修id获取部门对象
				restate.setTrouble(trouble);
				Restate res=(Restate) session.merge(restate);//合并
				//session.clear();//清除session缓存
				session.update(res);
				result=true;
			}
		trans.commit();//提交事物
		session.close();//关闭session
		return result;
	}

	//组合查询
	@Override
	public List getRestateByComposition(Restate restate) {
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession se=request.getSession();//创建session对象
		StringBuffer hql=new StringBuffer();

		//外键查询
		hql.append("from Restate res left join fetch res.trouble tro where 1=1");
		if((null!=restate.getTroNo()&&!"".equals(restate.getTroNo()))){
			int id=troubleService.getTroubleByNo(restate.getTroNo()).get(0).getId();
			hql.append(" and tro.id ="+id);
		}
		if((null!=restate.getExecution()&&!"".equals(restate.getExecution()))){
			hql.append(" and res.execution="+restate.getExecution());
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

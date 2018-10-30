package com.tthg.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.IComponentDao;
import com.tthg.entity.Component;
import com.tthg.entity.Trouble;
import com.tthg.service.ITroubleService;
/**
 * 零件调用实现类
 * @author 葛康  编写者
 * @since 2016-12-29 编写时间
 *
 */
@Repository
public class ComponentDao implements IComponentDao {
	@Autowired
	private SessionFactory sessionFactory;//装载sessionfactory

	@Autowired
	private ITroubleService troubleService;//装载故障维修接口
	
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

	//删除零件调用信息
	@Override
	public boolean deleteComponent(int[] ids) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			for(int i=0;i<ids.length;i++){
			//通过id获取零件实体
			Component component=this.getComponentById(ids[i]).get(0);

			session.delete(component);//执行删除操作
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

	//全查零件调用信息
	@Override
	public List<Component> getAllComponent() {
		String hql="from Component comp";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}

	//通过id查询零件调用信息
	@Override
	public List<Component> getComponentById(Integer id) {
		String hql="from Component comp where comp.id=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		query.setInteger(0, id);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//通过编号查询零件调用信息
	@Override
	public List<Component> getComponentByNo(String no) {
		String hql="from Component comp where comp.compNo=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		query.setString(0, no);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//添加零件调用信息
	@Override
	public boolean insertComponent(Component component, Integer troid) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=session.beginTransaction();//开启事物	
		//根据部门no获取部门对象
		Trouble trouble=troubleService.getTroubleByNo(component.getTroNo()).get(0);
		if (this.getComponentByNo(component.getCompNo()).size()<1) {//判断插入的员工是否存在
			Set set=new HashSet();
			set.add(component);
			trouble.setComponents(set);	//创建两表关联
			component.setTrouble(trouble);
			session.save(component);
			result=true;
		}else{
			System.out.println("零件信息存在，不能添加");
		}
		trans.commit();//提交事物
		session.close();//关闭session
		return result;
	}

	//修改零件调用信息
	@Override
	public boolean updateComponent(Integer troid, Component component) {
		boolean result=false;
		Session session=sessionFactory.openSession();//成关键session对象
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			if (null!=this.getComponentById(component.getId())) {
				//根据部门no获取部门对象
				Trouble trouble=troubleService.getTroubleByNo(component.getTroNo()).get(0);
				component.setTrouble(trouble);
				Component comp=(Component) session.merge(component);//合并
				//session.clear();//清除session缓存
				session.update(comp);
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

	@Override
	public List getComponentByComposition(Component component) {
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		HttpSession se=request.getSession();//创建session对象
		StringBuffer hql=new StringBuffer();

		//外键查询
		hql.append("from Component comp left join fetch comp.trouble tro where 1=1");
		if((null!=component.getTroNo()&&!"".equals(component.getTroNo()))){
			int id=troubleService.getTroubleByNo(component.getTroNo()).get(0).getId();
			hql.append(" and tro.id ="+id);
		}
		if((null!=component.getUname()&&!"".equals(component.getUname()))){
			hql.append(" and comp.uname like '%"+component.getUname()+"%'");
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

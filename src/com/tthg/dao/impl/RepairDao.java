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

import com.tthg.dao.IRepairDao;
import com.tthg.entity.Repair;
import com.tthg.entity.Trouble;
import com.tthg.service.IPageService;
/**
 * 维修登记实现类
 * @author 葛康  编写者
 * @since 2016-12-17 编写时间
 *
 */
@Repository
public class RepairDao implements IRepairDao {
	@Autowired
	private SessionFactory sessionFactory;//配置sessionFactory

	@Autowired
	private IPageService pageService;//分页服务层接口
	
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

	//删除维修登记
	@Override
	public boolean deleteRepair(int[] ids) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session对象
		Transaction trans=null;
		try{
			trans= session.beginTransaction();//开启事物
			
			for(int i=0;i<ids.length;i++){
			//根据id获取对象实体
			Repair repair=this.getRepairById(ids[i]).get(0);
			if(null==repair.getTrouble()&&null==repair.getEvaluate()){
				System.out.println("维修登记表下没有关联，可以删除");
				session.delete(repair);
				result=true;
			}else{
				System.out.println("维修登记表下没有关联，不可以删除");
				result=false;
			}
			}
			} catch (RuntimeException re) {
		        re.printStackTrace();
		    }      
			trans.commit();//提交事务
			session.close();//关闭session
			return result;
	}

	//全查维修登记
	@Override
	public List<Repair> getAllRepair() {
		String hql="from Repair rep";
		Session session=sessionFactory.openSession();//创建session对象
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}

	//根据id获取维修登记信息
	@Override
	public List<Repair> getRepairById(Integer id) {
		String hql="from Repair rep where rep.id=?";
		Session session=sessionFactory.openSession();//创建session对象
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		
		List list=query.list();

		
		Repair re=(Repair)list.get(0);
		System.out.println("Repair1:"+re.getCname());
		Trouble tro=re.getTrouble();
		System.out.println("tro:"+tro+",troId="+tro.getPlateNo());
		
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}

	//根据no获取维修登记信息
	@Override
	public List<Repair> getRepairByNo(String no) {
		String hql="from Repair rep where rep.reNo=?";
		Session session=sessionFactory.openSession();//创建session对象
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setString(0, no);
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}

	//添加维修登记信息
	@Override
	public boolean insertRepair(Repair repair) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session对象
		Transaction transaction= session.beginTransaction();//开启事物
		
		if (this.getRepairByNo(repair.getReNo()).size()<1) {
			result=true;
			session.save(repair);
		}
		transaction.commit();//提交事务
		session.close();//关闭session
		return result;
	}

	//修改维修登记信息
	@Override
	public boolean updateRepair(Integer id, Repair repair) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session对象
		Transaction trans=null;
		try{
			trans= session.beginTransaction();//开启事物
			
			session.update(repair);
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
	public List getRepairByComposition(Repair repair) {
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession se=request.getSession();//获取sesssion对象
		StringBuffer hql=new StringBuffer();
		hql.append("from Repair rep where 1=1");
		if((null!=repair.getPlateNo()&&!"".equals(repair.getPlateNo()))){
			hql.append(" and rep.plateNo like '%"+repair.getPlateNo()+"%'");
		}
		if((null!=repair.getCname()&&!"".equals(repair.getCname()))){
			hql.append(" and rep.cname like '%"+repair.getCname()+"%'");
		}
		if((null!=repair.getUname()&&!"".equals(repair.getUname()))){
			hql.append(" and rep.uname like '%"+repair.getUname()+"%'");
		}
		if((null!=repair.getReDate()&&!"".equals(repair.getReDate())&&null!=repair.getEnDate()&&!"".equals(repair.getEnDate()))){
			hql.append(" and rep.reDate >= '"+repair.getReDate()+"' and rep.reDate <='"+repair.getEnDate()+"'");
		}
		String hqlStr=hql.toString();
		se.setAttribute("hqlStr", hqlStr);
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hqlStr);
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		return list;
	}

	//验证车辆信息
	@Override
	public boolean getRepairByPlateNo(String plateNo) {
		String hql="from VehicleCustomer vc where vc.carId=?";
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session对象
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setInteger(0, new Integer(plateNo));
		
		List list=query.list();
		System.out.println(list.size());
		if (list.size()>0) {
			result=true;
		}
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return result;
	}  
}

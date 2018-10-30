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

import com.tthg.dao.IPaymentDao;
import com.tthg.entity.Payment;
import com.tthg.entity.Repair;
import com.tthg.service.IRepairService;
/**
 * 支付实现类
 * @author 葛康  编写者
 * @since 2016-12-29 编写时间
 *
 */
@Repository
public class PaymentDao implements IPaymentDao {
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

	//删除支付
	@Override
	public boolean deletePayment(int[] ids) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			for(int i=0;i<ids.length;i++){
			//通过id查询支付实体
			Payment payment=this.getPaymentById(ids[i]).get(0);
//			payment.setRepair(null);
			session.delete(payment);
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

	//全查支付信息
	@Override
	public List<Payment> getAllPayment() {
		String hql="from Payment paym";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}

	//通过id查询支付实体
	@Override
	public List<Payment> getPaymentById(Integer id) {
		String hql="from Payment paym where paym.id=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}

	//通过no查询支付信息
	@Override
	public List<Payment> getPaymentByNo(String no) {
		String hql="from Payment paym where paym.manNo=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setString(0, no);
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}
	
	//添加支付信息
	@Override
	public boolean insertPayment(Payment payment, Integer reid) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=session.beginTransaction();//开启事物
		Repair repair=repairService.getRepairByNo(payment.getReNo()).get(0);//根据维修id获取部门对象
		if (this.getPaymentByNo(payment.getManNo()).size()<1) {//判断插入的评估是否存在
			repair.setPayment(payment);	
			payment.setRepair(repair);
			session.save(payment);
			result=true;
		}else{
			System.out.println("支付信息存在，不能添加");
		}
		trans.commit();//提交事务
		session.close();//关闭session
		return result;
	}

	//修改支付信息
	@Override
	public boolean updatePayment(Integer reid, Payment payment) {
		boolean result=false;
		Session session=sessionFactory.openSession();
		Transaction trans=null;
		try{
			trans=session.beginTransaction();
			if (this.getPaymentById(payment.getId()).size()>0) {
				Repair repair=repairService.getRepairByNo(payment.getReNo()).get(0);//根据维修id获取部门对象
				payment.setRepair(repair);
				Payment paym=(Payment) session.merge(payment);//合并
				//session.clear();//清除session缓存
				session.update(paym);
				result=true;
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

	//组合查询
	@Override
	public List getPaymentByComposition(Payment payment) {
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession se=request.getSession();//创建session对象
		StringBuffer hql=new StringBuffer();

		//外键查询
		hql.append("from Payment paym left join fetch paym.repair re where 1=1");
		if((null!=payment.getReNo()&&!"".equals(payment.getReNo()))){
			int id=repairService.getRepairByNo(payment.getReNo()).get(0).getId();
			hql.append(" and re.id ="+id);
		}
		if((null!=payment.getPdate()&!"".equals(payment.getPdate())&&null!=payment.getEdate()&&!"".equals(payment.getEdate()))){
			hql.append(" and paym.pdate >= '"+payment.getPdate()+"' and paym.pdate <='"+payment.getEdate()+"'");
		}
		if((null!=payment.getCname()&&!"".equals(payment.getCname()))){
			hql.append(" and paym.cname like '%"+payment.getCname()+"%'");
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

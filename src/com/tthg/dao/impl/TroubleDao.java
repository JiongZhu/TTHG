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

import com.tthg.dao.ITroubleDao;
import com.tthg.entity.Repair;
import com.tthg.entity.Trouble;
import com.tthg.service.IRepairService;
/**
 * 故障维修实现类
 * @author 葛康  编写者
 * @since 2016-12-19 编写时间
 *
 */
@Repository
public class TroubleDao implements ITroubleDao {
	@Autowired
	private SessionFactory sessionFactory;//装配sessionFactory

	@Autowired
	private IRepairService repairService;//维修登记服务层接口
	//删除故障维修
	@Override
	public boolean deleteTrouble(int[] ids) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			for(int i=0;i<ids.length;i++){
			//根据id获取实体
			Trouble trouble=(Trouble) this.getTroubleById(ids[i]).get(0);

			if(trouble.getComponents().size()==0){
				System.out.println("维修下没有关系，可以删除");
				session.delete(trouble);
				result=true;
			}else{
				System.out.println("维修下有关系，不可以删除");
				result=false;
			}
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

	//故障维修全查
	@Override
	public List<Trouble> getAllTrouble() {
		String hql="from Trouble tro";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		
		return list;
	}

	//根据id查询故障维修
	@Override
	public List<Trouble> getTroubleById(Integer id) {
		String hql="from Trouble tro where tro.id=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		return list;
	}

	//根据编号查询故障维修
	@Override
	public List<Trouble> getTroubleByNo(String no) {
		String hql="from Trouble tro where tro.troNo=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setString(0, no);
		
		List list=query.list();
		transaction.commit();//提交事物
		session.close();//关闭session
		return list;
	}

	//添加故障维修
	@Override
	public boolean insertTrouble(Trouble trouble, Integer reid) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=session.beginTransaction();//开启事物
		//根据部门no获取部门对象
		Repair repair=repairService.getRepairByNo(trouble.getReNo()).get(0);
		if (this.getTroubleByNo(trouble.getTroNo()).size()<1) {//判断插入的员工是否存在
			repair.setTrouble(trouble);	
			trouble.setRepair(repair);
			System.out.println("维修信息添加");
			session.save(trouble);
			result=true;
		}else{
			System.out.println("维修信息存在，不能添加");
		}
		trans.commit();//提交事物
		session.close();//关闭session
		return result;
	}

	//修改故障维修
	@Override
	public boolean updateTrouble(Integer reid, Trouble trouble) {
		boolean result=false;
		Session session=sessionFactory.openSession();
		Transaction trans=null;
		try{
			trans=session.beginTransaction();
			if (this.getTroubleById(trouble.getId()).size()>0) {
				Repair repair=repairService.getRepairByNo(trouble.getReNo()).get(0);//根据部门id获取部门对象
				trouble.setRepair(repair);
				Trouble tro=(Trouble) session.merge(trouble);//合并
				//session.clear();//清除session缓存
				session.update(tro);
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
	public List getTroubleByComposition(Trouble trouble) {
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		HttpSession se=request.getSession();//创建session对象
		StringBuffer hql=new StringBuffer();

		//外键查询
		hql.append("from Trouble tro left join fetch tro.repair re where 1=1");
		if((null!=trouble.getReNo()&&!"".equals(trouble.getReNo()))){
			int id=repairService.getRepairByNo(trouble.getReNo()).get(0).getId();
			hql.append(" and re.id ="+id);
		}
		if((null!=trouble.getPlateNo()&&!"".equals(trouble.getPlateNo()))){
			hql.append(" and tro.plateNo = "+trouble.getPlateNo());
		}
		if((null!=trouble.getUname()&&!"".equals(trouble.getUname()))){
			hql.append(" and tro.uname like '%"+trouble.getUname()+"%'");
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

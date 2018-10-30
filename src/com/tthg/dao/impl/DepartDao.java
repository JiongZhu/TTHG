package com.tthg.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.IDepartDao;
import com.tthg.entity.Department;
/**
 * 部门实现类
 * @author 葛康  编写者
 * @since 2016-12-12 编写时间
 *
 */
@Repository
public class DepartDao implements IDepartDao {
	@Autowired
	private SessionFactory sessionFactory;//装载sessionFactory

	@Override
	//删除部门信息，因为一对多关系，所以删除前应判断部门下
	//是否有员工，返回布尔
	public boolean deleteDepart(int[] ids) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session对象
		Transaction trans=null;
		try{
			trans= session.beginTransaction();//创建事物
			
			for(int i=0;i<ids.length;i++){
				Department department=this.getDepartById(ids[i]).get(0);//通过id获取部门对象
				if(department.getUsers().size()==0){
					System.out.println("部门下没有员工，可以删除");
					session.delete(department);
					result=true;
				}else{
					System.out.println("部门下有员工，不可以删除");
					result=false;
				}
			}
			} catch (RuntimeException re) {
		        re.printStackTrace();
		    }      
    	trans.commit();//事物提交
        session.close();
        return result;
	}

	@Override
	//经理查询所有部门信息
	public List<Department> getAllDepart() {
		String hql="from Department depart";
		Session session=sessionFactory.openSession();//创建session对象
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		
		List list=query.list();

		transaction.commit();//提交事务
		session.close();//关闭session
		return list;
	}

	@Override
	//通过部门id查询用户信息
	public List<Department> getDepartById(Integer id) {
		String hql="from Department depart where depart.id=?";
		Session session=sessionFactory.openSession();//创建session对象
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		query.setInteger(0, id);
		
		List list=query.list();//query转换成集合
		transaction.commit();//提交事务
		session.close();//关闭session
		return list;
	}

	@Override
	//添加部门信息页面传递部门对象，进行添加操作并返回布尔类型信息
	public boolean insertDepart(Department department) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session对象
		Transaction trans=session.beginTransaction();//开启事物

		if (this.getDepartByNo(department.getDeNo()).size()<1) {
			result=true;
			System.out.println("wsdsdsfs");
			session.save(department);//session保存
		}
		trans.commit();//提交事务
		session.close();//关闭session
		return result;
	}

	@Override
	//修改部门信息页面传递部门对象，进行修改操作并返回布尔类型信息
	public boolean updateDepart(Integer id, Department department) {
		boolean result=false;
		Session session = sessionFactory.openSession();//创建session对象
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			session.update(department);//session修改
			
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
	//通过部门编号查询用户信息，一次来判断改部门是否存在
	//添加部门和删除部门是会用到
	public List<Department> getDepartByNo(String no) {
		String hql="from Department depart where depart.deNo=?";
		Session session=sessionFactory.openSession();//创建session对象
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		query.setString(0, no);

		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		return list;
	}
}

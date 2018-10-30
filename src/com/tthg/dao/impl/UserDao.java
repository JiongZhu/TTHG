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

import com.tthg.dao.IUserDao;
import com.tthg.entity.Department;
import com.tthg.entity.User;
import com.tthg.service.IDepartService;
import com.tthg.service.IPageService;
/**
 * 用户实现类
 * @author 葛康  编写者
 * @since 2016-12-14 编写时间
 *
 */
@Repository
public class UserDao implements IUserDao {
	@Autowired
	private SessionFactory sessionFactory;//自动装配sessionfactory
	@Autowired
	private IDepartService departService;//部门服务层接口
	@Autowired
	private IPageService pageService;//分页接口
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public IDepartService getDepartService() {
		return departService;
	}

	public void setDepartService(IDepartService departService) {
		this.departService = departService;
	}

	public IPageService getPageService() {
		return pageService;
	}

	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}

	@Override
	//通过用户id删除相对应得用户信息，并返回布尔类型信息
	public boolean deleteUser(int[] ids) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=null;
		try{
			trans=session.beginTransaction();//开启事物
			for(int i=0;i<ids.length;i++){
				User user=(User) this.getUserById(ids[i]).get(0);
				session.delete(user);
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

	@Override
	//经理通过权限查询出自己之外的所有用户
	public List<User> getUserByPower(String url) {
		String hql=url;//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}

	@Override
	//通过用户id查询用户信息
	public List<User> getUserById(Integer id) {
		String hql="from User us where us.id=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);//执行hql语句
		query.setInteger(0, id);
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}

	@Override
	//添加用户，因为一对多关系，所以传递参数不仅包含用户对象
	//还应包含需添加的部门id，以此来确定相应的部门与员工的关系
	public boolean insertUser(User user,Integer dmid) {
		boolean result=false;
		Session session=sessionFactory.openSession();//创建session
		Transaction trans=session.beginTransaction();//开启事物
		//根据部门id获取部门对象
		Department department=departService.getDepartById(dmid).get(0);
		System.out.println(department.getDeName()+user.getUno());
		System.out.println(this.getUserByNo(user.getUno()).size());
		if (this.getUserByNo(user.getUno()).size()<1) {//判断插入的员工是否存在
			Set set=new HashSet();
			set.add(user);//创建关联
			department.setUsers(set);	
			user.setDeparts(department);
			session.save(user);
			result=true;
		}else{
			System.out.println("员工信息存在，不能添加");
		}
		trans.commit();//提交事务
		session.close();//关闭session
		return result;
	}

	@Override
	//修改用户信息，因为一对多关系，所以传递参数应包含
	//需要修改的部门id，以此来修改部门与员工的关系
	public boolean updateUser(Integer dmid, User user) {
		boolean result=false;
		Session session=sessionFactory.openSession();
		Transaction trans=null;
		try{
			trans=session.beginTransaction();
			if (this.getUserById(user.getId()).size()>0) {
				Department department=departService.getDepartById(dmid).get(0);
				user.setDeparts(department);
				User us=(User) session.merge(user);//合并
				//session.clear();//清除session缓存
				session.update(us);
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
		session.close();//guanbisession
		return result;
	}

	@Override
	//通过用户编号查询用户信息，一次来判断改用户是否存在
	//添加用户和删除用户是会用到
	public List<User> getUserByNo(String no) {
		String hql="from User us where us.uno=?";//hql语句
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hql);
		query.setString(0, no);
		
		List list=query.list();
		transaction.commit();//提交事务
		session.close();//关闭session
		
		return list;
	}
	//组合查询
	@Override
	public List getUserByComposition(User user) {
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		HttpSession se=request.getSession();//创建session对象
		StringBuffer hql=new StringBuffer();
		System.out.println("getUserByComposition"+user.getUname());
		//外键查询
		hql.append("from User us left join fetch us.departs dm where 1=1");//外键查询语句
		if((null!=user.getUname()&&!"".equals(user.getUname()))){
			hql.append(" and us.uname like '%"+user.getUname()+"%'");
			System.out.println("getUserByComposition1"+user.getUname());
		}
		if((null!=user.getUstate()&&!"".equals(user.getUstate()))){
			hql.append(" and us.ustate like '%"+user.getUstate()+"%'");
		}
		if(null!=user.getExtreId()&&user.getExtreId()!=0){
			hql.append(" and dm.id='"+user.getExtreId()+"'");
		}
		
		String hqlStr=hql.toString();
		se.setAttribute("hqlStr", hqlStr);
		Session session=sessionFactory.openSession();//创建session
		Transaction transaction= session.beginTransaction();//开启事物
		Query query=session.createQuery(hqlStr);
		List list=query.list();
		System.out.println("getUserByComposition"+list.size());
		transaction.commit();//提交事务
		session.close();//关闭session
		return list;
	}

	@Override
	public User getUserByNameAndPwd(User user) {
		Session session=sessionFactory.openSession();
		String hql="from User u where u.uname=? and u.passward=?";
		Query query=session.createQuery(hql);
		query.setString(0, user.getUname());
		query.setString(1, user.getPassward());
		List<User> list=query.list();
		User u=null;
		if(list.size()==1){
			u=list.get(0);
		}
		session.close();
		return u;
	}

}

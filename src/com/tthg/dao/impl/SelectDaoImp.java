package com.tthg.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tthg.dao.SelectDao;

import com.tthg.entity.Vehicle;
import com.tthg.entity.VehicleBrand;
/**
 * 车辆管理展示实现类
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
@Repository
public class SelectDaoImp implements SelectDao{
    @Autowired
    private SessionFactory sessionFactory;
	@Override
	public List<Vehicle> selectByCarname(String selecttext) {
		// TODO Auto-generated method stub
		System.out.println("进入dao层selectByname");
		Vehicle v=new Vehicle();
		Session session=sessionFactory.openSession();
		String hql="from Vehicle v where v.VehicleName like ? or v.Brand like ?";
		Query query=session.createQuery(hql);
		query.setString(0, "%"+selecttext+"%");
		query.setString(1, "%"+selecttext+"%");
		List list=query.list();
		
		for(int i=0;i<list.size();i++){
			
			v=(Vehicle)list.get(i);
			System.out.println("carname="+v.getVehicleName());
		}
		
		return list;
	}
	@Override
	public List<Vehicle> selectByCarPrice(double minprice, double maxprice) {
		// TODO Auto-generated method stub
		System.out.println("进入dao中selectBycarPrice方法");
		Vehicle v=new Vehicle();
		Session session=sessionFactory.openSession();
		String hql="from Vehicle v where v.GuidePrice(RMB) between ? and ?";
		Query query=session.createQuery(hql);
		query.setDouble(0,minprice);
		query.setDouble(1, maxprice);
		List list=query.list();
		for(int i=0;i<list.size();i++){
			v=(Vehicle)list.get(i);		
			System.out.println("carname="+v.getVehicleName());
		}
		return list;
	}
	@Override
	public List<Vehicle> selectByCarBrand(String carBrand) {
		// TODO Auto-generated method stub
		System.out.println("进入dao层中selectbycarbrand方法");
		List<Vehicle> list;
		Vehicle v=new Vehicle();
		Session session=sessionFactory.openSession();
		String hql="from Vehicle v where v.Brand=?";
		System.out.println("carBrand+"+carBrand);
		Query query=session.createQuery(hql);
		query.setString(0,carBrand);
		if(query.list().size()<=0){
			list=null;			
		}
		else{
			list=query.list();
			for(int i=0;i<list.size();i++){
				v=(Vehicle)list.get(i);		
				System.out.println("carname="+v.getVehicleName());
			}
		}
		return list;
      
	}
	@Override
	public Vehicle showVehicle(String Carname) {
		System.out.println("进入dao层中showvehicle方法");
		Vehicle v=new Vehicle();
		Session session=sessionFactory.openSession();
		String hql="from Vehicle v where v.VehicleName like ?";
		System.out.println("hql语句执行");
		Query query=session.createQuery(hql);
		query.setString(0,Carname);
		List list=query.list();
		if(list.size()>0){
		
			v=(Vehicle)list.get(0);
			System.out.println("carname="+v.getVehicleName());
			System.out.println("photo:"+v.getPhoto());
			
		
		}
		return v;
	}
	@Override
	public List<VehicleBrand> showBrand() {
		// TODO Auto-generated method stub
		System.out.println("进入dao层中showBrand方法");
		VehicleBrand vb=new VehicleBrand();
		Session session=sessionFactory.openSession();
		String hql="from VehicleBrand";
		System.out.println("*******");
		Query query=session.createQuery(hql);
		List list=query.list();
		for(int i=0;i<list.size();i++){
			
			vb=(VehicleBrand)list.get(i);		
			System.out.println("list.size="+list.size()+"BrandName="+vb.getBrandName());
			
		}
		
		return list;
	}
	@Override
	public int sumPage(int pageNum) {
		// TODO Auto-generated method stub
		System.out.println("进入dao层");
		Session session=sessionFactory.openSession();
		//Session session=HibernateSessionFactory.getSession();
		String hql="from Vehicle v";
		Query query=session.createQuery(hql);
		int sumRecord=query.list().size();
		session.close();
		if(sumRecord%pageNum==0){
			return sumRecord/pageNum;
		}else{
			return sumRecord/pageNum+1;
		}	
	}

	@Override
	public List<Vehicle> getCarByPageNo(int pageNum, int pageNo,String carBrand) {
		Session session=sessionFactory.openSession();
		System.out.println("进入dao层");
		//Session session=HibernateSessionFactory.getSession();
		String hql="from Vehicle v where v.Brand=?";
		Query query=session.createQuery(hql);
		query.setString(0, carBrand);
		query.setFirstResult((pageNo-1)*pageNum);
		query.setMaxResults(pageNum);
		List<Vehicle> vList=query.list();	
		System.out.println("lisi.size:"+vList.size());
		session.close();
		return vList;
	}
	@Override
	public int flsumPage(int pageNum, String carBrand) {
		// TODO Auto-generated method stub
		System.out.println("进入dao层flsunpage");
		Session session=sessionFactory.openSession();
		//Session session=HibernateSessionFactory.getSession();
		String hql="from Vehicle v where v.Brand=?";
		Query query=session.createQuery(hql);
		query.setString(0, carBrand);
		int sumRecord=query.list().size();
		session.close();
		if(sumRecord%pageNum==0){
			return sumRecord/pageNum;
		}else{
			return sumRecord/pageNum+1;
		}	
		
	}

}

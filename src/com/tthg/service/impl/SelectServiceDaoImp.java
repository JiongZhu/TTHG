package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.SelectDao;
import com.tthg.entity.Vehicle;
import com.tthg.entity.VehicleBrand;
import com.tthg.service.SelectServiceDao;
/**
 * 车辆管理服务层实现类
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
@Service
public class SelectServiceDaoImp implements SelectServiceDao{
   @Autowired
   private SelectDao selectdao;
   
	public SelectDao getSelectdao() {
	return selectdao;
}

public void setSelectdao(SelectDao selectdao) {
	this.selectdao = selectdao;
}

	@Override
	public List<Vehicle> selectByCarname(String selecttext) {
		// TODO Auto-generated method stub
		System.out.println("进入service层selectbycarname");
		return selectdao.selectByCarname(selecttext);
	}

	@Override
	public List<Vehicle> selectByCarPrice(double minprice, double maxprice) {
		// TODO Auto-generated method stub
		System.out.println("进入service层中selectbyprice");
		return selectdao.selectByCarPrice(minprice, maxprice);
	}

	@Override
	public List<Vehicle> selectByCarBrand(String carBrand) {
		// TODO Auto-generated method stub
		System.out.println("进入service层中selectbycarbrand");
		return selectdao.selectByCarBrand(carBrand);
	}

	@Override
	public Vehicle showVehicle(String Carname) {
		// TODO Auto-generated method stub
		System.out.println("进入service层中showcar");
		return selectdao.showVehicle(Carname);
	}

	@Override
	public List<VehicleBrand> showBrand() {
		// TODO Auto-generated method stub
		System.out.println("进入service层中showBrand方法");
		return selectdao.showBrand();
	}
	public List<Vehicle> getCarByPageNo(int pageNum, int pageNo,String carBrand) {
		// TODO Auto-generated method stub
		System.out.println("进入service层分页查询");
		return selectdao.getCarByPageNo(pageNum, pageNo,carBrand);
	}

	@Override
	public int sumPage(int pageNum) {
		// TODO Auto-generated method stub
		System.out.println("进入service层");
		return selectdao.sumPage(pageNum);
	}

	@Override
	public int flsumPage(int pageNum, String carBrand) {
		// TODO Auto-generated method stub
		System.out.println("分类sumpage");
		return selectdao.flsumPage(pageNum, carBrand);
	}
}

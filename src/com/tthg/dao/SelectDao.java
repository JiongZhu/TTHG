package com.tthg.dao;

import java.util.List;

import com.tthg.entity.User;
import com.tthg.entity.Vehicle;
import com.tthg.entity.VehicleBrand;

/**
 *车辆搜索,分类查询到层
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
public interface SelectDao {
	public List<Vehicle> selectByCarname(String selecttext);//根据汽车名或者首字母查询
//	public List<User> selectByCarFletter(String fletter);
	public  List<Vehicle> selectByCarPrice(double minprice,double maxprice);
	public List<Vehicle> selectByCarBrand(String carBrand);
	public Vehicle showVehicle(String Carname);
	public List<VehicleBrand> showBrand();
	public int sumPage(int pageNum);
	public int flsumPage(int pageNum,String carBrand);
	public List<Vehicle> getCarByPageNo(int pageNum, int pageNo,String carBrand);
}

package com.tthg.service;

import java.util.List;

import com.tthg.entity.Vehicle;
import com.tthg.entity.VehicleBrand;
/**
 * 前台车辆管理服务层dao
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
public interface SelectServiceDao {
	public List<Vehicle> selectByCarname(String selecttext);
	public  List<Vehicle> selectByCarPrice(double minprice,double maxprice);
	public List<Vehicle> selectByCarBrand(String carBrand);
	public Vehicle showVehicle(String Carname);
	public List<VehicleBrand> showBrand();
	public int sumPage(int pageNum);
	public int flsumPage(int pageNum,String carBrand);
	public List<Vehicle> getCarByPageNo(int pageNum, int pageNo,String carBrand);

}

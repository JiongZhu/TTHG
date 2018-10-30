package com.tthg.dao;

import java.util.List;

import com.tthg.entity.VehicleCustomer;
/**
 * 购车客户接口
 * @author 朱宣羽  编写者
 * @since 2016-12-12 编写时间
 *
 */
public interface IVehicleCustomerDAO {
	void add(VehicleCustomer customer);//新增购车客户
	void delete(int[] ids);//删除购车客户
	void update(VehicleCustomer customer);//修改购车客户
	List<VehicleCustomer> searchAll();//查询所有购车客户
	List<VehicleCustomer> searchCustomer(VehicleCustomer customer);//根据条件(客户姓名、身份证号、销售顾问)组合查询所有购车客户
}

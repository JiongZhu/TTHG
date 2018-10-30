package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Order;
/**
 * 订单处理接口
 * @author 朱宣羽  编写者
 * @since 2016-12-12 编写时间
 *
 */
public interface IOrderDAO {
	void add(Order order);//新增订单信息
	void delete(int[] ids);//删除订单信息
	void update(Order order);//修改订单信息
	List<Order> searchAll();//全查订单信息
	List<Order> searchOrder(Order order);//根据条件(客户姓名、签订时间、审核状态)组合查询订单信息
}

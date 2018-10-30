package com.tthg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.OrderDao;
import com.tthg.entity.Appointment;
import com.tthg.entity.Order;
import com.tthg.service.OrderServiceDao;
/**
 * 前台预约服务层实现类
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
@Service
public class OrderServiceDaoImp implements OrderServiceDao{
	@Autowired
	private OrderDao orderdao;

	public OrderDao getOrderdao() {
		return orderdao;
	}

	public void setOrderdao(OrderDao orderdao) {
		this.orderdao = orderdao;
	}

	@Override
	public void addOrder(Appointment app) {
		// TODO Auto-generated method stub
		System.out.println("进入order中service层");
	    orderdao.addOrder(app);
	}
	

}

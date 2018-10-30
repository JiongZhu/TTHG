package com.tthg.service;

import com.tthg.entity.Appointment;
import com.tthg.entity.Order;
/**
 * 前台预约服务层dao
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
public interface OrderServiceDao {
	 void addOrder(Appointment app);
}

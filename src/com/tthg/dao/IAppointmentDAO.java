package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Appointment;
/**
 * 试乘试驾预约接口
 * @author 朱宣羽  编写者
 * @since 2016-12-12 编写时间
 *
 */
public interface IAppointmentDAO {
	void add(Appointment appointment);//新增试乘试驾预约
	void delete(int[] ids);//删除试乘试驾预约
	void update(Appointment appointment);//修改试乘试驾预约
	List<Appointment> searchAll();//全查试乘试驾预约
	List<Appointment> searchAppointment(Appointment appointment);//根据条件(客户姓名、预约车型、预约试驾、审核状态)组合查询试乘试驾预约
}

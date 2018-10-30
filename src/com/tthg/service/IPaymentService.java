package com.tthg.service;

import java.util.List;

import com.tthg.entity.Payment;
//客户支付服务层接口
public interface IPaymentService {
	//查询所有客户支付信息
	public List<Payment> getAllPayment();
	//删除客户支付信息，因为一对多关系，所以客户支付前应判断部门下
	//是否有其他表，返回布尔
	public boolean deletePayment(int[] ids);
	//通过客户支付编号查询用户信息，一次来判断客户支付是否存在
	//添加客户支付和删除评估是会用到
	public List<Payment> getPaymentByNo(String no);
	//通过客户支付id查询用户信息
	public List<Payment> getPaymentById(Integer id);
	//修改客户支付信息页面传递评估对象，进行修改操作并返回布尔类型信息
	public boolean updatePayment(Integer reid,Payment payment);
	//添加客户支付信息页面传递评估对象，进行添加操作并返回布尔类型信息
	public boolean insertPayment(Payment payment,Integer reid);
	//复合查询
	public List getPaymentByComposition(Payment payment);
}


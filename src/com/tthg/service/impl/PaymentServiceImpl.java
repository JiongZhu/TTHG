package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.service.impl.PaymentServiceImpl;
import com.tthg.dao.IPaymentDao;
import com.tthg.entity.Payment;
import com.tthg.service.IPaymentService;
@Service
//客户支付服务层接口实现类
public class PaymentServiceImpl implements IPaymentService {
	@Autowired
	private IPaymentDao paymentDAO;//部门实现接口
	//get和set方法
	//删除客户支付信息，因为一对多关系，所以客户支付前应判断部门下
	//是否有其他表，返回布尔
	@Override
	public boolean deletePayment(int[] ids) {
		// TODO Auto-generated method stub
		return paymentDAO.deletePayment(ids);
	}
	//查询所有客户支付信息
	@Override
	public List<Payment> getAllPayment() {
		// TODO Auto-generated method stub
		return paymentDAO.getAllPayment();
	}
	//通过客户支付id查询用户信息
	@Override
	public List<Payment> getPaymentById(Integer id) {
		// TODO Auto-generated method stub
		return paymentDAO.getPaymentById(id);
	}
	//通过客户支付编号查询用户信息，一次来判断客户支付是否存在
	//添加客户支付和删除评估是会用到
	@Override
	public List<Payment> getPaymentByNo(String no) {
		// TODO Auto-generated method stub
		return paymentDAO.getPaymentByNo(no);
	}
	//添加客户支付信息页面传递评估对象，进行添加操作并返回布尔类型信息
	@Override
	public boolean insertPayment(Payment payment, Integer reid) {
		// TODO Auto-generated method stub
		return paymentDAO.insertPayment(payment, reid);
	}
	//修改客户支付信息页面传递评估对象，进行修改操作并返回布尔类型信息
	@Override
	public boolean updatePayment(Integer reid, Payment payment) {
		// TODO Auto-generated method stub
		return paymentDAO.updatePayment(reid, payment);
	}
	//复合查询
	@Override
	public List getPaymentByComposition(Payment payment) {
		// TODO Auto-generated method stub
		return paymentDAO.getPaymentByComposition(payment);
	}
	
}

package com.tthg.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.tthg.entity.Appointment;
import com.tthg.service.IVehicleServiceDAO;
import com.tthg.service.OrderServiceDao;
/**
 * 前台预约action
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
@Controller
public class OrderAction extends ActionSupport{
	
	private String customerName;
	private String scheduledPersonNum;
	private String telephone;
	private String scheduledTime;
	private String cname;
	private String description;
	
@Autowired
private OrderServiceDao orderServiceDao;
@Autowired
private IVehicleServiceDAO ivd;



public String getCustomerName() {
	return customerName;
}

public void setCustomerName(String customerName) {
	this.customerName = customerName;
}

public String getTelephone() {
	return telephone;
}

public void setTelephone(String telephone) {
	this.telephone = telephone;
}



public String getScheduledPersonNum() {
	return scheduledPersonNum;
}

public void setScheduledPersonNum(String scheduledPersonNum) {
	this.scheduledPersonNum = scheduledPersonNum;
}

public String getScheduledTime() {
	return scheduledTime;
}

public void setScheduledTime(String scheduledTime) {
	this.scheduledTime = scheduledTime;
}

public String getCname() {
	return cname;
}

public void setCname(String cname) {
	this.cname = cname;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public OrderServiceDao getOrderServiceDao() {
	return orderServiceDao;
}

public void setOrderServiceDao(OrderServiceDao orderServiceDao) {
	this.orderServiceDao = orderServiceDao;
}

public String execute(){
	
   
	
	return "input";
}
public String order(){
	
	System.out.println("进入action跳转到order界面");
	System.out.println("customerName"+customerName);
	System.out.println("cname+"+cname);
	System.out.println("id="+ivd.getIdByVehicleName(cname));
	 Date date = null;
	try  
	{  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	   date=sdf.parse(scheduledTime);   
	} catch (java.text.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	System.out.println("date"+date+"==="+scheduledTime);
	Appointment appointment=new Appointment();
	appointment.setCustomerName(customerName);
	appointment.setScheduledPersonNum(new Integer(scheduledPersonNum));
	appointment.setTelephone(telephone);
	appointment.setScheduledTime(date);
	appointment.setRemarks(description);
	appointment.setCarStyleId(ivd.getIdByVehicleName(cname));
	appointment.setIntentionId(8);
	appointment.setState("未审核");
	orderServiceDao.addOrder(appointment);
	return SUCCESS;
}
public String tiaozhuan(){
	
	System.out.println("页面跳转");
	return "tsuccess";
}

public void setIvd(IVehicleServiceDAO ivd) {
	this.ivd = ivd;
}

public IVehicleServiceDAO getIvd() {
	return ivd;
}
}

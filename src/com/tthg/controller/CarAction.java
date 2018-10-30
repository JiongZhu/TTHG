package com.tthg.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.tthg.entity.Car;
import com.tthg.service.ICarServiceDAO;
@Controller
public class CarAction extends ActionSupport{
private Car car;//定义一个对象，用来接收下面方法中的返回对象
private JSONObject jsonObj;//定义一个JSONObject类，用来通过json获取对象
@Autowired
private ICarServiceDAO carserviceDAO;//定义服务层接口
public Car getCar() {
	return car;
}
public void setCar(Car car) {
	this.car = car;
}
public JSONObject getJsonObj() {
	return jsonObj;
}
public void setJsonObj(JSONObject jsonObj) {
	this.jsonObj = jsonObj;
}
public ICarServiceDAO getCarserviceDAO() {
	return carserviceDAO;
}
public void setCarserviceDAO(ICarServiceDAO carserviceDAO) {
	this.carserviceDAO = carserviceDAO;
}
@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	return SUCCESS;
}
//增肌车辆信息
public String addCar(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	car=(Car)JSONObject.toBean(jsobj,Car.class);//把获取到的对象赋给定义的实体对象
	carserviceDAO.add(car);//执行增加方法
	return SUCCESS;//成功返回
}
//增肌车辆信息
public String updateCar(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	car=(Car)JSONObject.toBean(jsobj,Car.class);//把获取到的对象赋给定义的实体对象
	carserviceDAO.update(car);//执行更新方法
	return SUCCESS;//成功返回
}
//增肌车辆信息
public String deleteCar(){
	HttpServletRequest request=ServletActionContext.getRequest(); //获取上下文的属性
	String ids=request.getParameter("ids");//得到数组
	String[] idStr = ids.split(",");//因为数组中放的是对象，用逗号分离
	int[] id=new int[idStr.length];//定义一个数组，长度和从页面传来的数组长度一样
	for(int i=0;i<id.length;i++){
		id[i]=Integer.parseInt(idStr[i]);//循环遍历数组
	}
	carserviceDAO.delete(id);//执行删除操作
	return SUCCESS;//成功返回
}
//增肌车辆信息
public String carList(){
	Map map=new HashMap();//定义一个map
	List list=carserviceDAO.searchAll();//调用全查方法
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/car")+"\\"+"title.json");//获取列标题
	map.put("total",list.size());
	map.put("rows", list);
	map.put("title",title);
	jsonObj=JSONObject.fromObject(map);//用json执行
	System.out.println(jsonObj);//输出传到前台的数据
	return SUCCESS;//成功返回
}
//增肌车辆信息
public String searchCar(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	String carNo=request.getParameter("carNo");//获取到jsp页面传来的一个数据
	Car ca=new Car();//定义一个新的对象封装信息
	ca.setCarNo(carNo);
	Map map=new HashMap();
	List list=carserviceDAO.searchCar(ca);//执行查询操作
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/car")+"\\"+"title.json");//获取列标题
	map.put("total",list.size());
	map.put("rows", list);
	map.put("title",title);
	jsonObj=JSONObject.fromObject(map);//用json执行
	System.out.println(jsonObj);//输出传到前台的数据
	return SUCCESS;//成功返回
}
//从特定位置读文件
public static JSONArray readJson(String path) {
	// 从给定位置获取文件
	File file = new File(path);
	BufferedReader reader = null;
	// 返回值,使用StringBuffer
	StringBuffer data = new StringBuffer();
	//
	try {
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		// 每次读取文件的缓存
		String temp = null;
		while ((temp = reader.readLine()) != null) {
			data.append(temp);
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		// 关闭文件流
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	return JSONArray.fromObject(data.toString());
}
}

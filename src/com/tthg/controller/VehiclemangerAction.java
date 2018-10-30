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
import com.tthg.entity.Vehiclemanger;
import com.tthg.service.IVehiclemangerServiceDAO;
@Controller
public class VehiclemangerAction extends ActionSupport{
private Vehiclemanger vehiclemanger;//定义一个对象，用来接收下面方法中的返回对象
private JSONObject jsonObj;//定义一个JSONObject类，用来通过json获取对象
@Autowired
private IVehiclemangerServiceDAO vehiclemangerserviceDAO;//定义服务层借接口
public Vehiclemanger getVehiclemanger() {
	return vehiclemanger;
}
public void setVehiclemanger(Vehiclemanger vehiclemanger) {
	this.vehiclemanger = vehiclemanger;
}
public JSONObject getJsonObj() {
	return jsonObj;
}
public void setJsonObj(JSONObject jsonObj) {
	this.jsonObj = jsonObj;
}
public IVehiclemangerServiceDAO getVehiclemangerserviceDAO() {
	return vehiclemangerserviceDAO;
}
public void setVehiclemangerserviceDAO(
		IVehiclemangerServiceDAO vehiclemangerserviceDAO) {
	this.vehiclemangerserviceDAO = vehiclemangerserviceDAO;
}
//新增车型信息
public String addVehiclemanger(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	vehiclemanger=(Vehiclemanger)JSONObject.toBean(jsobj,Vehiclemanger.class);//把获取到的对象赋给定义的实体对象
	vehiclemangerserviceDAO.add(vehiclemanger);//执行增加方法
	return SUCCESS;//成功返回
}
//更新车型信息
public String updateVehiclemanger(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	vehiclemanger=(Vehiclemanger)JSONObject.toBean(jsobj,Vehiclemanger.class);//把获取到的对象赋给定义的实体对象
	vehiclemangerserviceDAO.update(vehiclemanger);//执行更新方法
	return SUCCESS;//成功返回
}
//删除车型信息
public String deleteVehiclemanger(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	String ids=request.getParameter("ids");//得到数组
	String[] idStr = ids.split(",");//因为数组中放的是对象，用逗号分离
	int[] id=new int[idStr.length];//定义一个数组，长度和从页面传来的数组长度一样
	for(int i=0;i<id.length;i++){
		id[i]=Integer.parseInt(idStr[i]);//循环遍历数组
	}
	vehiclemangerserviceDAO.delete(id);//执行删除操作
	return SUCCESS;//成功返回
}
//全查车型信息
public String vehiclemangerList(){
	Map map=new HashMap();//定义一个map
	List list=vehiclemangerserviceDAO.searchAll();//调用全查方法
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/Vehiclemanger")+"\\"+"title.json");//获取列标题
	map.put("total",list.size());
	map.put("rows", list);
	map.put("title",title);
	jsonObj=JSONObject.fromObject(map);//用json执行
	System.out.println(jsonObj);
	return SUCCESS;//成功返回
}
//按条件查询
public String searchVehiclemanger(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	String vehiclemangerNo=request.getParameter("vehiclemangerNo");//获取到jsp页面传来的三个数据
	String vehiclemangerName=request.getParameter("vehiclemangerName");
	Vehiclemanger vehiclemangers=new Vehiclemanger();//定义一个新的对象封装信息
	vehiclemangers.setVehicleNo(vehiclemangerNo);
	vehiclemangers.setVehicleName(vehiclemangerName);
	Map map=new HashMap();
	List list=vehiclemangerserviceDAO.seachVehiclemanger(vehiclemangers);//执行查询操作
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/Vehiclemanger")+"\\"+"title.json");//获取列标题
	map.put("total",list.size());
	map.put("rows", list);
	map.put("title",title);
	jsonObj=JSONObject.fromObject(map);//用json执行
	System.out.println(jsonObj);
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

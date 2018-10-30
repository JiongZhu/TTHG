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
import com.tthg.entity.VehicleBrand;
import com.tthg.service.IVehicleBrandServiceDAO;
@Controller
public class VehicleBrandAction extends ActionSupport{

private VehicleBrand vehicleBrand;//定义一个对象，用来接收下面方法中的返回对象
private JSONObject jsonObj;//定义一个JSONObject类，用来通过json获取对象
@Autowired
private IVehicleBrandServiceDAO  vehicleserviceDAO;//定义服务层接口
public VehicleBrand getVehicleBrand() {
	return vehicleBrand;
}
public void setVehicleBrand(VehicleBrand vehicleBrand) {
	this.vehicleBrand = vehicleBrand;
}
public JSONObject getJsonObj() {
	return jsonObj;
}
public void setJsonObj(JSONObject jsonObj) {
	this.jsonObj = jsonObj;
}
public IVehicleBrandServiceDAO getVehicleserviceDAO() {
	return vehicleserviceDAO;
}
public void setVehicleserviceDAO(IVehicleBrandServiceDAO vehicleserviceDAO) {
	this.vehicleserviceDAO = vehicleserviceDAO;
}
@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	return SUCCESS;
}
//新增车型品牌信息
public String addVehicleBrand(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	vehicleBrand=(VehicleBrand)JSONObject.toBean(jsobj,VehicleBrand.class);//把获取到的对象赋给定义的实体对象
	vehicleserviceDAO.add(vehicleBrand);//执行增加方法
	return SUCCESS;//成功返回
}
//更新车型品牌信息
public String updateVehicleBrand(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	vehicleBrand=(VehicleBrand)JSONObject.toBean(jsobj,VehicleBrand.class);//把获取到的对象赋给定义的实体对象
	vehicleserviceDAO.update(vehicleBrand);//执行更新方法
	return SUCCESS;//成功返回
}
//删除车型品牌信息
public String deleteVehicleBrand(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	String ids=request.getParameter("ids");//得到数组
	String[] idStr = ids.split(",");//因为数组中放的是对象，用逗号分离
	int[] id=new int[idStr.length];//定义一个数组，长度和从页面传来的数组长度一样
	for(int i=0;i<id.length;i++){
		id[i]=Integer.parseInt(idStr[i]);//循环遍历数组
	}
	vehicleserviceDAO.delete(id);//执行删除操作
	return SUCCESS;//成功返回
}
//全查车型品牌信息
public String vehicleBrandList(){
	Map map=new HashMap();//定义一个map
	List list=vehicleserviceDAO.searchAll();//调用全查方法
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/VehicleBrand")+"\\"+"title.json");//获取列标题
	map.put("total",list.size());
	map.put("rows", list);
	map.put("title",title);
	jsonObj=JSONObject.fromObject(map);//用json执行
	System.out.println(jsonObj);//输出传到前台的数据
	return SUCCESS;
}
//按条件查询车型品牌信息
public String searchVehicleBrand(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	String vehiclebrandNo=request.getParameter("vehicleBrandNo");//获取到jsp页面传来的两个数据
	String vehiclebrandName=request.getParameter("vehicleBranName");
	VehicleBrand vehiclebrand=new VehicleBrand();//定义一个新的对象封装信息
	vehiclebrand.setBrandNo(vehiclebrandNo);
	vehiclebrand.setBrandName(vehiclebrandName);
	Map map=new HashMap();
	List list=vehicleserviceDAO.searchVehicleBrand(vehiclebrand);//执行查询操作
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/VehicleBrand")+"\\"+"title.json");//获取列标题
	map.put("total",list.size());
	map.put("rows", list);
	map.put("title",title);
	jsonObj=JSONObject.fromObject(map);	//用json执行
	return SUCCESS;
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

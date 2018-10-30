package com.tthg.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;


import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.tthg.entity.Accessories;
import com.tthg.service.IAccessoriesServiceDAO;
import com.tthg.util.DateJsonValueProcessor;
@Controller
public class AccessoriesAction extends ActionSupport{
	private Accessories accessories;//定义一个对象，用来接收下面方法中的返回对象
	private JSONObject jsonObj;//定义一个JSONObject类，用来通过json获取对象
   @Autowired
   private IAccessoriesServiceDAO iasd;//定义服务层接口
public Accessories getAccessories() {
	return accessories;
}
public void setAccessories(Accessories accessories) {
	this.accessories = accessories;
}
public JSONObject getJsonObj() {
	return jsonObj;
}
public void setJsonObj(JSONObject jsonObj) {
	this.jsonObj = jsonObj;
}
public IAccessoriesServiceDAO getIasd() {
	return iasd;
}
public void setIasd(IAccessoriesServiceDAO iasd) {
	this.iasd = iasd;
}
@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	return SUCCESS;
}
//增加配件信息
public String addAccessories(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));//用来处理日期的显示格式
	accessories=(Accessories)JSONObject.toBean(jsobj,Accessories.class);//把获取到的对象赋给定义的实体对象
	iasd.add(accessories);//执行增加方法
	return SUCCESS;//成功返回
}
//更新配件信息
public String updateAccessories(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));//用来处理日期的显示格式
	accessories=(Accessories)JSONObject.toBean(jsobj,Accessories.class);//把获取到的对象赋给定义的实体对象
	iasd.update(accessories);//执行更新方法
	return SUCCESS;//成功返回
}
//删除配件信息
public String deleteAccessories(){
	HttpServletRequest request=ServletActionContext.getRequest(); //获取上下文的属性
	String ids=request.getParameter("ids");//得到数组
	String[] idStr = ids.split(",");//因为数组中放的是对象，用逗号分离
	int[] id=new int[idStr.length];//定义一个数组，长度和从页面传来的数组长度一样
	for(int i=0;i<id.length;i++){
		id[i]=Integer.parseInt(idStr[i]);//循环遍历数组
	}
	iasd.delete(id);//执行删除操作
	return SUCCESS;//成功返回
}
//全查配件信息
public String accessoriesList(){
	Map map=new HashMap();
	List list=iasd.searchAll();
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/Accessories")+"\\"+"title.json");//获取列标题 	
	map.put("total",list.size());
	map.put("rows", list);
	map.put("title",title);
	JsonConfig jsonConfig = new JsonConfig();
	jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));//数据日期格式处理  
	jsonObj = JSONObject.fromObject(map,jsonConfig);//用json执行
	System.out.println(jsonObj);//输出传到前台的数据
	return SUCCESS;//成功返回
}
//查询配件信息
public String searchAccessories(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	String accessoriesNo=request.getParameter("AccessoriesNo");//获取到jsp页面传来的三个数据
	String acccessoriesName=request.getParameter("AccessoriesName");
	String carArea=request.getParameter("CarArea");
	//System.out.println();
	Accessories access=new Accessories();//定义一个新的对象封装信息
	access.setAcceNo(accessoriesNo);
	access.setAcceName(acccessoriesName);
	access.setCarArea(carArea);
	Map map=new HashMap();
	List list=iasd.searchAccessories(access);//执行查询操作
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/Accessories")+"\\"+"title.json");//获取列标题 
	map.put("total",list.size());
	map.put("rows", list);
	map.put("title",title);
	JsonConfig jsonConfig = new JsonConfig();
	jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));//数据日期格式处理  
	jsonObj=JSONObject.fromObject(map ,jsonConfig);	
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

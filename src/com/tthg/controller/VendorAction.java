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
import com.tthg.entity.Vendor;
import com.tthg.service.IVendorServiceDAO;
@Controller
public class VendorAction extends ActionSupport{
   private Vendor vendor;//定义一个对象，用来接收下面方法中的返回对象
   private JSONObject jsonObj;//定义一个JSONObject类，用来通过json获取对象
   @Autowired
   private IVendorServiceDAO vendorSDAO;//定义服务层接口
public Vendor getVendor() {
	return vendor;
}
public void setVendor(Vendor vendor) {
	this.vendor = vendor;
}
public JSONObject getJsonObj() {
	return jsonObj;
}
public void setJsonObj(JSONObject jsonObj) {
	this.jsonObj = jsonObj;
}
public IVendorServiceDAO getVendorSDAO() {
	return vendorSDAO;
}
public void setVendorSDAO(IVendorServiceDAO vendorSDAO) {
	this.vendorSDAO = vendorSDAO;
}
@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	return SUCCESS;
}
//新增供应商信息
public String  addVendor(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject  jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	vendor=(Vendor)JSONObject.toBean(jsobj,Vendor.class);//把获取到的对象赋给定义的实体对象
	vendorSDAO.add(vendor);//执行增加方法
	return SUCCESS;//成功返回
}
//更新供应商信息
public String updateVendor(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject  jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	vendor=(Vendor)JSONObject.toBean(jsobj,Vendor.class);//把获取到的对象赋给定义的实体对象
	vendorSDAO.update(vendor);//执行更新方法
	return SUCCESS;//成功返回
}
//删除供应商信息
public String deleteVendor(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	String ids=request.getParameter("ids");//得到数组
	String[] idStr=ids.split(",");//因为数组中放的是对象，用逗号分离
	int[] id=new int[idStr.length];//定义一个数组，长度和从页面传来的数组长度一样
	for(int i=0;i<id.length;i++){
		id[i]=Integer.parseInt(idStr[i]);//循环遍历数组
	}
	vendorSDAO.delete(id);//执行删除操作
	return SUCCESS;//成功返回
}
//全查方法
public String vendorList(){
	Map map=new HashMap();//定义一个map
	List list=vendorSDAO.searchAll();//调用全查方法
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/Vendor")+"\\"+"title.json");//获取列标题
	map.put("total",list.size());//长度
	map.put("rows", list);//宽度
	map.put("title",title);//标题
	jsonObj=JSONObject.fromObject(map);//用json执行
	System.out.println(jsonObj);//输出传到前台的数据
	return SUCCESS;//成功返回
}
//按条件查询
public String searchVendor(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	String vendorName=request.getParameter("vendorName");//获取到jsp页面传来的三个数据
	String address=request.getParameter("address");
	String vendorNo=request.getParameter("vendorNo");
	Vendor  ven=new Vendor();//定义一个新的对象封装信息
	ven.setVendorName(vendorName);
	ven.setAddress(address);
	ven.setVendorNo(vendorNo);
	Map map=new HashMap();
	List list=vendorSDAO.searchVendor(ven);//执行查询操作
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/Vendor")+"\\"+"title.json");//获取列标题
	map.put("total",list.size());
	map.put("rows", list);
	map.put("title",title);
	jsonObj=JSONObject.fromObject(map);//用json执行
	//System.out.println(jsonObj);//输出传到前台的数据
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

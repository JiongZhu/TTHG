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
import com.tthg.entity.Turnover;
import com.tthg.service.ITurnovermangerServiceDAO;

@Controller
public class TurnovermangerAction extends ActionSupport{
private Turnover turnover;//定义一个对象，用来接收下面方法中的返回对象
private JSONObject jsonObj;//定义一个JSONObject类，用来通过json获取对象
@Autowired
private ITurnovermangerServiceDAO itumDAO;
public Turnover getTurnover() {
	return turnover;
}
public void setTurnover(Turnover turnover) {
	this.turnover = turnover;
}
public JSONObject getJsonObj() {
	return jsonObj;
}
public void setJsonObj(JSONObject jsonObj) {
	this.jsonObj = jsonObj;
}
public ITurnovermangerServiceDAO getItumDAO() {
	return itumDAO;
}
public void setItumDAO(ITurnovermangerServiceDAO itumDAO) {
	this.itumDAO = itumDAO;
}
public String addTurnover(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	turnover=(Turnover)JSONObject.toBean(jsobj,Turnover.class);//把获取到的对象赋给定义的实体对象
	itumDAO.add(turnover);//执行添加操作
	return SUCCESS;
}
public String updateTurnover(){
	HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
	JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
	turnover=(Turnover)JSONObject.toBean(jsobj,Turnover.class);//把获取到的对象赋给定义的实体对象
	itumDAO.update(turnover);//执行更新操作
	return SUCCESS;
}
public String deleteTurnover(){
	HttpServletRequest request=ServletActionContext.getRequest(); //获取上下文的属性
	String ids=request.getParameter("ids");//得到数组
	String[] idStr = ids.split(",");//因为数组中放的是对象，用逗号分离
	int[] id=new int[idStr.length];
	for(int i=0;i<id.length;i++){
		id[i]=Integer.parseInt(idStr[i]);//循环遍历数
	}
	System.out.println("id:"+id[0]);
	itumDAO.delete(id);
	return SUCCESS;
}
public String turnoverList(){
	Map map=new HashMap();//定义一个map
	List list=itumDAO.searchAll();
	JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/Turnovermanger")+"\\"+"title.json");//获取列标题
	map.put("total",list.size());
	map.put("rows", list);
	map.put("title",title);
	jsonObj=JSONObject.fromObject(map);//用json执行
	System.out.println(jsonObj);//输出传到前台的数据
	return SUCCESS;//成功返回
}

public String searchTurnover(){
	if ("".equals(turnover.getId())||null==turnover.getId()) {
		this.turnoverList();
	}
	else{
		Map map=new HashMap();
		List<Turnover> list=itumDAO.searchTurnover(turnover);
		JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/Turnovermanger")+"\\"+"title.json");//获取列标题
		map.put("total",list.size());
		map.put("rows", list);
		map.put("title",title);
		jsonObj=JSONObject.fromObject(map);//用json执行
	}
	return SUCCESS;
}
//从给定位置读取Json文件
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

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
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.tthg.entity.Department;
import com.tthg.entity.Salesnum;
import com.tthg.service.ISalesnummangerServiceDAO;
@Controller
public class SalesnummangerAction extends ActionSupport{
	private Salesnum salesnum;//定义一个对象，用来接收下面方法中的返回对象
	private JSONObject jsonObj;//定义一个JSONObject类，用来通过json获取对象
	@Autowired
	private ISalesnummangerServiceDAO salesmanserviceDAO;//定义服务层接口
	public Salesnum getSalesnum() {
		return salesnum;
	}
	public void setSalesnum(Salesnum salesnum) {
		this.salesnum = salesnum;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public ISalesnummangerServiceDAO getSalesmanserviceDAO() {
		return salesmanserviceDAO;
	}
	public void setSalesmanserviceDAO(ISalesnummangerServiceDAO salesmanserviceDAO) {
		this.salesmanserviceDAO = salesmanserviceDAO;
	}
	//增加销售信息
	public String addSalesnumman(){
		HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
		salesnum=(Salesnum)JSONObject.toBean(jsobj,Salesnum.class);//把获取到的对象赋给定义的实体对象
		salesmanserviceDAO.add(salesnum);//执行增加方法
		return SUCCESS;//成功返回
	}
	//更新销售信息
	public String updateSalesnumman(){
		HttpServletRequest request=ServletActionContext.getRequest();//获取上下文的属性
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//通过json获取字符串
		salesnum=(Salesnum)JSONObject.toBean(jsobj,Salesnum.class);//把获取到的对象赋给定义的实体对象
		salesmanserviceDAO.update(salesnum);//执行更新方法
		return SUCCESS;//成功返回
	}
	//删除销售信息
	public String deleteSalesnumman(){
		HttpServletRequest request=ServletActionContext.getRequest(); //获取上下文的属性
		String ids=request.getParameter("ids");//得到数组
		String[] idStr = ids.split(",");//因为数组中放的是对象，用逗号分离
		int[] id=new int[idStr.length];
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);//循环遍历数组
		}
		salesmanserviceDAO.delete(id);//执行删除操作
		return SUCCESS;//成功返回
	}
	//全查销售信息
	public String salesnummanList(){
	  Map map=new HashMap();//定义一个map
	  List list=salesmanserviceDAO.searchAll();//调用全查方法
      JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/Salesnummanger")+"\\"+"title.json");//获取列标题
      map.put("total",list.size());
  	  map.put("rows", list);
 	  map.put("title",title);
 	 jsonObj=JSONObject.fromObject(map);//用json执行
 	 System.out.println(jsonObj);//输出传到前台的数据
 	 return SUCCESS;//成功返回
	}
	public String searchSalesnum(){

		if ("".equals(salesnum.getId())||null==salesnum.getId()) {
			this.salesnummanList();
		}else{
			//过滤
			Map map=new HashMap();
			List<Salesnum> list=salesmanserviceDAO.searchSalesnum(salesnum);//执行查询操作
			JSONArray title=this.readJson(ServletActionContext.getServletContext().getRealPath("/Background/Salesnummanger")+"\\"+"title.json");//获取列标题
			map.put("total",list.size());
			map.put("rows", list);
			map.put("title",title);
			jsonObj=JSONObject.fromObject(map);//转换成json	
		}
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

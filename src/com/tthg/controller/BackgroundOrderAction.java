package com.tthg.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.tthg.entity.Appointment;
import com.tthg.entity.Order;
import com.tthg.service.IOrderServiceDAO;
import com.tthg.util.DateJsonValueProcessor;
import com.tthg.util.ReadJsonFile;
@Controller
public class BackgroundOrderAction extends ActionSupport {
	// 对象实例
	private Order order;
	// ajax返回参数
	private JSONObject jsonObj;
	@Autowired
	private IOrderServiceDAO isd;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public IOrderServiceDAO getIsd() {
		return isd;
	}
	public void setIsd(IOrderServiceDAO isd) {
		this.isd = isd;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	// 新增订单
	public String addOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));//获取前台表单封装的json数据
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));//处理前台封装过来的日期格式
		order = (Order) JSONObject.toBean(jsobj, Order.class);//将json数据转换为实体
		order.setSubscriptionId(order.getSid());//处理外键	
		order.setState("未审核");
		isd.add(order);
		return SUCCESS;
	}

	// 修改订单
	public String updateOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));//获取前台表单封装的json数据
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));//处理前台封装过来的日期格式
		order = (Order) JSONObject.toBean(jsobj, Order.class);//将json数据转换为实体
		order.setSubscriptionId(order.getSid());//处理外键
		isd.update(order);
		HttpSession session=request.getSession();
		session.setAttribute("zstate",order);
		return SUCCESS;
	}

	// 删除订单
	public String deleteOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");//获取前台传过来的选定的记录的id数组
		String[] idStr = ids.split(",");
		int[] id = new int[idStr.length];
		for (int i = 0; i < id.length; i++) {
			id[i] = Integer.parseInt(idStr[i]);//转换为int数组
		}
		isd.delete(id);
		return SUCCESS;
	}

	// 多条件组合查询
	public String searchOrder() {
		Map map = new HashMap();
		List list = isd.searchOrder(order);
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/order")
				+ "\\" + "OrderTitle.json";//从文件读取前台datagrid需要动态显示的表头字段
		JSONArray title = new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));// 数据库向前台数据日期格式处理
		map.put("total", list.size());//总的记录数，用于前台分页
		map.put("rows", list);//记录的详细内容
		map.put("title", title);//表头字段名及列属性设置
		jsonConfig.setExcludes(new String[]{"subscription","invoice","contract","callback"});//设置实体字段的过滤，用于解决hibernate建立实体间联系问题
		jsonObj = JSONObject.fromObject(map, jsonConfig);//将数据封装为JSONObject数据类型
		return SUCCESS;
	}

	// 全查订单
	public String orderList() {
		Map map = new HashMap();
		List list = isd.searchAll();
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/order")
				+ "\\" + "OrderTitle.json";//从文件读取前台datagrid需要动态显示的表头字段
		JSONArray title = new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));// 数据库向前台数据日期格式处理
		map.put("total", list.size());//总的记录数，用于前台分页
		map.put("rows", list);//记录的详细内容
		map.put("title", title);//表头字段名及列属性设置
		jsonConfig.setExcludes(new String[]{"subscription","invoice","contract","callback"});//设置实体字段的过滤，用于解决hibernate建立实体间联系问题
		jsonObj = JSONObject.fromObject(map,jsonConfig);//将数据封装为JSONObject数据类型		
		return SUCCESS;
	}
	
	//销售顾问订单登记审核通过提示信息
	public String zState(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Map map=new HashMap();
		Order ord=(Order)session.getAttribute("zstate");
		String str=ord.getState();
		if(str.equals("已审核")){
			map.put("result", true);
			map.put("msg","编号为"+ord.getOrderNo()+"的订单登记已审核通过！" );
		}else{
			map.put("result", false);
		}
		jsonObj=JSONObject.fromObject(map);
		session.removeAttribute("zstate");
		return SUCCESS;
	}
}

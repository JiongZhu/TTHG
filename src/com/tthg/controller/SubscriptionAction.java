package com.tthg.controller;

import java.util.ArrayList;
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
import com.tthg.entity.Reception;
import com.tthg.entity.Subscription;
import com.tthg.entity.Vehicle;
import com.tthg.service.ISubscriptionServiceDAO;
import com.tthg.service.IVehicleServiceDAO;
import com.tthg.util.DateJsonValueProcessor;
import com.tthg.util.ReadJsonFile;
@Controller
public class SubscriptionAction extends ActionSupport {
	// 对象实例
	private Subscription subscription;
	private Vehicle vehicle;
	// ajax返回参数
	private JSONObject jsonObj;
	private JSONArray jsonArr;
	@Autowired
	private ISubscriptionServiceDAO isd;
	@Autowired
	private IVehicleServiceDAO ivd;
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public ISubscriptionServiceDAO getIsd() {
		return isd;
	}
	public void setIsd(ISubscriptionServiceDAO isd) {
		this.isd = isd;
	}	
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public JSONArray getJsonArr() {
		return jsonArr;
	}
	public void setJsonArr(JSONArray jsonArr) {
		this.jsonArr = jsonArr;
	}	
	public IVehicleServiceDAO getIvd() {
		return ivd;
	}
	public void setIvd(IVehicleServiceDAO ivd) {
		this.ivd = ivd;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	// 新增订购协议
	public String addSubscription() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
		subscription=(Subscription)JSONObject.toBean(jsobj,Subscription.class);	
		subscription.setCarStyleId(Integer.parseInt(subscription.getCname()));
		isd.add(subscription);
		return SUCCESS;
	}

	// 修改订购协议
	public String updateSubscription() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
		subscription=(Subscription)JSONObject.toBean(jsobj,Subscription.class);	
		subscription.setCarStyleId(Integer.parseInt(subscription.getCname()));
		isd.update(subscription);
		return SUCCESS;
	}

	// 删除订购协议
	public String deleteSubscription() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		int[] id = new int[idStr.length];
		for (int i = 0; i < id.length; i++) {
			id[i] = Integer.parseInt(idStr[i]);
		}
		isd.delete(id);
		return SUCCESS;
	}

	// 多条件组合查询
	public String searchSubscription() {
		Map map = new HashMap();
		List list = isd.searchSubscription(subscription);
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/order")
				+ "\\" + "SubscriptionTitle.json";
		JSONArray title = new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));// 数据日期格式处理
		map.put("total", list.size());
		map.put("rows", list);
		map.put("title", title);
		jsonConfig.setExcludes(new String[]{"vehicle","order"});
		jsonObj = JSONObject.fromObject(map, jsonConfig);
		return SUCCESS;
	}

	// 全查订购协议
	public String subscriptionList() {
		Map map = new HashMap();
		List list = isd.searchAll();
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/order")
				+ "\\" + "SubscriptionTitle.json";
		JSONArray title = new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));// 数据日期格式处理
		map.put("total", list.size());
		map.put("rows", list);
		map.put("title", title);
		jsonConfig.setExcludes(new String[]{"vehicle","order"});
		jsonObj = JSONObject.fromObject(map,jsonConfig);		
		return SUCCESS;
	}
	//处理前台下拉列表key-value的对应问题
	public String combobox(){
		List<Vehicle> vlist=ivd.searchAll();
		System.out.println(vlist.size());
		List<Map<String,Object>> clist=new ArrayList<Map<String,Object>>();	
		for(int i=0;i<vlist.size();i++){
			Vehicle v=vlist.get(i);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("vid", v.getId());
			map.put("vname",v.getVehicleName());
			clist.add(map);
		}
		jsonArr=JSONArray.fromObject(clist);
		return INPUT;
	}
}

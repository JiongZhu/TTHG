package com.tthg.controller;

import java.util.ArrayList;
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
import com.tthg.entity.IntentionCustomer;
import com.tthg.entity.Vehicle;
import com.tthg.service.IIntentionCustomerServiceDAO;
import com.tthg.service.IVehicleServiceDAO;
import com.tthg.util.ReadJsonFile;

@Controller
public class IntentionCustomerAction extends ActionSupport {
	//对象实例
	private IntentionCustomer customer;
	private Vehicle vehicle;
	//ajax返回参数
	private JSONObject jsonObj;
	private JSONArray jsonArr;
	@Autowired
	private IIntentionCustomerServiceDAO isd;
	@Autowired
	private IVehicleServiceDAO ivd;
	public IntentionCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(IntentionCustomer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public IIntentionCustomerServiceDAO getIsd() {
		return isd;
	}

	public void setIsd(IIntentionCustomerServiceDAO isd) {
		this.isd = isd;
	}
	
	public IVehicleServiceDAO getIvd() {
		return ivd;
	}

	public void setIvd(IVehicleServiceDAO ivd) {
		this.ivd = ivd;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	
	public JSONArray getJsonArr() {
		return jsonArr;
	}

	public void setJsonArr(JSONArray jsonArr) {
		this.jsonArr = jsonArr;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	//新增客户信息
	public String addCustomer(){
		HttpServletRequest request=ServletActionContext.getRequest();
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));
		customer=(IntentionCustomer)JSONObject.toBean(jsobj,IntentionCustomer.class);		
		isd.add(customer);
		return SUCCESS;
	}
	//修改客户信息
	public String updateCustomer(){
		HttpServletRequest request=ServletActionContext.getRequest();
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));
		customer=(IntentionCustomer)JSONObject.toBean(jsobj,IntentionCustomer.class);
		isd.update(customer);
		
		return SUCCESS;
	}
	//删除客户deleteCustomer
	public String deleteCustomer(){
		HttpServletRequest request=ServletActionContext.getRequest(); 
		String ids=request.getParameter("ids");
		String[] idStr = ids.split(",");
		int[] id=new int[idStr.length];
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);
		}
        isd.delete(id);
        return SUCCESS;
	}
	//全查客户
	public String customerList(){
		Map map=new HashMap();
		List list=isd.searchAll();
		String path=ServletActionContext.getServletContext().getRealPath("/Background/customer")+"\\"+"IntentionCustomerTitle.json";
		JSONArray title=new ReadJsonFile(path).getJsonArray();	
		map.put("total",list.size());
		map.put("rows", list);
		map.put("title",title);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"vehicle","customer"});
		jsonObj=JSONObject.fromObject(map,jsonConfig);
		return SUCCESS;
	}
    //多条件组合查询
	public String searchCustomer(){
		Map map=new HashMap();
		List list=isd.searchCustomer(customer);
		String path=ServletActionContext.getServletContext().getRealPath("/Background/customer")+"\\"+"IntentionCustomerTitle.json";
		JSONArray title=new ReadJsonFile(path).getJsonArray();		
		map.put("total",list.size());
		map.put("rows", list);
		map.put("title",title);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"vehicle","customer"});
		jsonObj=JSONObject.fromObject(map,jsonConfig);	
		return SUCCESS;
	}
	//处理前台下拉列表key-value的对应问题
	public String combobox(){
		List<Vehicle> vlist=ivd.searchAll();
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

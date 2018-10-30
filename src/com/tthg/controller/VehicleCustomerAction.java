package com.tthg.controller;

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
import com.tthg.dao.IVehicleCustomerDAO;
import com.tthg.entity.VehicleCustomer;
import com.tthg.util.ReadJsonFile;
@Controller
public class VehicleCustomerAction extends ActionSupport {
	//对象实例
	private VehicleCustomer customer;
	//ajax返回参数
	private JSONObject jsonObj;
	
	@Autowired
	private IVehicleCustomerDAO isd;

	public VehicleCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(VehicleCustomer customer) {
		this.customer = customer;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}

	public IVehicleCustomerDAO getIsd() {
		return isd;
	}

	public void setIsd(IVehicleCustomerDAO isd) {
		this.isd = isd;
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
		customer=(VehicleCustomer)JSONObject.toBean(jsobj,VehicleCustomer.class);		
		isd.add(customer);
		return SUCCESS;
	}
	//修改客户信息
	public String updateCustomer(){
		HttpServletRequest request=ServletActionContext.getRequest();
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));
		customer=(VehicleCustomer)JSONObject.toBean(jsobj,VehicleCustomer.class);		
		isd.update(customer);
		return SUCCESS;
	}
	//删除客户
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
    //多条件组合查询
	public String searchCustomer(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String customerName=request.getParameter("customerName");
		String cardId=request.getParameter("cardId");
		String consultantName=request.getParameter("consultantName");
		VehicleCustomer customer=new VehicleCustomer();	
		customer.setCustomerName(customerName);
		customer.setCardId(cardId);
		customer.setConsultantName(consultantName);		
		Map map=new HashMap();
		List list=isd.searchCustomer(customer);
		String path=ServletActionContext.getServletContext().getRealPath("/Background/customer")+"\\"+"VehicleCustomerTitle.json";
		JSONArray title=new ReadJsonFile(path).getJsonArray();		
		map.put("total",list.size());
		map.put("rows", list);
		map.put("title",title);
		jsonObj=JSONObject.fromObject(map);		
		return SUCCESS;
	}
	//全查客户
	public String customerList(){
		Map map=new HashMap();
		List list=isd.searchAll();
		String path=ServletActionContext.getServletContext().getRealPath("/Background/customer")+"\\"+"VehicleCustomerTitle.json";
		JSONArray title=new ReadJsonFile(path).getJsonArray();
		map.put("total",list.size());
		map.put("rows", list);
		map.put("title",title);
		jsonObj=JSONObject.fromObject(map);
		return SUCCESS;
	}
}

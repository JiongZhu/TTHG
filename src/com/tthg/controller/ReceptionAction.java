package com.tthg.controller;

import java.util.ArrayList;
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
import com.tthg.entity.Reception;
import com.tthg.entity.Vehicle;
import com.tthg.service.IReceptionServiceDAO;
import com.tthg.service.IVehicleServiceDAO;
import com.tthg.util.DateJsonValueProcessor;
import com.tthg.util.ReadJsonFile;
@Controller
public class ReceptionAction extends ActionSupport {
	//对象实例
	private Reception reception;
	private Vehicle vehicle;
	//ajax返回参数
	private JSONObject jsonObj;
	private JSONArray jsonArr;
	@Autowired
	private IReceptionServiceDAO isd;
	@Autowired
	private IVehicleServiceDAO ivd;
	public Reception getReception() {
		return reception;
	}
	public void setReception(Reception reception) {
		this.reception = reception;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public IReceptionServiceDAO getIsd() {
		return isd;
	}
	public void setIsd(IReceptionServiceDAO isd) {
		this.isd = isd;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public IVehicleServiceDAO getIvd() {
		return ivd;
	}
	public void setIvd(IVehicleServiceDAO ivd) {
		this.ivd = ivd;
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
		return SUCCESS;
	}
	//新增接待日志
	public String addReception(){
		HttpServletRequest request=ServletActionContext.getRequest();
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
		reception=(Reception)JSONObject.toBean(jsobj,Reception.class);	
		reception.setCarStyleId(Integer.parseInt(reception.getCname()));
		reception.setState("未审核");
		isd.add(reception);
		return SUCCESS;
	}
	//修改日志信息
	public String updateReception(){
		HttpServletRequest request=ServletActionContext.getRequest();
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
		reception=(Reception)JSONObject.toBean(jsobj,Reception.class);
		reception.setCarStyleId(Integer.parseInt(reception.getCname()));
		isd.update(reception);
		HttpSession session=request.getSession();
		session.setAttribute("zstate",reception);
		return SUCCESS;
	}
	//删除日志
	public String deleteReception(){
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
	public String searchReception(){
		System.out.println(reception.getReceiver());
		Map map=new HashMap();
		List list=isd.searchReception(reception);		
		String path=ServletActionContext.getServletContext().getRealPath("/Background/sales/exhibition")+"\\"+"ReceptionTitle.json";
		JSONArray title=new ReadJsonFile(path).getJsonArray();		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));//数据日期格式处理  
		map.put("total",list.size());
		map.put("rows", list);
		map.put("title",title);
		jsonConfig.setExcludes(new String[]{"vehicle"});
		jsonObj=JSONObject.fromObject(map,jsonConfig);		
		return SUCCESS;
	}
	//全查日志
	public String receptionList(){
		Map map=new HashMap();
		List list=isd.searchAll();
		String path=ServletActionContext.getServletContext().getRealPath("/Background/sales/exhibition")+"\\"+"ReceptionTitle.json";
		JSONArray title=new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));//数据日期格式处理  
		map.put("total",list.size());
		map.put("rows", list);
		map.put("title",title);
		jsonConfig.setExcludes(new String[]{"vehicle"});
		jsonObj = JSONObject.fromObject(map,jsonConfig);
		return SUCCESS;
	}
	//接待员接待日志审核通过提示信息
	public String zState(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Map map=new HashMap();
		Reception rec=(Reception)session.getAttribute("zstate");
		String str=rec.getState();
		if(str.equals("已审核")){
			map.put("result", true);
			map.put("msg","编号为"+rec.getReceptionNo()+"的展厅接待日志已审核通过！" );
		}else{
			map.put("result", false);
		}
		jsonObj=JSONObject.fromObject(map);
		session.removeAttribute("zstate");
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

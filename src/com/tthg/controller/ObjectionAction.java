package com.tthg.controller;

import java.util.Date;
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
import com.tthg.entity.Objection;
import com.tthg.service.IObjectionServiceDAO;
import com.tthg.util.DateJsonValueProcessor;
import com.tthg.util.ReadJsonFile;
@Controller
public class ObjectionAction extends ActionSupport {
	// 对象实例
	private Objection objection;
	// ajax返回参数
	private JSONObject jsonObj;
	@Autowired
	private IObjectionServiceDAO isd;
	public Objection getObjection() {
		return objection;
	}
	public void setObjection(Objection objection) {
		this.objection = objection;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public IObjectionServiceDAO getIsd() {
		return isd;
	}
	public void setIsd(IObjectionServiceDAO isd) {
		this.isd = isd;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	// 新增异议处理
	public String addObjection() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));
		objection = (Objection) JSONObject.toBean(jsobj, Objection.class);
		isd.add(objection);
		return SUCCESS;
	}

	// 修改异议处理信息
	public String updateObjection() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));
		objection = (Objection) JSONObject.toBean(jsobj, Objection.class);
		isd.update(objection);
		return SUCCESS;
	}

	// 删除异议处理
	public String deleteObjection() {
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
	public String searchObjection() {
		Map map = new HashMap();
		List list = isd.searchObjection(objection);
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/service")
				+ "\\" + "ObjectionTitle.json";
		JSONArray title = new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));// 数据日期格式处理
		map.put("total", list.size());
		map.put("rows", list);
		map.put("title", title);
		jsonObj = JSONObject.fromObject(map, jsonConfig);
		return SUCCESS;
	}

	// 全查异议处理
	public String objectionList() {
		Map map = new HashMap();
		List list = isd.searchAll();
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/service")
				+ "\\" + "ObjectionTitle.json";
		JSONArray title = new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));// 数据日期格式处理
		map.put("total", list.size());
		map.put("rows", list);
		map.put("title", title);
		jsonObj = JSONObject.fromObject(map, jsonConfig);
		return SUCCESS;
	}	
}

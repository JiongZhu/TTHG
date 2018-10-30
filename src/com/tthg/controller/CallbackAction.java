package com.tthg.controller;

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
import com.tthg.entity.Callback;
import com.tthg.service.ICallbackServiceDAO;
import com.tthg.util.DateJsonValueProcessor;
import com.tthg.util.ReadJsonFile;
@Controller
public class CallbackAction extends ActionSupport {
	// 对象实例
	private Callback callback;
	// ajax返回参数
	private JSONObject jsonObj;
	@Autowired
	private ICallbackServiceDAO isd;
	public Callback getCallback() {
		return callback;
	}
	public void setCallback(Callback callback) {
		this.callback = callback;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public ICallbackServiceDAO getIsd() {
		return isd;
	}
	public void setIsd(ICallbackServiceDAO isd) {
		this.isd = isd;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	// 新增客户回访
	public String addCallback() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));//获取前台表单封装的json数据
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));//处理前台封装过来的日期格式
		callback=(Callback)JSONObject.toBean(jsobj,Callback.class);	
		callback.setOrderId(callback.getOid());
		isd.add(callback);
		return SUCCESS;
	}

	// 修改客户回访
	public String updateCallback() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
		callback=(Callback)JSONObject.toBean(jsobj,Callback.class);	
		callback.setOrderId(callback.getOid());
		isd.update(callback);	
		return SUCCESS;
	}

	// 删除客户回访
	public String deleteCallback() {
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
	public String searchCallback() {
		Map map = new HashMap();
		List list = isd.searchCallback(callback);
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/service")
				+ "\\" + "CallbackTitle.json";
		JSONArray title = new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));// 数据日期格式处理
		map.put("total", list.size());
		map.put("rows", list);
		map.put("title", title);
		jsonConfig.setExcludes(new String[]{"order"});
		jsonObj = JSONObject.fromObject(map, jsonConfig);
		return SUCCESS;
	}

	// 全查客户回访
	public String callbackList() {
		Map map = new HashMap();
		List list = isd.searchAll();
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/service")
				+ "\\" + "CallbackTitle.json";
		JSONArray title = new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));// 数据日期格式处理
		map.put("total", list.size());
		map.put("rows", list);
		map.put("title", title);
		jsonConfig.setExcludes(new String[]{"order"});
		jsonObj = JSONObject.fromObject(map,jsonConfig);		
		return SUCCESS;
	}	
}

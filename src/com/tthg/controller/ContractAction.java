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
import com.tthg.entity.Contract;
import com.tthg.entity.Reception;
import com.tthg.entity.Subscription;
import com.tthg.service.IContractionServiceDAO;
import com.tthg.util.DateJsonValueProcessor;
import com.tthg.util.ReadJsonFile;
@Controller
public class ContractAction extends ActionSupport {
	// 对象实例
	private Contract contract;
	// ajax返回参数
	private JSONObject jsonObj;
	@Autowired
	private IContractionServiceDAO isd;
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public IContractionServiceDAO getIsd() {
		return isd;
	}
	public void setIsd(IContractionServiceDAO isd) {
		this.isd = isd;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	// 新增购车合同
	public String addContract() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
		contract=(Contract)JSONObject.toBean(jsobj,Contract.class);	
		contract.setOrderId(contract.getOid());
		isd.add(contract);
		return SUCCESS;
	}

	// 修改购车合同
	public String updateContract() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
		contract=(Contract)JSONObject.toBean(jsobj,Contract.class);	
		contract.setOrderId(contract.getOid());
		isd.update(contract);
		return SUCCESS;
	}

	// 删除购车合同
	public String deleteContract() {
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
	public String searchContract() {
		Map map = new HashMap();
		List list = isd.searchContract(contract);
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/deal")
				+ "\\" + "ContractTitle.json";
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

	// 全查购车合同
	public String contractList() {
		Map map = new HashMap();
		List list = isd.searchAll();
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/deal")
				+ "\\" + "ContractTitle.json";
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

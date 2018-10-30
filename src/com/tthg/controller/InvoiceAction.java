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
import com.tthg.entity.Invoice;
import com.tthg.service.IInvoiceServiceDAO;
import com.tthg.util.DateJsonValueProcessor;
import com.tthg.util.ReadJsonFile;
@Controller
public class InvoiceAction extends ActionSupport {
	// 对象实例
	private Invoice invoice;
	// ajax返回参数
	private JSONObject jsonObj;
	@Autowired
	private IInvoiceServiceDAO isd;
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public IInvoiceServiceDAO getIsd() {
		return isd;
	}
	public void setIsd(IInvoiceServiceDAO isd) {
		this.isd = isd;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	// 新增购车发票
	public String addInvoice() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
		invoice=(Invoice)JSONObject.toBean(jsobj,Invoice.class);	
		invoice.setOrderId(invoice.getOid());
		isd.add(invoice);
		return SUCCESS;
	}

	// 修改购车发票
	public String updateInvoice() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
		invoice=(Invoice)JSONObject.toBean(jsobj,Invoice.class);	
		invoice.setOrderId(invoice.getOid());
		isd.update(invoice);
		return SUCCESS;
	}

	// 删除购车发票
	public String deleteInvoice() {
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
	public String searchInvoice() {
		Map map = new HashMap();
		List list = isd.searchInvoice(invoice);
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/deal")
				+ "\\" + "InvoiceTitle.json";
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

	// 全查购车发票
	public String invoiceList() {
		Map map = new HashMap();
		List list = isd.searchAll();
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/deal")
				+ "\\" + "InvoiceTitle.json";
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

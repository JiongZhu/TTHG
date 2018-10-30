package com.tthg.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.tthg.entity.IntentionCustomer;
import com.tthg.entity.Series;
import com.tthg.service.IIntentionCustomerServiceDAO;
@Controller
public class IntentionCustomerEchartAction extends ActionSupport {
	private JSONObject jsonObj;//定义一个JSONObject类，用来通过json获取对象
	@Autowired
	private IIntentionCustomerServiceDAO isd;
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public IIntentionCustomerServiceDAO getIsd() {
		return isd;
	}
	public void setIsd(IIntentionCustomerServiceDAO isd) {
		this.isd = isd;
	}
	@Override
	public String execute() throws Exception {
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"流失原因"}));
        List<String> category = new ArrayList<String>(Arrays.asList(new String []{"车型不合适","预购资金不足","其他原因"}));//横坐标  
        List<Series> series = new ArrayList<Series>();//纵坐标
        List<IntentionCustomer> list=isd.searchAll();
        int j=0,k=0,t=0;
        for(int i=0;i<list.size();i++){
        	IntentionCustomer customer=list.get(i);
        	if(customer.getNegotiation().equals("车型不合适"))
        		j++;
        	if(customer.getNegotiation().equals("预购资金不足"))
        		k++;
        	if(customer.getNegotiation().equals("其他原因"))
        		t++;
        }
        series.add(new Series("销售量", "bar",new ArrayList<Integer>(Arrays.asList(j,k,t))));
        Map map=new HashMap();
        map.put("legend", legend);
        map.put("category", category);
        map.put("series", series);
        jsonObj=JSONObject.fromObject(map);
		return SUCCESS;
	}
}

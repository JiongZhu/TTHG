package com.tthg.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.tthg.entity.Repairnum;
import com.tthg.entity.Salesnum;
import com.tthg.entity.Series;
import com.tthg.entity.Turnover;
import com.tthg.service.IRepairnumServiceDAO;
import com.tthg.service.ISalesServiceDAO;
import com.tthg.service.ITurnoverServiceDAO;
@Controller
public class EchartAction extends ActionSupport {
	private JSONObject jsonObj;//定义一个JSONObject类，用来通过json获取对象
	private Salesnum salesnum;//定义一个对象，用来接收下面方法中的返回对象
	private Repairnum repairnum;//定义一个对象，用来接收下面方法中的返回对象
	private Turnover  turnover;//定义一个对象，用来接收下面方法中的返回对象
	@Autowired
	private ISalesServiceDAO isd;//定义服务层接口
	@Autowired
	private IRepairnumServiceDAO ird;//定义服务层接口
	@Autowired
	private ITurnoverServiceDAO  itd;
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public Salesnum getSalesnum() {
		return salesnum;
	}

	public void setSalesnum(Salesnum salesnum) {
		this.salesnum = salesnum;
	}

	public Repairnum getRepairnum() {
		return repairnum;
	}

	public void setRepairnum(Repairnum repairnum) {
		this.repairnum = repairnum;
	}

	public Turnover getTurnover() {
		return turnover;
	}

	public void setTurnover(Turnover turnover) {
		this.turnover = turnover;
	}

	public ISalesServiceDAO getIsd() {
		return isd;
	}

	public void setIsd(ISalesServiceDAO isd) {
		this.isd = isd;
	}	

	public IRepairnumServiceDAO getIrd() {
		return ird;
	}

	public void setIrd(IRepairnumServiceDAO ird) {
		this.ird = ird;
	}

	public ITurnoverServiceDAO getItd() {
		return itd;
	}

	public void setItd(ITurnoverServiceDAO itd) {
		this.itd = itd;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"销售量","维修量","营业额"}));//数据分组  
        List<String> category = new ArrayList<String>(Arrays.asList(new String []{"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"}));//横坐标  
        List<Series> series = new ArrayList<Series>();//纵坐标
        String str=request.getParameter("str");
       // System.out.println("str1:"+str);
        if(null==str||"".equals(str)){
        	str="2015";
        }
        int numy=Integer.parseInt(str);
        salesnum=isd.getnum(numy); 
        repairnum=ird.getnum(numy);
        turnover=itd.getnum(numy);
        series.add(new Series("销售量", "bar",new ArrayList<Integer>(Arrays.asList(salesnum.getJanuarynum(),salesnum.getFebruarynum(),salesnum.getMarchnum(),
        		salesnum.getAprilnum(),salesnum.getMaynum(),salesnum.getJunenum(),salesnum.getJulynum(),salesnum.getAugustnum(),salesnum.getSeptembernum(),
        		salesnum.getOctobernum(),salesnum.getNovembernum(),salesnum.getDecembernum()))));     
        series.add(new Series("维修量", "bar",new  ArrayList<Integer>(Arrays.asList(repairnum.getJanuarynum(),repairnum.getFebruarynum(),repairnum.getMarchnum(),
        		repairnum.getAprilnum(),repairnum.getMaynum(),repairnum.getJunenum(),repairnum.getJulynum(),repairnum.getAugustnum(),repairnum.getSeptembernum(),
        		repairnum.getOctobernum(),repairnum.getNovembernum(),repairnum.getDecembernum()))));
        series.add(new Series("营业额", "line",new  ArrayList<Integer>(Arrays.asList(turnover.getJanuarynum(),turnover.getFebruarynum(),turnover.getMarchnum(),turnover.getAprilnum(),turnover.getMaynum(),turnover.getJunenum(),turnover.getJulynum(),turnover.getAugustnum(),turnover.getSeptembernum(),turnover.getOctobernum(),turnover.getNovembernum(),turnover.getDecembernum()))));  
        Map map=new HashMap();
        map.put("legend", legend);
        map.put("category", category);
        map.put("series", series);
        jsonObj=JSONObject.fromObject(map);
		return SUCCESS;
	}
}

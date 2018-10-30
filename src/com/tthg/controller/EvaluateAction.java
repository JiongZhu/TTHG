package com.tthg.controller;


import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.tthg.entity.Evaluate;
import com.tthg.service.IEvaluateService;
import com.tthg.service.IPageService;
import com.tthg.util.DateJsonValueProcessor;
/**
 * 维修评估action
 * @author 葛康  编写者
 * @since 2016-12-17 编写时间
 *
 */
@Controller
public class EvaluateAction {
	private Evaluate evaluate;//评估实体
	private JSONObject evJson;//json
	private JSONArray evJsonA;
	private String rows;// 每页显示的记录数  
	private String page;// 当前第几页

	@Autowired
	private IEvaluateService evaluateService;//服务层接口
	@Autowired
	private IPageService pageService;//分页服务层接口
	
	public EvaluateAction(){
		
	}
	//	set与get
	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}
	
	public IEvaluateService getEvaluateService() {
		return evaluateService;
	}

	public void setEvaluateService(IEvaluateService evaluateService) {
		this.evaluateService = evaluateService;
	}

	public JSONObject getEvJson() {
		return evJson;
	}
	public void setEvJson(JSONObject evJson) {
		this.evJson = evJson;
	}
	public JSONArray getEvJsonA() {
		return evJsonA;
	}
	public void setEvJsonA(JSONArray evJsonA) {
		this.evJsonA = evJsonA;
	}
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public IPageService getPageService() {
		return pageService;
	}
	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}
	public String execute(){
		return this.preShowAllEv();
	}

	//	删除维修评估
	public String preRemoveEv(){
		HttpServletRequest request=ServletActionContext.getRequest(); //创建HttpServletRequest对象
		String ids=request.getParameter("ids");//获取页面参数
		String[] idStr = ids.split(",");//分离参数
		int[] id=new int[idStr.length];
		System.out.println(id.length);
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);//属性转化
		}
		boolean result=evaluateService.deleteEvaluate(id);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        evJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}
	//	修改维修评估
	public String preUpdateEv(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象

		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//转换json
		//日期序列化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		//json转换时间格式
		evaluate=(Evaluate)JSONObject.toBean(jsobj,Evaluate.class);//json转化		

		boolean result=evaluateService.updateEvaluate(1, evaluate);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        evJson= JSONObject.fromObject(jsonStr);//json转换

		return "success";
	}
	//全查评估记录
	public String preShowAllEv(){
		String url="from Evaluate ev";
		List<Evaluate> evaluateList=evaluateService.getAllEvaluate();//执行全查操作

		List list=pageService.getList(url,page, rows);//分页操作

		//为额外属性赋值
		List uList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Evaluate ev=(Evaluate) list.get(i);
			ev.setReNo(ev.getRepair().getReNo());
			uList.add(ev);
		}
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"repair"});//过滤多余属性
		//日期序列化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));//日期格式化

		//向页面传值
		Map map=new HashMap();
		map.put("total", evaluateList.size());
		map.put("rows", uList);

		evJson= JSONObject.fromObject(map,jsonConfig); //转换成json  

		System.out.println(evJson);
		return "success";
	}

	//添加维修评估
	public String preAddEv(){		
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//获取页面参数
		//日期格式化
		System.out.println("request.getParameter"+request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		evaluate=(Evaluate)JSONObject.toBean(jsobj,Evaluate.class);//反序列化
		System.out.println("request.getParameter"+request.getParameter("str"));
		
		boolean result=evaluateService.insertEvaluate(evaluate,1);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        evJson= JSONObject.fromObject(jsonStr);//json转换

		return "success";
		
	}
	//复合查询
	public String preSelect(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		HttpSession se=request.getSession();//创建Session对象
		//执行复合查询
		List evaluateList=evaluateService.getEvaluateByComposition(evaluate);
		String url=(String) se.getAttribute("hqlStr");
		List list=pageService.getList(url,page, rows);
		System.out.println("list长度"+list.size());
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"repair"});//过滤多余属性
		//日期格式化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		//为多余属性赋值
		List evList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Evaluate ev=(Evaluate) list.get(i);
			ev.setReNo(ev.getRepair().getReNo());
			evList.add(ev);
		}
		//向页面传值
		Map map=new HashMap();		
		map.put("total",evaluateList.size());
		map.put("rows", evList);

		evJson=JSONObject.fromObject(map,jsonConfig);//转换成json 
		return "success";
	}

}

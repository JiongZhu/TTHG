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

import com.tthg.entity.Component;
import com.tthg.service.IComponentService;
import com.tthg.service.IPageService;
import com.tthg.util.DateJsonValueProcessor;
/**
 * 零件调用action
 * @author 葛康  编写者
 * @since 2016-12-18 编写时间
 */
@Controller
public class ComponentAction {
	private Component component;//零件实体类
	private JSONObject compJson;//json
	private JSONArray compJsonA;
	private String rows;// 每页显示的记录数  
	private String page;// 当前第几页

	@Autowired
	private IComponentService componentService;//零件服务层接口
	@Autowired
	private IPageService pageService;//分页服务层接口
	
	public ComponentAction(){
		
	}
	//	set与get
	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public IComponentService getComponentService() {
		return componentService;
	}

	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}
	
	public JSONObject getCompJson() {
		return compJson;
	}
	
	public void setCompJson(JSONObject compJson) {
		this.compJson = compJson;
	}
	
	public JSONArray getCompJsonA() {
		return compJsonA;
	}
	
	public void setCompJsonA(JSONArray compJsonA) {
		this.compJsonA = compJsonA;
	}
	
	public IPageService getPageService() {
		return pageService;
	}
	
	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
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

	public String execute(){
		return this.preShowAllCo();
	}
	//	添加零件信息
	public String preAddCo(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//获取页面参数
		//日期格式化
		System.out.println("request.getParameter"+request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		component=(Component)JSONObject.toBean(jsobj,Component.class);//反序列化
		System.out.println("request.getParameter"+request.getParameter("str"));
		
		boolean result=componentService.insertComponent(component,1);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        compJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
		
	}
	//	删除零件信息
	public String preRemoveCo(){
		HttpServletRequest request=ServletActionContext.getRequest(); //创建HttpServletRequest对象
		String ids=request.getParameter("ids");//获取页面参数
		String[] idStr = ids.split(",");//分离参数
		int[] id=new int[idStr.length];
		System.out.println(id.length);
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);//属性转化
		}
		boolean result=componentService.deleteComponent(id);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        compJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}
	//修改零件信息
	public String preUpdateCo(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象

		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//转换json
		//日期序列化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		//json转换时间格式
		component=(Component)JSONObject.toBean(jsobj,Component.class);//json转化		

		boolean result=componentService.updateComponent(1, component);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        compJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}
	//全查零件信息
	public String preShowAllCo(){
		String url="from Component comp";
		List<Component> componentList=componentService.getAllComponent();//执行全查操作

		List list=pageService.getList(url,page, rows);//分页操作

		List uList=new ArrayList();
		//为额外属性赋值
		for (int i = 0; i < list.size(); i++) {
			Component comp=(Component) list.get(i);
			comp.setTroNo(comp.getTrouble().getTroNo());
			uList.add(comp);
		}
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"trouble"});
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));//日期格式化

		Map map=new HashMap();
		map.put("total", componentList.size());
		map.put("rows", uList);

		compJson= JSONObject.fromObject(map,jsonConfig); //转换成json  

		System.out.println(compJson);
		return "success";
	}

	//复合查询
	public String preSelect(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		HttpSession se=request.getSession();//创建Session对象

		List componentList=componentService.getComponentByComposition(component);//执行复合查询
		String url=(String) se.getAttribute("hqlStr");
		List list=pageService.getList(url,page, rows);
		System.out.println("list长度"+list.size());
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"trouble"});
		//日期格式化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		
		List evList=new ArrayList();
		//为额外属性赋值
		for (int i = 0; i < list.size(); i++) {
			Component comp=(Component) list.get(i);
			comp.setTroNo(comp.getTrouble().getTroNo());
			evList.add(comp);
		}
		Map map=new HashMap();
		
		map.put("total",componentList.size());
		map.put("rows", evList);

		compJson=JSONObject.fromObject(map,jsonConfig);//转换成json 
		return "success";
	}
}

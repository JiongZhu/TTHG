package com.tthg.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import com.tthg.entity.Engstate;
import com.tthg.entity.Restate;
import com.tthg.entity.Repair;
import com.tthg.entity.Trouble;
import com.tthg.service.IEngstateService;
import com.tthg.service.IPageService;
import com.tthg.service.IRestateService;
import com.tthg.service.IRepairService;
import com.tthg.service.ITroubleService;
import com.tthg.util.DateJsonValueProcessor;
/**
 * 维修状态action
 * @author 葛康  编写者
 * @since 2016-12-15 编写时间
 *
 */
@Controller
public class RestateAction {
	private Restate restate;//状态实体
	private JSONObject resJson;//json
	private JSONArray resJsonA;
	private String rows;// 每页显示的记录数  
	private String page;// 当前第几页

	@Autowired
	private IRestateService restateService;//状态服务层接口
	@Autowired
	private IPageService pageService;//分叶服务层接口
	@Autowired
	private IRepairService repairService;//登记服务层接口
	@Autowired
	private IEngstateService engstateService;//登记服务层接口
	@Autowired
	private ITroubleService troubleService;//维修服务层接口
	
	public RestateAction(){
		
	}
	//get和set
	public Restate getRestate() {
		return restate;
	}

	public void setRestate(Restate restate) {
		this.restate = restate;
	}

	public IRestateService getRestateService() {
		return restateService;
	}

	public ITroubleService getTroubleService() {
		return troubleService;
	}
	
	public IEngstateService getEngstateService() {
		return engstateService;
	}
	
	public void setEngstateService(IEngstateService engstateService) {
		this.engstateService = engstateService;
	}
	
	public void setTroubleService(ITroubleService troubleService) {
		this.troubleService = troubleService;
	}
	
	public IRepairService getRepairService() {
		return repairService;
	}
	
	public void setRepairService(IRepairService repairService) {
		this.repairService = repairService;
	}
	
	public void setRestateService(IRestateService restateService) {
		this.restateService = restateService;
	}
	
	public JSONObject getResJson() {
		return resJson;
	}
	
	public void setResJson(JSONObject resJson) {
		this.resJson = resJson;
	}
	
	public JSONArray getResJsonA() {
		return resJsonA;
	}
	
	public void setResJsonA(JSONArray resJsonA) {
		this.resJsonA = resJsonA;
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
		return this.preShowAllRs();
	}
	//添加状态信息
	public String preAddRs(){
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		//获取页面参数
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));
		//日期序列化
		System.out.println("request.getParameter"+request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		//反序列化
		restate=(Restate)JSONObject.toBean(jsobj,Restate.class);
		System.out.println("request.getParameter"+request.getParameter("str"));
		
		boolean result=restateService.insertRestate(restate,1);
		String jsonStr="";
        if(result){
     	   jsonStr="{\"success\":true}";
        }else{
     	   jsonStr="{\"success\":false}";
        }
        resJson= JSONObject.fromObject(jsonStr);//jsonn转换
		return "success";
	}
	//删除状态信息
	public String preRemoveRs(){
		HttpServletRequest request=ServletActionContext.getRequest(); //创建HttpServletRequest对象
		String ids=request.getParameter("ids");//获取页面参数
		String[] idStr = ids.split(",");//分离参数
		int[] id=new int[idStr.length];
		System.out.println(id.length);
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);//属性转化
		}
		boolean result=restateService.deleteRestate(id);
		String jsonStr="";
        if(result){
     	   jsonStr="{\"success\":true}";
        }else{
     	   jsonStr="{\"success\":false}";
        }
        resJson= JSONObject.fromObject(jsonStr);//jsonn转换
		return "success";
	}
	//修改状态信息
	public String preUpdateRs(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象

		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//转换json
		//日期序列化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		//json转换时间格式
		restate=(Restate)JSONObject.toBean(jsobj,Restate.class);//json转化		
		System.out.println(restate.getExecution());
		boolean result=restateService.updateRestate(1, restate);
		String jsonStr="";
        if(result){
     	   jsonStr="{\"success\":true}";
        }else{
     	   jsonStr="{\"success\":false}";
        }
        resJson= JSONObject.fromObject(jsonStr);//jsonn转换
		//修改维修登记的状态以及工程师状态的完成时间和完成度
		//获取当前时间  
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");       
		Date curDate= new Date(System.currentTimeMillis());     
		String str= formatter.format(curDate);  
		System.out.println("时间"+str);
		if(restate.getExecution()==1){
			Repair repair=restate.getTrouble().getRepair();
			repair.setState(new Short(1+""));
			repairService.updateRepair(1, repair);
			Engstate eng=repair.getEngstate();
			eng.setEdate(str);
			eng.setState(new Short(1+""));
			eng.setExtreNo(repair.getReNo());
			System.out.println("时间："+eng.getEdate());
			engstateService.updateEngstate(1, eng);
		}
		return "success";
	}
	//全查状态信息
	public String preShowAllRs(){
		String url="from Restate res";
		//执行全查操作
		List<Restate> restateList=restateService.getAllRestate();

		List list=pageService.getList(url,page, rows);//分页操作

		//为额外属性赋值
		List uList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Restate res=(Restate) list.get(i);
			res.setTroNo(res.getTrouble().getTroNo());
			uList.add(res);
		}
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"trouble"});
		//日期序列化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));//日期格式化

		//向页面传值
		Map map=new HashMap();
		map.put("total", restateList.size());
		map.put("rows", uList);

		resJson= JSONObject.fromObject(map,jsonConfig); //转换成json  

		System.out.println(resJson);
		return "success";
	}

	//复合查询
	public String preSelect(){
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession se=request.getSession();//创建Session对象
		//执行复合查询
		List restateList=restateService.getRestateByComposition(restate);
		String url=(String) se.getAttribute("hqlStr");
		//分页查询
		List list=pageService.getList(url,page, rows);
		System.out.println("list长度"+list.size());
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"trouble"});
		//日期格式化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		//为额外属性赋值
		List evList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Restate res=(Restate) list.get(i);
			res.setTroNo(res.getTrouble().getTroNo());
			evList.add(res);
		}
		//向页面传值
		Map map=new HashMap();
		map.put("total",restateList.size());
		map.put("rows", evList);

		resJson=JSONObject.fromObject(map,jsonConfig);//转换成json 
		return "success";
	}
	//删除状态信息
	public String preStr(){
		HttpServletRequest request=ServletActionContext.getRequest(); //创建HttpServletRequest对象
		HttpSession session=request.getSession();
		String ids=request.getParameter("ids");//获取页面参数
		String[] idStr = ids.split(",");//分离参数
		int[] id=new int[idStr.length];
		String str="完成登记:";
		String jsonStr="";
		for(int i=0;i<id.length;i++){
			Restate res=restateService.getRestateById(Integer.parseInt(idStr[i])).get(0);
			Trouble tro=res.getTrouble();
			str+=tro.getRepair().getReNo()+" ";
		}
		jsonStr="{\"success\":true,\"message\":\""+str+"\"}";

		session.setAttribute("mess", str);
		System.out.println("str:"+str);
        resJson= JSONObject.fromObject(jsonStr);//jsonn转换
		return "success";
	}

}

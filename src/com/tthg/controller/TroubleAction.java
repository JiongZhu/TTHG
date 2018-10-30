package com.tthg.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.tthg.entity.Trouble;
import com.tthg.service.IPageService;
import com.tthg.service.ITroubleService;
import com.tthg.util.DateJsonValueProcessor;
/**
 * 故障维修action
 * @author 葛康  编写者
 * @since 2016-12-27 编写时间
 *
 */
@Controller
public class TroubleAction {
	private Trouble trouble;//维修实体
	private JSONObject troJson;
	private JSONArray troJsonA;//json
	private String rows;// 每页显示的记录数  
	private String page;// 当前第几页

	@Autowired
	private ITroubleService troubleService;//维修服务层接口
	@Autowired
	private IPageService pageService;//分页服务层接口
	
	public TroubleAction(){
		
	}
//get与set
	public Trouble getTrouble() {
		return trouble;
	}

	public void setTrouble(Trouble trouble) {
		this.trouble = trouble;
	}

	public ITroubleService getTroubleService() {
		return troubleService;
	}

	public void setTroubleService(ITroubleService troubleService) {
		this.troubleService = troubleService;
	}
	
	public JSONObject getTroJson() {
		return troJson;
	}
	public void setTroJson(JSONObject troJson) {
		this.troJson = troJson;
	}
	public JSONArray getTroJsonA() {
		return troJsonA;
	}
	public void setTroJsonA(JSONArray troJsonA) {
		this.troJsonA = troJsonA;
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
		return this.preShowAllTr();
	}
	//添加故障维修信息
	public String preAddTr(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//获取页面参数
		//日期格式化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		trouble=(Trouble)JSONObject.toBean(jsobj,Trouble.class);//反序列化
		
		boolean result=troubleService.insertTrouble(trouble,1);
		String jsonStr="";
        if(result){
     	   jsonStr="{\"success\":true}";
        }else{
     	   jsonStr="{\"success\":false}";
        }
        troJson= JSONObject.fromObject(jsonStr);//jsonn转换
		return "success";
	}
	//删除故障维修信息
	public String preRemoveTr(){
		HttpServletRequest request=ServletActionContext.getRequest(); //创建HttpServletRequest对象
		String ids=request.getParameter("ids");//获取页面参数
		String[] idStr = ids.split(",");//分离参数
		int[] id=new int[idStr.length];
		System.out.println(id.length);
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);//属性转化
		}
		boolean result=troubleService.deleteTrouble(id);
		String jsonStr="";
        if(result){
     	   jsonStr="{\"success\":true}";
        }else{
     	   jsonStr="{\"success\":false}";
        }
        troJson= JSONObject.fromObject(jsonStr);//jsonn转换
		return "success";
	}
	//修改故障维修信息
	public String preUpdateTr(){
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		//获取页面数据并转换成json
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//转换json
		//日期序列化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		//json转换时间格式
		trouble=(Trouble)JSONObject.toBean(jsobj,Trouble.class);//json转化		
		
        boolean result=troubleService.updateTrouble(1, trouble);
        String jsonStr="";
        if(result){
     	   jsonStr="{\"success\":true}";
        }else{
     	   jsonStr="{\"success\":false}";
        }
        troJson= JSONObject.fromObject(jsonStr);//jsonn转换
		return "success";
	}
	//全查故障维修信息
	public String preShowAllTr(){
		String url="from Trouble tro";
		List<Trouble> troubleList=troubleService.getAllTrouble();//执行全查操作

		System.out.println("troubleList.size()"+troubleList.size());
		List list=pageService.getList(url,page, rows);//分页操作

		//为额外属性赋值
		List uList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Trouble tr=(Trouble) list.get(i);
			tr.setReNo(tr.getRepair().getReNo());
			uList.add(tr);
		}
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		//过滤多余属性（维修登记实体，维修状态实体，零件调用）
		jsonConfig.setExcludes(new String[]{"repair","restate","components"});
		//日期序列化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));//日期格式化

		//向页面传值
		Map map=new HashMap();
		map.put("total", troubleList.size());
		map.put("rows", uList);

		troJson= JSONObject.fromObject(map,jsonConfig); //转换成json  

		System.out.println(troJson);
		return "success";
	}

	//复合查询
	public String preSelect(){
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession se=request.getSession();//创建Session对象

		//查询
		List troubleList=troubleService.getTroubleByComposition(trouble);//执行复合查询
		String url=(String) se.getAttribute("hqlStr");
		//分页查询
		List list=pageService.getList(url,page, rows);
		System.out.println("list长度"+list.size());
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		//过滤多余属性（维修登记实体，维修状态实体，零件调用）
		jsonConfig.setExcludes(new String[]{"repair","restate","components"});
		//日期格式化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		//为多余属性赋值
		List evList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Trouble tr=(Trouble) list.get(i);
			tr.setReNo(tr.getRepair().getReNo());
			evList.add(tr);
		}
		//向页面传值
		Map map=new HashMap();
		map.put("total",troubleList.size());
		map.put("rows", evList);

		troJson=JSONObject.fromObject(map,jsonConfig);//转换成json 
		return "success";
	}
	//通知任务处理
	public String checkRep(){
		List<Trouble> troubleList=troubleService.getAllTrouble();//执行全查操作
		String str="新任务：";
		//记录主管安排任务编号
		List<Trouble> list=new ArrayList<Trouble>();
		Iterator it=troubleList.iterator();
		while(it.hasNext()){
			Trouble tro=(Trouble) it.next();
			if (null==tro.getHandle()||"".equals(tro.getHandle())) {
				str+=tro.getRepair().getReNo()+"  ";
				list.add(tro);
			}
		}
		System.out.println("str"+str);
		String jsonStr="{\"success\":true,\"str\":\""+str+"\",\"list\":\""+list+"\"}";
		troJson=JSONObject.fromObject(jsonStr);//转换成json
		return "success";
	}

}

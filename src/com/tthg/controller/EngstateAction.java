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

import com.tthg.entity.Engstate;
import com.tthg.entity.Repair;
import com.tthg.entity.Trouble;
import com.tthg.entity.User;
import com.tthg.service.IEngstateService;
import com.tthg.service.IPageService;
import com.tthg.service.IRepairService;
import com.tthg.service.ITroubleService;
import com.tthg.service.IUserService;
import com.tthg.util.DateJsonValueProcessor;
/**
 * 工程师状态action
 * @author 葛康  编写者
 * @since 2016-12-29 编写时间
 *
 */
@Controller
public class EngstateAction {
	private Engstate engstate;//状态实体
	private Repair repair;//维修登记实体
	private JSONObject enJson;//json
	private JSONArray enJsonA;
	private String rows;// 每页显示的记录数  
	private String page;// 当前第几页
	
	@Autowired
	private IEngstateService engstateService;//用护服务层接口
	@Autowired
	private IRepairService repairService;//维修登记服务层接口
	@Autowired
	private IUserService userService;//用户服务层接口
	@Autowired
	private ITroubleService troubleService;//用户服务层接口
	@Autowired
	private IPageService pageService;//分页服务层接口
	
	public EngstateAction(){
	}
	
	//get好set	
	public String getRows() {
		return rows;
	}

	public Engstate getEngstate() {
		return engstate;
	}

	public void setEngstate(Engstate engstate) {
		this.engstate = engstate;
	}

	public JSONObject getEnJson() {
		return enJson;
	}

	public ITroubleService getTroubleService() {
		return troubleService;
	}

	public void setTroubleService(ITroubleService troubleService) {
		this.troubleService = troubleService;
	}

	public void setEnJson(JSONObject enJson) {
		this.enJson = enJson;
	}

	public JSONArray getEnJsonA() {
		return enJsonA;
	}

	public void setEnJsonA(JSONArray enJsonA) {
		this.enJsonA = enJsonA;
	}

	public IEngstateService getEngstateService() {
		return engstateService;
	}

	public void setEngstateService(IEngstateService engstateService) {
		this.engstateService = engstateService;
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

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public IRepairService getRepairService() {
		return repairService;
	}

	public void setRepairService(IRepairService repairService) {
		this.repairService = repairService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String execute(){
		return this.preShowAllEngstate();
	}
	//工程师状态全查
	public String preShowAllEngstate(){
		String url="from Engstate eng";
		List<Engstate> engstateList=engstateService.getAllEngstate();//执行全查操作

		List list=pageService.getList(url,page, rows);//分页操作

		//额外属性赋值
		List engList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Engstate eng=(Engstate) list.get(i);
			eng.setExtreNo(eng.getRepair().getReNo());
			engList.add(eng);
		}
		
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"repair"});//过滤多余属性（维修登记实体）
		//日期格式化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));

		//向页面传递数据
		Map map=new HashMap();
		map.put("total", engstateList.size());
		map.put("rows", engList);

		enJson= JSONObject.fromObject(map,jsonConfig); //转换成json  

		System.out.println(enJson);
		return "success";
	}
	//添加工程师状态
	public String preAddEngstate(){		
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//获取页面参数
		//日期格式化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		//反序列化
		engstate=(Engstate)JSONObject.toBean(jsobj,Engstate.class);

		boolean result=engstateService.insertEngstate(engstate);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        enJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
		
	}
	//删除工程师状态
	public String preRemoveEngstate(){
		HttpServletRequest request=ServletActionContext.getRequest(); //创建HttpServletRequest对象
		String ids=request.getParameter("ids");//获取页面参数
		String[] idStr = ids.split(",");//分离参数
		int[] id=new int[idStr.length];
		System.out.println(id.length);
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);//属性转化
		}
		boolean result=engstateService.deleteEngstate(id);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        enJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}
	//修改工程师状态
	public String preUpdateEngstate(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象

		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//转换json
		//日期序列化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		//json转换时间格式
		engstate=(Engstate)JSONObject.toBean(jsobj,Engstate.class);//json转化		

		System.out.println("birthday"+request.getParameter("str"));
		boolean result=engstateService.updateEngstate(1, engstate);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        enJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}
	
	//复合查询
	public String preSelect(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		HttpSession se=request.getSession();//创建Session对象

		if (null==engstate.getExtreNo()||"".equals(engstate.getExtreNo())) {
			engstate.setExtreNo("");
		}
		System.out.println("eerfetregr");
		List engstateList=engstateService.getEngstateByComposition(engstate);//执行复合查询
		String url=(String) se.getAttribute("hqlStr");
		List list=pageService.getList(url,page, rows);
		System.out.println("list长度"+list.size());
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"repair"});//过滤多余属性
		//日期格式化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		//为额外属性赋值
		List enList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Engstate en=(Engstate) list.get(i);
			en.setExtreNo(en.getRepair().getReNo());
			enList.add(en);
		}
		//向页面传递数据
		Map map=new HashMap();
		map.put("total",engstateList.size());
		map.put("rows", enList);

		enJson=JSONObject.fromObject(map,jsonConfig);//转换成json 
		return "success";
	}
	
	//添加维修登记接口
	public String preAddRe(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//json转化
		//日期序列化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss"}));
		System.out.println(request.getParameter("str"));
		repair=(Repair)JSONObject.toBean(jsobj,Repair.class);//反序列化
//		repair.setId(-1);
		System.out.println(request.getParameter("str"));
		boolean bo=repairService.insertRepair(repair);//执行添加操作
		//添加故障维修信息
		Trouble trouble=new Trouble();
		trouble.setTroContent(repair.getReContent());
		trouble.setPlateNo(repair.getPlateNo());
		trouble.setUname(repair.getUname());
		trouble.setReNo(repair.getReNo());
		System.out.println("trouble.setReNo"+trouble.getReNo());
		troubleService.insertTrouble(trouble, 1);
		System.out.println("repair.setReNo"+trouble.getRepair().getReNo());
		//添加维修登记记录同事添加工程师状态信息
		Engstate eng=new Engstate();
		User user=new User();
		user.setUname(repair.getUname());
		eng.setUname(repair.getUname());
		System.out.println("repair.getUname()"+repair.getUname());
		List<User> list=userService.getUserByComposition(user);
		System.out.println("repair.getUname()"+list.size());
		eng.setUno(list.get(0).getUno());
		eng.setUname(repair.getUname());
		eng.setExtreNo(repair.getReNo());//必须赋值
		eng.setEdate("0000-00-00 00:00:00");
		eng.setState(new Short(0+""));
		boolean result=engstateService.insertEngstate(eng);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        enJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
		
	}
}

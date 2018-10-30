package com.tthg.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.tthg.entity.Repair;
import com.tthg.entity.Trouble;
import com.tthg.entity.User;
import com.tthg.service.IComponentService;
import com.tthg.service.IPageService;
import com.tthg.service.IRepairService;
import com.tthg.service.ITroubleService;
import com.tthg.service.IUserService;
import com.tthg.util.DateJsonValueProcessor;
/**
 * 维修登记action
 * @author 葛康  编写者
 * @since 2016-12-12 编写时间
 *
 */
@Controller
public class RepairAction {
	private Repair repair;//维修登记实体
	private JSONObject reJson;//json
	private JSONArray reJsonA;
	private String rows;// 每页显示的记录数  
	private String page;// 当前第几页

	@Autowired
	private IRepairService repairService;//维修登记服务层接口
	@Autowired
	private IUserService userService;//用户服务层接口
	@Autowired
	private ITroubleService troubleService;//维修服务层接口
	@Autowired
	private IComponentService componentService;//用户服务层接口
	@Autowired
	private IPageService pageService;//分页接口
	
	public RepairAction(){
		
	}
	//set和get
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

	public ITroubleService getTroubleService() {
		return troubleService;
	}
	
	public void setTroubleService(ITroubleService troubleService) {
		this.troubleService = troubleService;
	}
	
	public JSONObject getReJson() {
		return reJson;
	}

	public void setReJson(JSONObject reJson) {
		this.reJson = reJson;
	}

	public JSONArray getReJsonA() {
		return reJsonA;
	}

	public void setReJsonA(JSONArray reJsonA) {
		this.reJsonA = reJsonA;
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

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IComponentService getComponentService() {
		return componentService;
	}
	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}
	public String execute(){
		return this.preShowAllRe();
	}

	//添加维修登记接口
	public String preAddRe(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//json转化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss"}));
		repair=(Repair)JSONObject.toBean(jsobj,Repair.class);//反序列化
//		repair.setId(-1);
		int i=3333;
		boolean result=repairService.insertRepair(repair);//执行添加操作
		
		//添加故障维修信息
		Trouble trouble=new Trouble();
		trouble.setTroNo(""+i++);
		trouble.setTroContent(repair.getReContent());
		trouble.setPlateNo(repair.getPlateNo());
		trouble.setUname(repair.getUname());
		trouble.setReNo(repair.getReNo());
		troubleService.insertTrouble(trouble, 1);
		
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        reJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
		
	}
	//删除维修登记接口
	public String preRemoveRe(){
		HttpServletRequest request=ServletActionContext.getRequest(); //创建HttpServletRequest对象
		String ids=request.getParameter("ids");//获取页面参数
		String[] idStr = ids.split(",");//分离ids
		int[] id=new int[idStr.length];
		System.out.println(id.length);
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);//类型转换
		}
        boolean result=repairService.deleteRepair(id);//执行删除操作
        String jsonStr="";
        if(result){
     	   jsonStr="{\"success\":true}";
        }else{
     	   jsonStr="{\"success\":false}";
        }
        reJson= JSONObject.fromObject(jsonStr);//转变为json
		return "success";
	}
	//修改维修登记接口
	public String preUpdateRe(){
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		//转换成json
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));	
		//日期序列化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd HH:mm:ss"}));//日期序列化
		repair=(Repair)JSONObject.toBean(jsobj,Repair.class);//json实例化		
		boolean result=repairService.updateRepair(repair.getId(), repair);//执行修改操作
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        reJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}
	//全查维修登记接口
	public String preShowAllRe(){
		List<Repair> reList=repairService.getAllRepair();//全查登记信息
		String url="from Repair rep";
		List list=pageService.getList(url,page, rows);//执行分页操作
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		//过滤多余属性（故障维修实体，维修评估实体，客户支付实体，工程师状态实体）
		jsonConfig.setExcludes(new String[]{"trouble","evaluate","payment","engstate"});//过滤多余参数
		//日期序列化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		System.out.println(list.size());
		//向页面传值
		Map map=new HashMap();
		map.put("total", reList.size());
		map.put("rows", list);

		reJson= JSONObject.fromObject(map,jsonConfig); //转换成json	  

		System.out.println(reJson);
		return "success";
	}

	//复合查询
	public String preSelect(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		HttpSession se=request.getSession();//创建session对象
		//执行复合查询
		List repairList=repairService.getRepairByComposition(repair);
		String url=(String) se.getAttribute("hqlStr");//获取参数数值
		List list=pageService.getList(url,page, rows);//执行分页操作
		System.out.println("list长度"+list.size());
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		//过滤多余属性（故障维修实体，维修评估实体，客户支付实体，工程师状态实体）
		jsonConfig.setExcludes(new String[]{"trouble","evaluate","payment","engstate"});//过滤多余参数
		//日期序列化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));//日期序列化
		//向页面传值
		Map map=new HashMap();
		map.put("total",repairList.size());
		map.put("rows", list);

		reJson=JSONObject.fromObject(map,jsonConfig);//转换成json 
		return "success";
	}
	//处理用户下拉列表
	public String preUser(){
		String url="from User us where us.power>=5 and us.power<=6";
		List<User> list=userService.getUserByPower(url);//执行用户全查
		System.out.println(list.size());
		List<Map<Integer, String>> mlist= new ArrayList<Map<Integer,String>>();  
		//为页面下拉列表传值
		for (int i = 0; i < list.size(); i++) {
			User us=(User) list.get(i);
			Map map=new HashMap();
			map.put("uname", us.getUname());
			map.put("dename", us.getUname());
			mlist.add(map);
		}
		
		reJsonA=JSONArray.fromObject(mlist);//转换成json 
		System.out.println(reJsonA);
		return "input";
	}
	
	//个人信息处理
	public String preShowSelfUser(){
		String url="from User us where us.power=5";
		List<User> userList=userService.getUserByPower(url);

		List list=pageService.getList(url,page, rows);

		//处理部门外键
		List uList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			User u=(User) list.get(i);
			u.setExtreId(u.getDeparts().getId());
			uList.add(u);
		}
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"departs"});
		//日期序列化
		jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));

		//向页面传值
		Map map=new HashMap();
		map.put("total", userList.size());
		map.put("rows", uList);

		reJson= JSONObject.fromObject(map,jsonConfig);   //转换成json 

		System.out.println(reJson);
		return "success";
	}
	//验证车牌信息
	public String prePlate(){
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		String plateno=request.getParameter("plateNo");//获取页面车牌信息
		System.out.println("result:"+plateno);
		boolean result=repairService.getRepairByPlateNo(plateno);//执行查询操作
		String jsonStr="";
        if(result){
     	   jsonStr="{\"success\":true}";
        }else{
     	   jsonStr="{\"success\":false}";
        }
        reJson= JSONObject.fromObject(jsonStr);//jsonn转换
		return "success";
	}
	
	//获取支付信息
	public String prePrice(){
		System.out.println("id:");
		HttpServletRequest request=ServletActionContext.getRequest(); //创建HttpServletRequest对象
		String ids=request.getParameter("ids");//获取页面参数

		int id=Integer.parseInt(ids);//类型转换

		System.out.println("id:"+id);
		//页面成本费处理
		Repair re=repairService.getRepairById(id).get(0);
		System.out.println("Repair:"+re.getReNo());
		Trouble tro=re.getTrouble();
		System.out.println("tro:"+tro);
		System.out.println("Trouble:"+tro.getPlateNo());
		Set set=tro.getComponents();
		System.out.println("set:"+set.size());
		double price=0.0;
		Iterator it = set.iterator();
		while(it.hasNext()){
			Component component=(Component) it.next();
			//单价与数量
			price+=component.getUnitPri()*component.getCnum()+1000;
		}
		System.out.println("价格："+price);
        String jsonStr="";
     	jsonStr="{\"success\":true,\"price\":\""+price+"\"}";

        reJson= JSONObject.fromObject(jsonStr);//转变为json
		return "success";
	}
	
	//验证车牌信息
	public String preStr(){
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest(); 
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("mess");
		String jsonStr="";
		System.out.println("str:"+str);
        if(null!=str&&!"".equals(str)){
     	   jsonStr="{\"success\":true,\"message\":\""+str+"\"}";
        }else{
     	   jsonStr="{\"success\":false,\"message\":\""+str+"\"}";
        }
        session.setAttribute("mess", "");
        reJson= JSONObject.fromObject(jsonStr);//jsonn转换
		return "success";
	}
}

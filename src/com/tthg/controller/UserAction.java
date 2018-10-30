package com.tthg.controller;

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

import com.opensymphony.xwork2.ActionContext;
import com.tthg.entity.Department;
import com.tthg.entity.User;
import com.tthg.service.IDepartService;
import com.tthg.service.IPageService;
import com.tthg.service.IUserService;
import com.tthg.util.DateJsonValueProcessor;
/**
 * 用户action
 * @author 葛康  编写者
 * @since 2016-12-18 编写时间
 *
 */
@Controller
public class UserAction {
	private User user;//用户实体
	private JSONObject usJson;
	private JSONArray usJsonA;
	private String rows;// 每页显示的记录数  
	private String page;// 当前第几页
	
	@Autowired
	private IUserService userService;//用护服务层接口
	@Autowired
	private IDepartService departService;//部门服务层接口
	@Autowired
	private IPageService pageService;//分页服务层接口
	
	public UserAction(){
	}
	//get好set
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JSONObject getUsJson() {
		return usJson;
	}

	public void setUsJson(JSONObject usJson) {
		this.usJson = usJson;
	}

	public JSONArray getUsJsonA() {
		return usJsonA;
	}

	public void setUsJsonA(JSONArray usJsonA) {
		this.usJsonA = usJsonA;
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

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IDepartService getDepartService() {
		return departService;
	}

	public void setDepartService(IDepartService departService) {
		this.departService = departService;
	}

	public IPageService getPageService() {
		return pageService;
	}

	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}

	public String execute(){
		return this.preShowAllUser();
	}
	//用户全查
	public String preShowAllUser(){
		String url="from User us where us.power>0";
		List<User> userList=userService.getUserByPower(url);//执行全查操作

		List list=pageService.getList(url,page, rows);//分页操作

		//为多余属性传值
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
//		JSONObject jsonObject = JSONObject.fromObject(o,config);  
//      tt = (TT)JSONObject.toBean(jsonObject, TT.class);  
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);     
//		jsonConfig.setExcludes(new String[]{"departs","hibernateLazyInitializer"});
		//日期格式化
		jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));//日期格式化

		//向页面传值
		Map map=new HashMap();
		map.put("total", userList.size());
		map.put("rows", uList);

		usJson= JSONObject.fromObject(map,jsonConfig); //转换成json  

		System.out.println(usJson);
		return "success";
	}
	//添加用户
	public String preAddUser(){		
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//获取页面参数
		//日期格式化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		user=(User)JSONObject.toBean(jsobj,User.class);//反序列化
//		System.out.println("user:"+user.getExtreId()+user.getBdate());
//		java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date;
//		try {
//			date = formatter.parse(user.getBdate());
//			user.setBirthday(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			System.out.println("出错");
//			e.printStackTrace();
//		}

		boolean result=userService.insertUser(user, user.getExtreId());
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
       usJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
		
	}
	//删除用户
	public String preRemoveUser(){
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest(); 
		String ids=request.getParameter("ids");//获取页面参数
		String[] idStr = ids.split(",");//分离参数
		int[] id=new int[idStr.length];
		System.out.println(id.length);
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);//属性转化
		}
		boolean result=userService.deleteUser(id);
        //传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
       usJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}
	//修改用户
	public String preUpdateUser(){
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		//转换json
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));
		//日期序列化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		//json转换时间格式
		user=(User)JSONObject.toBean(jsobj,User.class);//json转化		

		System.out.println("birthday"+request.getParameter("str"));
		boolean result=userService.updateUser(user.getExtreId(), user);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
       usJson= JSONObject.fromObject(jsonStr);//json转换

		return "success";
	}
	
	//复合查询
	public String preSelect(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		HttpSession se=request.getSession();//创建Session对象
		if (null==user.getExtreId()||"".equals(user.getExtreId())) {
			user.setExtreId(0);
			System.out.println("list长度");
		}
		//执行复合查询
		List userList=userService.getUserByComposition(user);
		String url=(String) se.getAttribute("hqlStr");
		//分页查询
		List list=pageService.getList(url,page, rows);
		System.out.println("list长度"+list.size());
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"departs"});
		//日期格式化
		jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
		//为多余属性传值
		List uList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			User u=(User) list.get(i);
			u.setExtreId(u.getDeparts().getId());
			uList.add(u);
		}
		//向页面传值
		Map map=new HashMap();
		map.put("total",userList.size());
		map.put("rows", uList);

		usJson=JSONObject.fromObject(map,jsonConfig);//转换成json 
		return "success";
	}
	//处理部门下拉列表
	public String preDm(){
		List<Department> list=departService.getAllDepart();
		System.out.println(list.size());
		List<Map<Integer, String>> mlist= new ArrayList<Map<Integer,String>>();  
		//为页面下拉列表传值
		for (int i = 0; i < list.size(); i++) {
			Department dm=(Department) list.get(i);
			Map map=new HashMap();
			map.put("deId", dm.getId());
			map.put("deName", dm.getDeName());
			mlist.add(map);
		}
		
		usJsonA=JSONArray.fromObject(mlist);//转换成json 
		System.out.println(usJsonA);
		return "input";
	}
	//个人信息处理
	public String preShowSelfUser(){
		User loginUser=(User)ActionContext.getContext().getSession().get("loginUser");
		String url="from User us where us.uname='"+loginUser.getUname()+"'";
		List<User> userList=userService.getUserByPower(url);

		List list=pageService.getList(url,page, rows);

		//为多余属性赋值
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
//		JSONObject jsonObject = JSONObject.fromObject(o,config);  
//      tt = (TT)JSONObject.toBean(jsonObject, TT.class);  
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);     
//		jsonConfig.setExcludes(new String[]{"departs","hibernateLazyInitializer"});
		//日期序列化
		jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));

		//向页面传值
		Map map=new HashMap();
		map.put("total", userList.size());
		map.put("rows", uList);

		usJson= JSONObject.fromObject(map,jsonConfig);   //转换成json 

		System.out.println(usJson);
		return "success";
	}
}

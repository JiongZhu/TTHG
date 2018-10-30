package com.tthg.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tthg.entity.User;
import com.tthg.service.IUserService;
@Controller
public class BackgroundLoginAction extends ActionSupport {
	private User user;//登录用户对象
	private String usersValidateCode;//待验证的验证码
	private JSONObject jsonObj;//json对象
	@Autowired 
	private IUserService isd;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUsersValidateCode() {
		return usersValidateCode;
	}
	public void setUsersValidateCode(String usersValidateCode) {
		this.usersValidateCode = usersValidateCode;
	}
	public IUserService getIsd() {
		return isd;
	}
	public void setIsd(IUserService isd) {
		this.isd = isd;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	//实现验证登录
	public String login(){
		User loginUser=isd.getUserByNameAndPwd(user);//通过用户名和密码取得登录对象，若输入有误为null
		Map<String,String> map=(Map<String,String>)ActionContext.getContext().getSession().get("map");//获取验证码判断结果
		Map<String,Object> msg=new HashMap<String, Object>();//向前台反馈的json类型数据信息
		String result=map.get("result");
		if(result.equals("fail")) {
			msg.put("result", "验证码输入有误，请重新输入！");
		} else if(null==loginUser){
			msg.put("result", "用户名或密码输入有误，请重新输入！");
		}else{
			ActionContext.getContext().getSession().put("loginUser",loginUser);//登陆成功，将登录用户对象放到session中，用于后台系统过滤操作
			msg.put("url", "index.jsp");//登录成功转向的url
		}
		jsonObj=JSONObject.fromObject(msg);
		return SUCCESS;
	}
	//实现注销用户
	public String logout(){
		ActionContext.getContext().getSession().remove("loginUser");//将session中保存的登录用户清除，并跳转到登录界面
		return INPUT;
	}
	/*
	 * 验证用户输入的验证码是否正确
	 */
	public String validateCode(){
		//获取系统会话中目前存在的验证码
		String sessionCode=(String)(ActionContext.getContext().getSession().get("sessionCode"));
		//判断用户输入的验证码是否正确
		Map map = new HashMap();
		if(null != sessionCode && sessionCode.equals(this.getUsersValidateCode())) {
			map.put("result", "correct");			
		} else {
			map.put("result", "fail");	
		}
		ActionContext.getContext().getSession().put("map",map);
		jsonObj=JSONObject.fromObject(map);		
		return SUCCESS;
	}
}

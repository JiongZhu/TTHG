package com.tthg.controller;

import java.io.IOException;
import java.io.PrintWriter;



import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.java.swing.plaf.windows.resources.windows;
import com.tthg.entity.User;
import com.tthg.service.LoginServiceDao;
/**
 * 注册登录action
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
@Controller
public class LoginAction extends ActionSupport{
	private String username;
	private String password;
	private String optionsRadios;
	private String birth;
	private String tel;
	private String cerno;
	private String repass;
	private User u;
	@Autowired
	private LoginServiceDao loginservicedao;

	public String getOptionsRadios() {
		return optionsRadios;
	}

	public void setOptionsRadios(String optionsRadios) {
		this.optionsRadios = optionsRadios;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCerno() {
		return cerno;
	}

	public void setCerno(String cerno) {
		this.cerno = cerno;
	}

	public String getRepass() {
		return repass;
	}

	public void setRepass(String repass) {
		this.repass = repass;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	
	public LoginServiceDao getLoginservicedao() {
		return loginservicedao;
	}

	public void setLoginservicedao(LoginServiceDao loginservicedao) {
		this.loginservicedao = loginservicedao;
	}
	
   public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

public String execute(){
	System.out.println("进入action");
	
	System.out.println("username"+username);
	   
	 if( loginservicedao.isLogin(username,password))
	   
	 return SUCCESS; 
	 else{
         
		 return INPUT;  
	 }
   }
//前台注册方法
 public String add(){
	
	System.out.println("进入action");
	u=new User();
	u.setBirthday(birth);
	u.setUname(username);
	u.setPassward(password);
	u.setCertificateNo(cerno);
	u.setSex(optionsRadios);
	u.setTelephone(tel);
	u.setDeId(1);
	if(password.equals(repass)){
		loginservicedao.adduser(u);
		return SUCCESS;
	}
	else{

		return "error";
		
		
	}
	
}


}

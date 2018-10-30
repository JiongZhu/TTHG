package com.tthg.controller;

import java.io.ByteArrayInputStream;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tthg.util.ValidateCode;
@Controller
public class ValidateCodeAction extends ActionSupport {

	//保存验证码的图像对象
	private ByteArrayInputStream inputStream;  

	public void setInputStream(ByteArrayInputStream inputStream) {      
		this.inputStream = inputStream;      
	}  
	
	public ByteArrayInputStream getInputStream() {      
		return inputStream;      
	}     	
	/*
	 * 获取生成的验证码并将验证码添加到会话中进行判断
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception{ 
		//获得验证码处理对象
		ValidateCode valCode = ValidateCode.Instance();      
		//取得带有随机字符串的图片
		this.setInputStream(valCode.getImage());
		
		//将生成的验证码添加到当前会话中，以便验证
		ActionContext.getContext().getSession().put("sessionCode", valCode.getString());     
		return SUCCESS;      
	}  
}

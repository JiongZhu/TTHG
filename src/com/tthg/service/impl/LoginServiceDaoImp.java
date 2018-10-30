package com.tthg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.LoginDao;
import com.tthg.entity.User;
import com.tthg.service.LoginServiceDao;
/**
 * 注册登录服务层实现类
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
@Service
public class LoginServiceDaoImp implements LoginServiceDao{
	@Autowired
	private LoginDao logindao;
	

	public LoginDao getLogindao() {
		return logindao;
	}


	public void setLogindao(LoginDao logindao) {
		this.logindao = logindao;
	}


	@Override
	public boolean isLogin(String username,String password) {
		System.out.println("进入SERVICE");
		return logindao.isLogin(username,password);
	}


	@Override
	public void adduser(User u) {
		// TODO Auto-generated method stub
		logindao.adduser(u);
		
	}

}

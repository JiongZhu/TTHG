package com.tthg.service;

import com.tthg.entity.User;
/**
 * 注册登录服务层dao
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
public interface LoginServiceDao {
	boolean isLogin(String username,String password);
	void adduser(User u);
	

}

package com.tthg.dao;

import com.tthg.entity.User;
/**
 * 注册登录dao
 * @author 吴玉双  编写者
 * @since 2016-12-18 编写时间
 */
public interface LoginDao {
	boolean isLogin(String username,String password);
	void adduser(User u);

}

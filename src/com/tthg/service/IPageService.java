package com.tthg.service;

import java.util.List;

//分页服务层接口
public interface IPageService {
	//分页查询
	public List getList(String url,String page, String rows);
}

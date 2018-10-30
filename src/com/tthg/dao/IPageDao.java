package com.tthg.dao;

import java.util.List;

//分页接口
public interface IPageDao {
	// 根据第几页获取，每页几行获取数据
	public List getList(String url,String page, String rows);
}

package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IPageDao;
import com.tthg.service.IPageService;
import com.tthg.service.impl.PageServiceImpl;
//分页服务层接口实现
@Service
public class PageServiceImpl implements IPageService {
	@Autowired
	private IPageDao pageDAO;//部门实现接口
	//分页查询
	@Override
	public List getList(String url, String page, String rows) {
		// TODO Auto-generated method stub
		return pageDAO.getList(url, page, rows);
	}
	
}

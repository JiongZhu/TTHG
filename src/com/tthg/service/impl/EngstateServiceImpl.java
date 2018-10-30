package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.service.impl.EngstateServiceImpl;
import com.tthg.dao.IEngstateDao;
import com.tthg.entity.Engstate;
import com.tthg.service.IEngstateService;
@Service
//状态服务层接口实现类
public class EngstateServiceImpl implements IEngstateService {
	@Autowired
	private IEngstateDao engstateDAO;//部门实现接口
	//get和set方法
	public IEngstateDao getEngstateDAO() {
		return engstateDAO;
	}

	public void setEngstateDAO(IEngstateDao engstateDAO) {
		this.engstateDAO = engstateDAO;
	}

	//根据id数组删除
	@Override
	public boolean deleteEngstate(int[] ids) {
		// TODO Auto-generated method stub
		return engstateDAO.deleteEngstate(ids);
	}

	//全查状态
	@Override
	public List<Engstate> getAllEngstate() {
		// TODO Auto-generated method stub
		return engstateDAO.getAllEngstate();
	}
	
	//复合查询
	@Override
	public List getEngstateByComposition(Engstate engstate) {
		// TODO Auto-generated method stub
		return engstateDAO.getEngstateByComposition(engstate);
	}

	//根据id查询状态
	@Override
	public List<Engstate> getEngstateById(Integer id) {
		// TODO Auto-generated method stub
		return engstateDAO.getEngstateById(id);
	}

	//根据no查询状态
	@Override
	public List<Engstate> getEngstateByNo(String no) {
		// TODO Auto-generated method stub
		return engstateDAO.getEngstateByNo(no);
	}

	//插入状态信息
	@Override
	public boolean insertEngstate(Engstate engstate) {
		// TODO Auto-generated method stub
		return engstateDAO.insertEngstate(engstate);
	}

	//修改状态
	@Override
	public boolean updateEngstate(Integer dmid, Engstate engstate) {
		// TODO Auto-generated method stub
		return engstateDAO.updateEngstate(dmid, engstate);
	}
}

package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.service.impl.RestateServiceImpl;
import com.tthg.dao.IRestateDao;
import com.tthg.entity.Restate;
import com.tthg.service.IRestateService;
@Service
//维修状态服务层接口实现类
public class RestateServiceImpl implements IRestateService {
	@Autowired
	private IRestateDao restateDAO;//部门实现接口
	//get和set方法

	public IRestateDao getRestateDAO() {
		return restateDAO;
	}

	public void setRestateDAO(IRestateDao restateDAO) {
		this.restateDAO = restateDAO;
	}
	//删除维修状态信息，因为一对多关系，所以维修状态前应判断部门下
	//是否有其他表，返回布尔
	@Override
	public boolean deleteRestate(int[] ids) {
		// TODO Auto-generated method stub
		return restateDAO.deleteRestate(ids);
	}
	//查询所有维修状态信息
	@Override
	public List<Restate> getAllRestate() {
		// TODO Auto-generated method stub
		return restateDAO.getAllRestate();
	}
	//通过维修状态id查询用户信息
	@Override
	public List<Restate> getRestateById(Integer id) {
		// TODO Auto-generated method stub
		return restateDAO.getRestateById(id);
	}
	//通过维修状态编号查询用户信息，一次来判断维修状态是否存在
	//添加维修状态和删除维修状态是会用到
	@Override
	public List<Restate> getRestateByNo(String no) {
		// TODO Auto-generated method stub
		return restateDAO.getRestateByNo(no);
	}
	//添加维修状态信息页面传递评估对象，进行添加操作并返回布尔类型信息
	@Override
	public boolean insertRestate(Restate restate, Integer troid) {
		// TODO Auto-generated method stub
		return restateDAO.insertRestate(restate, troid);
	}
	//修改维修状态信息页面传递评估对象，进行修改操作并返回布尔类型信息
	@Override
	public boolean updateRestate(Integer troid, Restate restate) {
		// TODO Auto-generated method stub
		return restateDAO.updateRestate(troid, restate);
	}
	//组合查询
	@Override
	public List getRestateByComposition(Restate restate) {
		// TODO Auto-generated method stub
		return restateDAO.getRestateByComposition(restate);
	}
	
}

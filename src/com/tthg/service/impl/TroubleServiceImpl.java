package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.service.impl.TroubleServiceImpl;
import com.tthg.dao.ITroubleDao;
import com.tthg.entity.Trouble;
import com.tthg.service.ITroubleService;
@Service
//故障服务层接口实现类
public class TroubleServiceImpl implements ITroubleService {
	@Autowired
	private ITroubleDao troubleDAO;//部门实现接口
	//get和set方法

	public ITroubleDao getTroubleDAO() {
		return troubleDAO;
	}

	public void setTroubleDAO(ITroubleDao troubleDAO) {
		this.troubleDAO = troubleDAO;
	}
	//删除故障信息，因为一对多关系，所以故障前应判断部门下
	//是否有其他表，返回布尔
	@Override
	public boolean deleteTrouble(int[] ids) {
		// TODO Auto-generated method stub
		return troubleDAO.deleteTrouble(ids);
	}
	//查询所有故障信息
	@Override
	public List<Trouble> getAllTrouble() {
		// TODO Auto-generated method stub
		return troubleDAO.getAllTrouble();
	}
	//通过故障id查询用户信息
	@Override
	public List<Trouble> getTroubleById(Integer id) {
		// TODO Auto-generated method stub
		return troubleDAO.getTroubleById(id);
	}
	//通过故障编号查询用户信息，一次来判断故障是否存在
	//添加故障和删除故障是会用到
	@Override
	public List<Trouble> getTroubleByNo(String no) {
		// TODO Auto-generated method stub
		return troubleDAO.getTroubleByNo(no);
	}
	//添加故障信息页面传递故障对象，进行添加操作并返回布尔类型信息
	@Override
	public boolean insertTrouble(Trouble trouble, Integer reid) {
		// TODO Auto-generated method stub
		return troubleDAO.insertTrouble(trouble, reid);
	}
	//修改故障信息页面传递评估对象，进行修改操作并返回布尔类型信息
	@Override
	public boolean updateTrouble(Integer reid, Trouble trouble) {
		// TODO Auto-generated method stub
		return troubleDAO.updateTrouble(reid, trouble);
	}
	//组合查询
	@Override
	public List getTroubleByComposition(Trouble trouble) {
		// TODO Auto-generated method stub
		return troubleDAO.getTroubleByComposition(trouble);
	}
	
}

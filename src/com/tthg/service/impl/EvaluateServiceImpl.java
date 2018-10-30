package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.service.impl.EvaluateServiceImpl;
import com.tthg.dao.IEvaluateDao;
import com.tthg.entity.Evaluate;
import com.tthg.service.IEvaluateService;
@Service
//维修评估服务层接口实现类
public class EvaluateServiceImpl implements IEvaluateService {
	@Autowired
	private IEvaluateDao evaluateDAO;//部门实现接口
	//get和set方法
	public IEvaluateDao getEvaluateDAO() {
		return evaluateDAO;
	}

	public void setEvaluateDAO(IEvaluateDao evaluateDAO) {
		this.evaluateDAO = evaluateDAO;
	}
	//删除评估信息，因为一对多关系，所以评估前应判断部门下
	//是否有其他表，返回布尔
	@Override
	public boolean deleteEvaluate(int[] ids) {
		// TODO Auto-generated method stub
		return evaluateDAO.deleteEvaluate(ids);
	}
	//查询所有评估信息
	@Override
	public List<Evaluate> getAllEvaluate() {
		// TODO Auto-generated method stub
		return evaluateDAO.getAllEvaluate();
	}
	//通过评估id查询用户信息
	@Override
	public List<Evaluate> getEvaluateById(Integer id) {
		// TODO Auto-generated method stub
		return evaluateDAO.getEvaluateById(id);
	}
	//通过评估编号查询用户信息，一次来判断评估是否存在
	//添加评估和删除评估是会用到
	@Override
	public List<Evaluate> getEvaluateByNo(String no) {
		// TODO Auto-generated method stub
		return evaluateDAO.getEvaluateByNo(no);
	}
	//添加评估信息页面传递评估对象，进行添加操作并返回布尔类型信息
	@Override
	public boolean insertEvaluate(Evaluate evaluate, Integer reid) {
		// TODO Auto-generated method stub
		return evaluateDAO.insertEvaluate(evaluate, reid);
	}
	//修改维修信息页面传递评估对象，进行修改操作并返回布尔类型信息
	@Override
	public boolean updateEvaluate(Integer reid, Evaluate evaluate) {
		// TODO Auto-generated method stub
		return evaluateDAO.updateEvaluate(reid, evaluate);
	}
	//复合查询
	@Override
	public List getEvaluateByComposition(Evaluate evaluate) {
		// TODO Auto-generated method stub
		return evaluateDAO.getEvaluateByComposition(evaluate);
	}

	
}

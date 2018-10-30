package com.tthg.service;

import java.util.List;

import com.tthg.entity.Evaluate;
//评估服务层接口
public interface IEvaluateService {
	//查询所有评估信息
	public List<Evaluate> getAllEvaluate();
	//删除评估信息，因为一对多关系，所以评估前应判断部门下
	//是否有其他表，返回布尔
	public boolean deleteEvaluate(int[] ids);
	//通过评估编号查询用户信息，一次来判断评估是否存在
	//添加评估和删除评估是会用到
	public List<Evaluate> getEvaluateByNo(String no);
	//通过评估id查询用户信息
	public List<Evaluate> getEvaluateById(Integer id);
	//修改维修信息页面传递评估对象，进行修改操作并返回布尔类型信息
	public boolean updateEvaluate(Integer reid,Evaluate evaluate);
	//添加评估信息页面传递评估对象，进行添加操作并返回布尔类型信息
	public boolean insertEvaluate(Evaluate evaluate,Integer reid);
	//复合查询
	public List getEvaluateByComposition(Evaluate evaluate);
}


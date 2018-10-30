package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Trouble;

//故障接口
public interface ITroubleDao {
	//查询所有故障信息
	public List<Trouble> getAllTrouble();
	//删除故障信息，因为一对多关系，所以故障前应判断部门下
	//是否有其他表，返回布尔
	public boolean deleteTrouble(int[] ids);
	//通过故障编号查询用户信息，一次来判断故障是否存在
	//添加故障和删除故障是会用到
	public List<Trouble> getTroubleByNo(String no);
	//通过故障id查询用户信息
	public List<Trouble> getTroubleById(Integer id);
	//修改故障信息页面传递评估对象，进行修改操作并返回布尔类型信息
	public boolean updateTrouble(Integer reid,Trouble trouble);
	//添加故障信息页面传递故障对象，进行添加操作并返回布尔类型信息
	public boolean insertTrouble(Trouble trouble,Integer reid);
	//组合查询
	public List getTroubleByComposition(Trouble trouble);
}

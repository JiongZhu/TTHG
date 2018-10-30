package com.tthg.service;

import java.util.List;

import com.tthg.entity.Restate;
//维修状态服务层接口
public interface IRestateService {
	//查询所有维修状态信息
	public List<Restate> getAllRestate();
	//删除维修状态信息，因为一对多关系，所以维修状态前应判断部门下
	//是否有其他表，返回布尔
	public boolean deleteRestate(int[] ids);
	//通过维修状态编号查询用户信息，一次来判断维修状态是否存在
	//添加维修状态和删除维修状态是会用到
	public List<Restate> getRestateByNo(String no);
	//通过维修状态id查询用户信息
	public List<Restate> getRestateById(Integer id);
	//修改维修状态信息页面传递评估对象，进行修改操作并返回布尔类型信息
	public boolean updateRestate(Integer troid,Restate restate);
	//添加维修状态信息页面传递评估对象，进行添加操作并返回布尔类型信息
	public boolean insertRestate(Restate restate,Integer troid);
	//组合查询
	public List getRestateByComposition(Restate restate);
}

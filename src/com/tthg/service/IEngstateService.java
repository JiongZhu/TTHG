package com.tthg.service;

import java.util.List;

import com.tthg.entity.Engstate;
//状态服务层接口
public interface IEngstateService {
	//查询出所有状态
	public List<Engstate> getAllEngstate();
	//通过用户id删除相对应得用户信息，并返回布尔类型信息
	public boolean deleteEngstate(int[] ids);
	//通过状态编号查询用户信息，一次来判断改状态是否存在
	//添加状态和删除用户是会用到
	public List<Engstate> getEngstateByNo(String no);
	//通过用户id查询用户信息
	public List<Engstate> getEngstateById(Integer id);
	//修改状态信息，因为一对多关系，所以传递参数应包含
	//需要修改的状态id，以此来修改部门与员工的关系
	public boolean updateEngstate(Integer dmid,Engstate engstate);
	//添加状态，因为一对多关系，所以传递参数不仅包含用户对象
	//还应包含需添加的状态id
	public boolean insertEngstate(Engstate engstate);
	//复合查询
	public List getEngstateByComposition(Engstate engstate);
}


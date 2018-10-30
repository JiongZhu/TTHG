package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Repair;

//维修登记接口
public interface IRepairDao {
	//查询所有维修信息
	public List<Repair> getAllRepair();
	//删除维修信息，因为一对多关系，所以维修前应判断部门下
	//是否有其他表，返回布尔
	public boolean deleteRepair(int[] ids);
	//通过维修编号查询用户信息，一次来判断改部门是否存在
	//添加维修和删除维修是会用到
	public List<Repair> getRepairByNo(String no);
	//通过维修id查询用户信息
	public List<Repair> getRepairById(Integer id);
	//修改维修信息页面传递维修对象，进行修改操作并返回布尔类型信息
	public boolean updateRepair(Integer id,Repair repair);
	//添加维修信息页面传递维修对象，进行添加操作并返回布尔类型信息
	public boolean insertRepair(Repair repair);
	//组合查询
	public List getRepairByComposition(Repair repair);
	//车牌号查询
	public boolean getRepairByPlateNo(String plateNo);
}

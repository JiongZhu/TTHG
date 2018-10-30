package com.tthg.service;

import java.util.List;

import com.tthg.entity.Department;
//部门服务层接口
public interface IDepartService {
	//经理查询所有部门信息
	public List<Department> getAllDepart();
	//删除部门信息，因为一对多关系，所以删除前应判断部门下
	//是否有员工，返回布尔
	public boolean deleteDepart(int[] ids);
	//通过部门编号查询用户信息，一次来判断改部门是否存在
	//添加部门和删除部门是会用到
	public List<Department> getDepartByNo(String no);
	//通过部门id查询用户信息
	public List<Department> getDepartById(Integer id);
	//修改部门信息页面传递部门对象，进行修改操作并返回布尔类型信息
	public boolean updateDepart(Integer id,Department department);
	//添加部门信息页面传递部门对象，进行添加操作并返回布尔类型信息
	public boolean insertDepart(Department department);
}

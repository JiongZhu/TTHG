package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Department;
/**
 * 部门接口
 * @author葛康  编写人
 * @since 2016-12-10  编写日期
 * @method
 * 1:插入一个系统对象 public boolean insertComponent(Component component,Integer troid);
 * 2:修改一个系统对象 public boolean updateComponent(Integer troid,Component component);
 * 3:删除一个系统对象 public boolean deleteComponent(int[] ids);
 * 4:编号查询一个系统对象 public List<Component> getComponentByNo(String no);
 * 5:编号id一个系统对象 public List<Component> getComponentById(Integer id);
 * 6:复合查询 public List getComponentByComposition(Component component);
 * 7:全查系统对象 public List<Component> getAllComponent();
 */
public interface IDepartDao {
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

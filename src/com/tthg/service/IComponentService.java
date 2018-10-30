package com.tthg.service;

import java.util.List;

import com.tthg.entity.Component;
//零件服务层接口
public interface IComponentService {
	//查询所有零件信息
	public List<Component> getAllComponent();
	//删除零件信息，因为一对多关系，所以零件前应判断部门下
	//是否有其他表，返回布尔
	public boolean deleteComponent(int[] ids);
	//通过零件编号查询用户信息，一次来判断零件是否存在
	//添加零件和删除零件是会用到
	public List<Component> getComponentByNo(String no);
	//通过零件id查询用户信息
	public List<Component> getComponentById(Integer id);
	//修改零件信息页面传递零件对象，进行修改操作并返回布尔类型信息
	public boolean updateComponent(Integer troid,Component component);
	//添加零件信息页面传递零件对象，进行添加操作并返回布尔类型信息
	public boolean insertComponent(Component component,Integer troid);
	//复合查询
	public List getComponentByComposition(Component component);
}

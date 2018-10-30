package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.service.impl.ComponentServiceImpl;
import com.tthg.dao.IComponentDao;
import com.tthg.entity.Component;
import com.tthg.service.IComponentService;
//维修服务层接口实现类
@Service
public class ComponentServiceImpl implements IComponentService {
	@Autowired
	private IComponentDao componentDAO;//部门实现接口
	//get和set方法

	public IComponentDao getComponentDAO() {
		return componentDAO;
	}

	public void setComponentDAO(IComponentDao componentDAO) {
		this.componentDAO = componentDAO;
	}
	//删除零件信息，因为一对多关系，所以零件前应判断部门下
	//是否有其他表，返回布尔
	@Override
	public boolean deleteComponent(int[] ids) {
		// TODO Auto-generated method stub
		return componentDAO.deleteComponent(ids);
	}
	//查询所有零件信息
	@Override
	public List<Component> getAllComponent() {
		// TODO Auto-generated method stub
		return componentDAO.getAllComponent();
	}
	//通过零件id查询用户信息
	@Override
	public List<Component> getComponentById(Integer id) {
		// TODO Auto-generated method stub
		return componentDAO.getComponentById(id);
	}
	//通过零件编号查询用户信息，一次来判断零件是否存在
	//添加零件和删除零件是会用到
	@Override
	public List<Component> getComponentByNo(String no) {
		// TODO Auto-generated method stub
		return componentDAO.getComponentByNo(no);
	}
	//添加零件信息页面传递零件对象，进行添加操作并返回布尔类型信息
	@Override
	public boolean insertComponent(Component component, Integer troid) {
		// TODO Auto-generated method stub
		return componentDAO.insertComponent(component, troid);
	}
	//修改零件信息页面传递零件对象，进行修改操作并返回布尔类型信息
	@Override
	public boolean updateComponent(Integer troid, Component component) {
		// TODO Auto-generated method stub
		return componentDAO.updateComponent(troid, component);
	}
	//复合查询
	@Override
	public List getComponentByComposition(Component component) {
		// TODO Auto-generated method stub
		return componentDAO.getComponentByComposition(component);
	}
	
}

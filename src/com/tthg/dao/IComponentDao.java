package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Component;

/**
 * 零件调用接口
 * @author葛康  编写人
 * @since 2016-12-27  编写日期
 * @method
 * 1:插入一个系统对象 public boolean insertComponent(Component component,Integer troid);
 * 2:修改一个系统对象 public boolean updateComponent(Integer troid,Component component);
 * 3:删除一个系统对象 public boolean deleteComponent(int[] ids);
 * 4:编号查询一个系统对象 public List<Component> getComponentByNo(String no);
 * 5:编号id一个系统对象 public List<Component> getComponentById(Integer id);
 * 6:复合查询 public List getComponentByComposition(Component component);
 * 7:全查系统对象 public List<Component> getAllComponent();
 */

public interface IComponentDao {
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

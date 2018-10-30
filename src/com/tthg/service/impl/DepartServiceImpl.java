package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.service.impl.DepartServiceImpl;
import com.tthg.dao.IDepartDao;
import com.tthg.entity.Department;
import com.tthg.service.IDepartService;
@Service
//部门服务层接口实现类
public class DepartServiceImpl implements IDepartService {
	@Autowired
	private IDepartDao departDAO;//部门实现接口
	//get和set方法
	public IDepartDao getDepartDAO() {
		return departDAO;
	}

	public void setDepartDAO(IDepartDao departDAO) {
		this.departDAO = departDAO;
	}

	@Override
	//删除部门信息，因为一对多关系，所以删除前应判断部门下
	//是否有员工，返回布尔
	public boolean deleteDepart(int[] ids) {
		// TODO Auto-generated method stub
		return departDAO.deleteDepart(ids);
	}

	@Override
	//经理查询所有部门信息
	public List<Department> getAllDepart() {
		// TODO Auto-generated method stub
		return departDAO.getAllDepart();
	}

	@Override
	//通过部门id查询用户信息
	public List<Department> getDepartById(Integer id) {
		// TODO Auto-generated method stub
		return departDAO.getDepartById(id);
	}

	@Override
	//添加部门信息页面传递部门对象，进行添加操作并返回布尔类型信息
	public boolean insertDepart(Department department) {
		// TODO Auto-generated method stub
		return departDAO.insertDepart(department);
	}

	@Override
	//修改部门信息页面传递部门对象，进行修改操作并返回布尔类型信息
	public boolean updateDepart(Integer id, Department department) {
		// TODO Auto-generated method stub
		return departDAO.updateDepart(id, department);
	}

	@Override
	//通过部门编号查询用户信息，一次来判断改部门是否存在
	//添加部门和删除部门是会用到
	public List<Department> getDepartByNo(String no) {
		// TODO Auto-generated method stub
		return departDAO.getDepartByNo(no);
	}
	
}

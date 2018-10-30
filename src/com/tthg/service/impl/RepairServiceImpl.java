package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IRepairDao;
import com.tthg.entity.Repair;
import com.tthg.service.IRepairService;
@Service
//维修服务层接口实现类
public class RepairServiceImpl implements IRepairService {
	@Autowired
	private IRepairDao repairDao;//部门实现接口
	//get和set方法

	public IRepairDao getRepairDao() {
		return repairDao;
	}

	public void setRepairDao(IRepairDao repairDao) {
		this.repairDao = repairDao;
	}
	//删除维修信息，因为一对多关系，所以维修前应判断部门下
	//是否有其他表，返回布尔
	@Override
	public boolean deleteRepair(int[] ids) {
		// TODO Auto-generated method stub
		return repairDao.deleteRepair(ids);
	}
	//查询所有维修信息
	@Override
	public List<Repair> getAllRepair() {
		// TODO Auto-generated method stub
		return repairDao.getAllRepair();
	}
	//通过维修id查询用户信息
	@Override
	public List<Repair> getRepairById(Integer id) {
		// TODO Auto-generated method stub
		return repairDao.getRepairById(id);
	}
	//通过维修编号查询用户信息，一次来判断改部门是否存在
	//添加维修和删除维修是会用到
	@Override
	public List<Repair> getRepairByNo(String no) {
		// TODO Auto-generated method stub
		return repairDao.getRepairByNo(no);
	}
	//添加维修信息页面传递维修对象，进行添加操作并返回布尔类型信息
	@Override
	public boolean insertRepair(Repair repair) {
		// TODO Auto-generated method stub
		return repairDao.insertRepair(repair);
	}
	//修改维修信息页面传递维修对象，进行修改操作并返回布尔类型信息
	@Override
	public boolean updateRepair(Integer id, Repair repair) {
		// TODO Auto-generated method stub
		return repairDao.updateRepair(id, repair);
	}
	//组合查询
	@Override
	public List getRepairByComposition(Repair repair) {
		// TODO Auto-generated method stub
		return repairDao.getRepairByComposition(repair);
	}
	//车牌号查询
	@Override
	public boolean getRepairByPlateNo(String plateNo) {
		// TODO Auto-generated method stub
		return repairDao.getRepairByPlateNo(plateNo);
	}

}

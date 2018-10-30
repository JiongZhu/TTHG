package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IRepairnummangerDAO;
import com.tthg.entity.Repairnum;
import com.tthg.service.IRepairnummangerServiceDAO;
@Service
public class RepairnummangerServiceDAO implements IRepairnummangerServiceDAO{
  @Autowired
   private IRepairnummangerDAO  repairmanDAO;
  
	public IRepairnummangerDAO getRepairmanDAO() {
	return repairmanDAO;
}

  public void setRepairmanDAO(IRepairnummangerDAO repairmanDAO) {
	this.repairmanDAO = repairmanDAO;
}

	@Override
	public void add(Repairnum repainum) {
		// TODO Auto-generated method stub
		repairmanDAO.add(repainum);
	}

	@Override
	public void delete(int[] ids) {
		// TODO Auto-generated method stub
		repairmanDAO.delete(ids);
	}

	@Override
	public List<Repairnum> searchAll() {
		// TODO Auto-generated method stub
		return repairmanDAO.searchAll();
	}

	@Override
	public void update(Repairnum repairnum) {
		// TODO Auto-generated method stub
		repairmanDAO.update(repairnum);
	}

	@Override
	public List<Repairnum> searchRepairnum(Repairnum repairnum) {
		// TODO Auto-generated method stub
		return repairmanDAO.searchRepairnum(repairnum);
	}

}

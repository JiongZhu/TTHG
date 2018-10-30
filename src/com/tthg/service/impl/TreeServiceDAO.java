package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.ITreeDAO;
import com.tthg.entity.Tree;
import com.tthg.service.ITreeServiceDAO;
@Service
public class TreeServiceDAO implements ITreeServiceDAO {

	@Autowired
	private ITreeDAO itd;
	
	public ITreeDAO getItd() {
		return itd;
	}

	public void setItd(ITreeDAO itd) {
		this.itd = itd;
	}

	@Override
	public List<Tree> getAllTree() {
		return itd.getAllTree();
	}

	@Override
	public List<Tree> getById(int[] ids) {
		return itd.getById(ids);
	}

}

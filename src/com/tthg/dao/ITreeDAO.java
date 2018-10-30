package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Tree;

public interface ITreeDAO {
	List<Tree> getAllTree();
	List<Tree> getById(int[] ids);
}

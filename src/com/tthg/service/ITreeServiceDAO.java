package com.tthg.service;

import java.util.List;

import com.tthg.entity.Tree;

public interface ITreeServiceDAO {
	List<Tree> getAllTree();
	List<Tree> getById(int[] ids);
}

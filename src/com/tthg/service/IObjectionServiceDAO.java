package com.tthg.service;

import java.util.List;

import com.tthg.entity.Objection;

public interface IObjectionServiceDAO {
	void add(Objection objection);
	void delete(int[] ids);
	void update(Objection objection);
	List<Objection> searchAll();
	List<Objection> searchObjection(Objection objection);
}

package com.tthg.entity;

import java.util.List;

public class Series {
	
	private String name;
	private String type;
	public List<Integer> data;
	
	public Series(String name, String type, List<Integer> data) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
}

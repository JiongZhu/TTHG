package com.tthg.entity;

/**
 * Tree entity. @author MyEclipse Persistence Tools
 */

public class Tree implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pid;
	private String name;
	private Boolean open;
	private String file;

	// Constructors

	/** default constructor */
	public Tree() {
	}

	/** full constructor */
	public Tree(Integer pid, String name, Boolean open, String file) {
		this.pid = pid;
		this.name = name;
		this.open = open;
		this.file = file;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return this.open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
package com.tthg.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */
/**
 * 部门实体
 * @author 葛康  编写者
 * @since 2016-12-11 编写时间
 *
 */
public class Department implements java.io.Serializable {

	// Fields

	private Integer id;//id
	private String deNo;//部门编号
	private String deName;//部门名
	
	private Set users=new HashSet();//外键用户

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** full constructor */
	public Department(String deNo, String deName) {
		this.deNo = deNo;
		this.deName = deName;
	}

	// Property accessors

	//set和get
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeNo() {
		return this.deNo;
	}

	public void setDeNo(String deNo) {
		this.deNo = deNo;
	}

	public String getDeName() {
		return this.deName;
	}

	public void setDeName(String deName) {
		this.deName = deName;
	}

	public Set getUsers() {
		return users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}
	
}
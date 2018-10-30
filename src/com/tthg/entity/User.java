package com.tthg.entity;

import java.util.Date;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
/**
 * 用户表的javaBean，一个对象对应一个部门javabean说明
 * @author 葛康  编写者
 * @since 2016-12-15 编写时间
 *
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;//id
	private String uno;//用户编号
	private String uname;//用户名
	private String passward;//密码
	private String sex;//性别
	private String certificateNo;//身份证号
	private String birthday;//出生年月日
	private String telephone;//联系电话
	private String ustate;//工作状态
	private Integer power;//职位（权限）
	private Integer deId;//外键（部门id）
	
	private Integer extreId;//额外补充deid

	private Department departs;//部门

	// Constructors

	/** default constructor */
	public User() {
		
	}

	/** minimal constructor */
	public User(Integer deId) {
		this.deId = deId;
	}

	/** full constructor */
	public User(String uno, String uname, String passward, String sex,
			String certificateNo, String birthday, String telephone,
			String ustate, Integer power, Integer deId) {
		this.uno = uno;
		this.uname = uname;
		this.passward = passward;
		this.sex = sex;
		this.certificateNo = certificateNo;
		this.birthday = birthday;
		this.telephone = telephone;
		this.ustate = ustate;
		this.power = power;
		this.deId = deId;
	}

	// Property accessors
	//get和set
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUno() {
		return this.uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassward() {
		return this.passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCertificateNo() {
		return this.certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUstate() {
		return this.ustate;
	}

	public void setUstate(String ustate) {
		this.ustate = ustate;
	}

	public Integer getPower() {
		return this.power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getDeId() {
		return this.deId;
	}

	public void setDeId(Integer deId) {
		this.deId = deId;
	}

	public Department getDeparts() {
		return departs;
	}

	public void setDeparts(Department departs) {
		this.departs = departs;
	}

	public Integer getExtreId() {
		return extreId;
	}

	public void setExtreId(Integer extreId) {
		this.extreId = extreId;
	}
}
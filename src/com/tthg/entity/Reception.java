package com.tthg.entity;

import java.util.Date;

/**
 * Reception entity. @author MyEclipse Persistence Tools
 */

public class Reception implements java.io.Serializable {

	// Fields

	private Integer id;
	private String receptionNo;
	private String customerName;
	private Integer age;
	private String sex;
	private String telephone;
	private String address;
	private Integer carStyleId;
	private Integer rank;
	private String negotiation;
	private Integer rankAftern;
	private String closedCondition;
	private String receiver;
	private String remarks;
	private Date receptionTime;
	private String state;

	//一对一单项外键关联	 有外键一方
	private Vehicle vehicle;
	//处理关联关系，解决前台数据显示为0问题
	private String cname;
	// Constructors

	/** default constructor */
	public Reception() {
	}

	/** minimal constructor */
	public Reception(Integer carStyleId) {
		this.carStyleId = carStyleId;
	}

	/** full constructor */
	public Reception(String receptionNo, String customerName, Integer age,
			String sex, String telephone, String address, Integer carStyleId,
			Integer rank, String negotiation, Integer rankAftern,
			String closedCondition, String receiver, String remarks,
			Date receptionTime, String state) {
		this.receptionNo = receptionNo;
		this.customerName = customerName;
		this.age = age;
		this.sex = sex;
		this.telephone = telephone;
		this.address = address;
		this.carStyleId = carStyleId;
		this.rank = rank;
		this.negotiation = negotiation;
		this.rankAftern = rankAftern;
		this.closedCondition = closedCondition;
		this.receiver = receiver;
		this.remarks = remarks;
		this.receptionTime = receptionTime;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReceptionNo() {
		return this.receptionNo;
	}

	public void setReceptionNo(String receptionNo) {
		this.receptionNo = receptionNo;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCarStyleId() {
		return this.carStyleId;
	}

	public void setCarStyleId(Integer carStyleId) {
		this.carStyleId = carStyleId;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getNegotiation() {
		return this.negotiation;
	}

	public void setNegotiation(String negotiation) {
		this.negotiation = negotiation;
	}

	public Integer getRankAftern() {
		return this.rankAftern;
	}

	public void setRankAftern(Integer rankAftern) {
		this.rankAftern = rankAftern;
	}

	public String getClosedCondition() {
		return this.closedCondition;
	}

	public void setClosedCondition(String closedCondition) {
		this.closedCondition = closedCondition;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getReceptionTime() {
		return this.receptionTime;
	}

	public void setReceptionTime(Date receptionTime) {
		this.receptionTime = receptionTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
}
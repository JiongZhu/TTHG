package com.tthg.entity;

import java.util.Date;

/**
 * Subscription entity. @author MyEclipse Persistence Tools
 */

public class Subscription implements java.io.Serializable {

	// Fields

	private Integer id;
	private String subscriptionNo;
	private String customerName;
	private String sex;
	private Integer age;
	private String cardId;
	private String address;
	private String telephone;
	private String pc;
	private String email;
	private Integer carStyleId;//外键
	private Date subTime;
	
	private Vehicle vehicle;
	private Order order;
	// Constructors
	private String cname;
	
	/** default constructor */
	public Subscription() {
	}

	/** minimal constructor */
	public Subscription(Integer carStyleId) {
		this.carStyleId = carStyleId;
	}

	/** full constructor */
	public Subscription(String subscriptionNo, String customerName, String sex,
			Integer age, String cardId, String address, String telephone,
			String pc, String email, Integer carStyleId, Date subTime) {
		this.subscriptionNo = subscriptionNo;
		this.customerName = customerName;
		this.sex = sex;
		this.age = age;
		this.cardId = cardId;
		this.address = address;
		this.telephone = telephone;
		this.pc = pc;
		this.email = email;
		this.carStyleId = carStyleId;
		this.subTime = subTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubscriptionNo() {
		return this.subscriptionNo;
	}

	public void setSubscriptionNo(String subscriptionNo) {
		this.subscriptionNo = subscriptionNo;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPc() {
		return this.pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCarStyleId() {
		return this.carStyleId;
	}

	public void setCarStyleId(Integer carStyleId) {
		this.carStyleId = carStyleId;
	}

	public Date getSubTime() {
		return this.subTime;
	}

	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
}
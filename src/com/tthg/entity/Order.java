package com.tthg.entity;

import java.util.Date;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderNo;
	private String customerName;
	private String cardId;
	private String telephone;
	private Integer carId;
	private Integer subscriptionId;
	private Date orTime;
	private String state;

	private Subscription subscription;
	private Invoice invoice;
	private Contract contract;
	private Callback callback;
	// Constructors
	private Integer sid;

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/** full constructor */
	public Order(String orderNo, String customerName, String cardId,
			String telephone, Integer carId, Integer subscriptionId,
			Date orTime, String state) {
		this.orderNo = orderNo;
		this.customerName = customerName;
		this.cardId = cardId;
		this.telephone = telephone;
		this.carId = carId;
		this.subscriptionId = subscriptionId;
		this.orTime = orTime;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getSubscriptionId() {
		return this.subscriptionId;
	}

	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Date getOrTime() {
		return this.orTime;
	}

	public void setOrTime(Date orTime) {
		this.orTime = orTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Callback getCallback() {
		return callback;
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
}
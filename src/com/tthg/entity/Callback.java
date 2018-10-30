package com.tthg.entity;

import java.util.Date;

/**
 * Callback entity. @author MyEclipse Persistence Tools
 */

public class Callback implements java.io.Serializable {

	// Fields

	private Integer id;
	private String callbackNo;
	private String customerName;
	private String telephone;
	private Integer orderId;
	private Date callbackTime;
	private String evaluation;
	private String feedback;

	private Order order;
	// Constructors
	private Integer oid;

	/** default constructor */
	public Callback() {
	}

	/** minimal constructor */
	public Callback(Integer orderId) {
		this.orderId = orderId;
	}

	/** full constructor */
	public Callback(String callbackNo, String customerName, String telephone,
			Integer orderId, Date callbackTime, String evaluation,
			String feedback) {
		this.callbackNo = callbackNo;
		this.customerName = customerName;
		this.telephone = telephone;
		this.orderId = orderId;
		this.callbackTime = callbackTime;
		this.evaluation = evaluation;
		this.feedback = feedback;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCallbackNo() {
		return this.callbackNo;
	}

	public void setCallbackNo(String callbackNo) {
		this.callbackNo = callbackNo;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getCallbackTime() {
		return this.callbackTime;
	}

	public void setCallbackTime(Date callbackTime) {
		this.callbackTime = callbackTime;
	}

	public String getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}
}
package com.tthg.entity;

import java.util.Date;

/**
 * Invoice entity. @author MyEclipse Persistence Tools
 */

public class Invoice implements java.io.Serializable {

	// Fields

	private Integer id;
	private String invoiceNo;
	private Integer orderId;
	private Date invTime;

	private Order order;
	private Integer oid;
	// Constructors

	/** default constructor */
	public Invoice() {
	}

	/** minimal constructor */
	public Invoice(Integer orderId) {
		this.orderId = orderId;
	}

	/** full constructor */
	public Invoice(String invoiceNo, Integer orderId, Date invTime) {
		this.invoiceNo = invoiceNo;
		this.orderId = orderId;
		this.invTime = invTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getInvTime() {
		return this.invTime;
	}

	public void setInvTime(Date invTime) {
		this.invTime = invTime;
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
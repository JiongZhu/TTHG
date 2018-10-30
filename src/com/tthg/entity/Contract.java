package com.tthg.entity;

import java.util.Date;

/**
 * Contract entity. @author MyEclipse Persistence Tools
 */

public class Contract implements java.io.Serializable {

	// Fields

	private Integer id;
	private String contractNo;
	private Date contractTime;
	private Integer orderId;

	private Order order;
	private Integer oid;
	// Constructors

	/** default constructor */
	public Contract() {
	}

	/** minimal constructor */
	public Contract(Integer orderId) {
		this.orderId = orderId;
	}

	/** full constructor */
	public Contract(String contractNo, Date contractTime, Integer orderId) {
		this.contractNo = contractNo;
		this.contractTime = contractTime;
		this.orderId = orderId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getContractTime() {
		return this.contractTime;
	}

	public void setContractTime(Date contractTime) {
		this.contractTime = contractTime;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
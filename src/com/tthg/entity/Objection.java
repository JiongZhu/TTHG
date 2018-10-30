package com.tthg.entity;

import java.util.Date;

/**
 * Objection entity. @author MyEclipse Persistence Tools
 */

public class Objection implements java.io.Serializable {

	// Fields

	private Integer id;
	private String objectionNo;
	private String customerName;
	private String telephone;
	private Integer carId;
	private String problems;
	private String processing;
	private Date obTime;

	// Constructors

	/** default constructor */
	public Objection() {
	}

	/** minimal constructor */
	public Objection(Integer carId) {
		this.carId = carId;
	}

	/** full constructor */
	public Objection(String objectionNo, String customerName, String telephone,
			Integer carId, String problems, String processing, Date obTime) {
		this.objectionNo = objectionNo;
		this.customerName = customerName;
		this.telephone = telephone;
		this.carId = carId;
		this.problems = problems;
		this.processing = processing;
		this.obTime = obTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObjectionNo() {
		return this.objectionNo;
	}

	public void setObjectionNo(String objectionNo) {
		this.objectionNo = objectionNo;
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

	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getProblems() {
		return this.problems;
	}

	public void setProblems(String problems) {
		this.problems = problems;
	}

	public String getProcessing() {
		return this.processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public Date getObTime() {
		return this.obTime;
	}

	public void setObTime(Date obTime) {
		this.obTime = obTime;
	}

}
package com.tthg.entity;

/**
 * AccessoriesBrand entity. @author MyEclipse Persistence Tools
 */

public class AccessoriesBrand implements java.io.Serializable {

	// Fields

	private Integer id;
	private String brandNo;
	private String brandName;

	// Constructors

	/** default constructor */
	public AccessoriesBrand() {
	}

	/** full constructor */
	public AccessoriesBrand(String brandNo, String brandName) {
		this.brandNo = brandNo;
		this.brandName = brandName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrandNo() {
		return this.brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}
package com.tthg.entity;

/**
 * VehicleBrand entity. @author MyEclipse Persistence Tools
 */

public class VehicleBrand implements java.io.Serializable {

	// Fields

	private Integer id;
	private String BrandNo;
	private String BrandName;
	private String Sign;

	// Constructors

	/** default constructor */
	public VehicleBrand() {
	}

	/** full constructor */
	public VehicleBrand(String BrandNo, String BrandName, String Sign) {
		this.BrandNo = BrandNo;
		this.BrandName = BrandName;
		this.Sign = Sign;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrandNo() {
		return this.BrandNo;
	}

	public void setBrandNo(String BrandNo) {
		this.BrandNo = BrandNo;
	}

	public String getBrandName() {
		return this.BrandName;
	}

	public void setBrandName(String BrandName) {
		this.BrandName = BrandName;
	}

	public String getSign() {
		return this.Sign;
	}

	public void setSign(String Sign) {
		this.Sign = Sign;
	}

}
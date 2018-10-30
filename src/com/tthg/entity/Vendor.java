package com.tthg.entity;

/**
 * Vendor entity. @author MyEclipse Persistence Tools
 */

public class Vendor implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vendorNo;
	private String vendorName;
	private String address;
	private String telephone;
	private String reputation;
	private String websites;
	private String headerName;
	private String headerSex;

	// Constructors

	/** default constructor */
	public Vendor() {
	}

	/** full constructor */
	public Vendor(String vendorNo, String vendorName, String address,
			String telephone, String reputation, String websites,
			String headerName, String headerSex) {
		this.vendorNo = vendorNo;
		this.vendorName = vendorName;
		this.address = address;
		this.telephone = telephone;
		this.reputation = reputation;
		this.websites = websites;
		this.headerName = headerName;
		this.headerSex = headerSex;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVendorNo() {
		return this.vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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

	public String getReputation() {
		return this.reputation;
	}

	public void setReputation(String reputation) {
		this.reputation = reputation;
	}

	public String getWebsites() {
		return this.websites;
	}

	public void setWebsites(String websites) {
		this.websites = websites;
	}

	public String getHeaderName() {
		return this.headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderSex() {
		return this.headerSex;
	}

	public void setHeaderSex(String headerSex) {
		this.headerSex = headerSex;
	}

}
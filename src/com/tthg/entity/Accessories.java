package com.tthg.entity;

import java.util.Date;

/**
 * Accessories entity. @author MyEclipse Persistence Tools
 */

public class Accessories implements java.io.Serializable {

	// Fields

	private Integer id;
	private String acceNo;
	private String acceName;
	private String accessoriesType;
	private String carArea;
	private String carStyle;
	private Date producTime;
	private Double price;
	private String photo;

	// Constructors

	/** default constructor */
	public Accessories() {
	}

	/** full constructor */
	public Accessories(String acceNo, String acceName, String accessoriesType,
			String carArea, String carStyle, Date producTime, Double price,
			String photo) {
		this.acceNo = acceNo;
		this.acceName = acceName;
		this.accessoriesType = accessoriesType;
		this.carArea = carArea;
		this.carStyle = carStyle;
		this.producTime = producTime;
		this.price = price;
		this.photo = photo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAcceNo() {
		return this.acceNo;
	}

	public void setAcceNo(String acceNo) {
		this.acceNo = acceNo;
	}

	public String getAcceName() {
		return this.acceName;
	}

	public void setAcceName(String acceName) {
		this.acceName = acceName;
	}

	public String getAccessoriesType() {
		return this.accessoriesType;
	}

	public void setAccessoriesType(String accessoriesType) {
		this.accessoriesType = accessoriesType;
	}

	public String getCarArea() {
		return this.carArea;
	}

	public void setCarArea(String carArea) {
		this.carArea = carArea;
	}

	public String getCarStyle() {
		return this.carStyle;
	}

	public void setCarStyle(String carStyle) {
		this.carStyle = carStyle;
	}

	public Date getProducTime() {
		return this.producTime;
	}

	public void setProducTime(Date producTime) {
		this.producTime = producTime;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
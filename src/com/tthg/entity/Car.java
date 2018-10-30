package com.tthg.entity;

/**
 * Car entity. @author MyEclipse Persistence Tools
 */

public class Car implements java.io.Serializable {

	// Fields

	private Integer id;
	private String carNo;
	private Integer vehicleId;

	// Constructors

	/** default constructor */
	public Car() {
	}

	/** full constructor */
	public Car(String carNo, Integer vehicleId) {
		this.carNo = carNo;
		this.vehicleId = vehicleId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Integer getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

}
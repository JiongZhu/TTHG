package com.tthg.entity;

/**
 * Vehiclemanger entity. @author MyEclipse Persistence Tools
 */

public class Vehiclemanger implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vehicleNo;
	private String fletter;
	private String brand;
	private String series;
	private String vehicleName;
	private Double guidePrice;
	private String mj;
	private String manufacturea;
	private String rank;
	private String engine;
	private String gearbox;
	private String lengthWh;
	private Double maxSpeed;
	private String fuelConsumption;
	private String warranty;
	private String color;
	private Double displacement;
	private Integer numberofCylinders;
	private String photo;

	// Constructors

	/** default constructor */
	public Vehiclemanger() {
	}

	/** full constructor */
	public Vehiclemanger(String vehicleNo, String fletter, String brand,
			String series, String vehicleName, Double guidePrice, String mj,
			String manufacturea, String rank, String engine, String gearbox,
			String lengthWh, Double maxSpeed, String fuelConsumption,
			String warranty, String color, Double displacement,
			Integer numberofCylinders, String photo) {
		this.vehicleNo = vehicleNo;
		this.fletter = fletter;
		this.brand = brand;
		this.series = series;
		this.vehicleName = vehicleName;
		this.guidePrice = guidePrice;
		this.mj = mj;
		this.manufacturea = manufacturea;
		this.rank = rank;
		this.engine = engine;
		this.gearbox = gearbox;
		this.lengthWh = lengthWh;
		this.maxSpeed = maxSpeed;
		this.fuelConsumption = fuelConsumption;
		this.warranty = warranty;
		this.color = color;
		this.displacement = displacement;
		this.numberofCylinders = numberofCylinders;
		this.photo = photo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVehicleNo() {
		return this.vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getFletter() {
		return this.fletter;
	}

	public void setFletter(String fletter) {
		this.fletter = fletter;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getVehicleName() {
		return this.vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public Double getGuidePrice() {
		return this.guidePrice;
	}

	public void setGuidePrice(Double guidePrice) {
		this.guidePrice = guidePrice;
	}

	public String getMj() {
		return this.mj;
	}

	public void setMj(String mj) {
		this.mj = mj;
	}

	public String getManufacturea() {
		return this.manufacturea;
	}

	public void setManufacturea(String manufacturea) {
		this.manufacturea = manufacturea;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getEngine() {
		return this.engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getGearbox() {
		return this.gearbox;
	}

	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	public String getLengthWh() {
		return this.lengthWh;
	}

	public void setLengthWh(String lengthWh) {
		this.lengthWh = lengthWh;
	}

	public Double getMaxSpeed() {
		return this.maxSpeed;
	}

	public void setMaxSpeed(Double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getFuelConsumption() {
		return this.fuelConsumption;
	}

	public void setFuelConsumption(String fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	public String getWarranty() {
		return this.warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getDisplacement() {
		return this.displacement;
	}

	public void setDisplacement(Double displacement) {
		this.displacement = displacement;
	}

	public Integer getNumberofCylinders() {
		return this.numberofCylinders;
	}

	public void setNumberofCylinders(Integer numberofCylinders) {
		this.numberofCylinders = numberofCylinders;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
package com.tthg.entity;

import java.util.Set;

/**
 * Vehicle entity. @author MyEclipse Persistence Tools
 */

public class Vehicle implements java.io.Serializable {

	// Fields

	private Integer id;
	private String VehicleNo;
	private String Fletter;
	private String Brand;
	private String Series;
	private String VehicleName;
	private Double GuidePrice;
	private String MJ;
	private String Manufacturea;
	private String Rank;
	private String Engine;
	private String Gearbox;
	private String Lwh;
	private Double MaxSpeed;
	private String FuelConsumption;
	private String Warranty;
	private String Color;
	private Double Displacement;
	private Integer NumberofCylinders;
	private String Photo;

	//一对一单项外键关联	 无外键一方
	private Set<Reception> reception;
	private Set<Appointment> appointment;
	private Set<IntentionCustomer> icustomer;
	private Set<Subscription> subscription;
	// Constructors

	/** default constructor */
	public Vehicle() {
	}

	/** full constructor */
	public Vehicle(String VehicleNo, String Fletter, String Brand,
			String Series, String VehicleName, Double GuidePrice, String MJ,
			String Manufacturea, String Rank, String Engine, String Gearbox,
			String Lwh, Double MaxSpeed, String FuelConsumption,
			String Warranty, String Color, Double Displacement,
			Integer NumberofCylinders, String Photo) {
		this.VehicleNo = VehicleNo;
		this.Fletter = Fletter;
		this.Brand = Brand;
		this.Series = Series;
		this.VehicleName = VehicleName;
		this.GuidePrice = GuidePrice;
		this.MJ = MJ;
		this.Manufacturea = Manufacturea;
		this.Rank = Rank;
		this.Engine = Engine;
		this.Gearbox = Gearbox;
		this.Lwh = Lwh;
		this.MaxSpeed = MaxSpeed;
		this.FuelConsumption = FuelConsumption;
		this.Warranty = Warranty;
		this.Color = Color;
		this.Displacement = Displacement;
		this.NumberofCylinders = NumberofCylinders;
		this.Photo = Photo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVehicleNo() {
		return this.VehicleNo;
	}

	public void setVehicleNo(String VehicleNo) {
		this.VehicleNo = VehicleNo;
	}

	public String getFletter() {
		return this.Fletter;
	}

	public void setFletter(String Fletter) {
		this.Fletter = Fletter;
	}

	public String getBrand() {
		return this.Brand;
	}

	public void setBrand(String Brand) {
		this.Brand = Brand;
	}

	public String getSeries() {
		return this.Series;
	}

	public void setSeries(String Series) {
		this.Series = Series;
	}

	public String getVehicleName() {
		return this.VehicleName;
	}

	public void setVehicleName(String VehicleName) {
		this.VehicleName = VehicleName;
	}

	public Double getGuidePrice() {
		return this.GuidePrice;
	}

	public void setGuidePrice(Double GuidePrice) {
		this.GuidePrice = GuidePrice;
	}

	public String getMJ() {
		return this.MJ;
	}

	public void setMJ(String MJ) {
		this.MJ = MJ;
	}

	public String getManufacturea() {
		return this.Manufacturea;
	}

	public void setManufacturea(String Manufacturea) {
		this.Manufacturea = Manufacturea;
	}

	public String getRank() {
		return this.Rank;
	}

	public void setRank(String Rank) {
		this.Rank = Rank;
	}

	public String getEngine() {
		return this.Engine;
	}

	public void setEngine(String Engine) {
		this.Engine = Engine;
	}

	public String getGearbox() {
		return this.Gearbox;
	}

	public void setGearbox(String Gearbox) {
		this.Gearbox = Gearbox;
	}

	public String getLwh() {
		return this.Lwh;
	}

	public void setLwh(String Lwh) {
		this.Lwh = Lwh;
	}

	public Double getMaxSpeed() {
		return this.MaxSpeed;
	}

	public void setMaxSpeed(Double MaxSpeed) {
		this.MaxSpeed = MaxSpeed;
	}

	public String getFuelConsumption() {
		return this.FuelConsumption;
	}

	public void setFuelConsumption(String FuelConsumption) {
		this.FuelConsumption = FuelConsumption;
	}

	public String getWarranty() {
		return this.Warranty;
	}

	public void setWarranty(String Warranty) {
		this.Warranty = Warranty;
	}

	public String getColor() {
		return this.Color;
	}

	public void setColor(String Color) {
		this.Color = Color;
	}

	public Double getDisplacement() {
		return this.Displacement;
	}

	public void setDisplacement(Double Displacement) {
		this.Displacement = Displacement;
	}

	public Integer getNumberofCylinders() {
		return this.NumberofCylinders;
	}

	public void setNumberofCylinders(Integer NumberofCylinders) {
		this.NumberofCylinders = NumberofCylinders;
	}

	public String getPhoto() {
		return this.Photo;
	}

	public void setPhoto(String Photo) {
		this.Photo = Photo;
	}

	public Set<Reception> getReception() {
		return reception;
	}

	public void setReception(Set<Reception> reception) {
		this.reception = reception;
	}

	public Set<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(Set<Appointment> appointment) {
		this.appointment = appointment;
	}

	public Set<IntentionCustomer> getIcustomer() {
		return icustomer;
	}

	public void setIcustomer(Set<IntentionCustomer> icustomer) {
		this.icustomer = icustomer;
	}

	public Set<Subscription> getSubscription() {
		return subscription;
	}

	public void setSubscription(Set<Subscription> subscription) {
		this.subscription = subscription;
	}

}
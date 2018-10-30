package com.tthg.entity;

/**
 * VehicleCustomer entity. @author MyEclipse Persistence Tools
 */

public class VehicleCustomer implements java.io.Serializable {

	// Fields

	private Integer id;
	private String customerNo;
	private String customerName;
	private String sex;
	private Integer age;
	private String telephone;
	private String address;
	private String pc;
	private String email;
	private String hobby;
	private String cardId;
	private String driverLicense;
	private Integer carId;
	private String consultantName;

	// Constructors

	/** default constructor */
	public VehicleCustomer() {
	}

	/** minimal constructor */
	public VehicleCustomer(Integer carId) {
		this.carId = carId;
	}

	/** full constructor */
	public VehicleCustomer(String customerNo, String customerName, String sex,
			Integer age, String telephone, String address, String pc,
			String email, String hobby, String cardId, String driverLicense,
			Integer carId, String consultantName) {
		this.customerNo = customerNo;
		this.customerName = customerName;
		this.sex = sex;
		this.age = age;
		this.telephone = telephone;
		this.address = address;
		this.pc = pc;
		this.email = email;
		this.hobby = hobby;
		this.cardId = cardId;
		this.driverLicense = driverLicense;
		this.carId = carId;
		this.consultantName = consultantName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPc() {
		return this.pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getDriverLicense() {
		return this.driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getConsultantName() {
		return this.consultantName;
	}

	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}

}
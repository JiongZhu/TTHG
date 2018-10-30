package com.tthg.entity;

/**
 * IntentionCustomer entity. @author MyEclipse Persistence Tools
 */

public class IntentionCustomer implements java.io.Serializable {

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
	private String payment;
	private Integer carStyleId;
	private String attention;
	private String otherRequirements;
	private String negotiation;
	private String result;
	private String consultantName;
	
	private Vehicle vehicle;
	//一对一外键关联	 无外键一方	
	private Appointment appointment;
	//处理关联关系，解决前台数据显示为0问题
	private String cname;
	// Constructors

	/** default constructor */
	public IntentionCustomer() {
	}

	/** minimal constructor */
	public IntentionCustomer(Integer carStyleId) {
		this.carStyleId = carStyleId;
	}

	/** full constructor */
	public IntentionCustomer(String customerNo, String customerName,
			String sex, Integer age, String telephone, String address,
			String pc, String email, String hobby, String cardId,
			String driverLicense, String payment, Integer carStyleId,
			String attention, String otherRequirements, String negotiation,
			String result, String consultantName) {
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
		this.payment = payment;
		this.carStyleId = carStyleId;
		this.attention = attention;
		this.otherRequirements = otherRequirements;
		this.negotiation = negotiation;
		this.result = result;
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

	public String getPayment() {
		return this.payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Integer getCarStyleId() {
		return this.carStyleId;
	}

	public void setCarStyleId(Integer carStyleId) {
		this.carStyleId = carStyleId;
	}

	public String getAttention() {
		return this.attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getOtherRequirements() {
		return this.otherRequirements;
	}

	public void setOtherRequirements(String otherRequirements) {
		this.otherRequirements = otherRequirements;
	}

	public String getNegotiation() {
		return this.negotiation;
	}

	public void setNegotiation(String negotiation) {
		this.negotiation = negotiation;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getConsultantName() {
		return this.consultantName;
	}

	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
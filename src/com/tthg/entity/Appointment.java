package com.tthg.entity;

import java.util.Date;

/**
 * Appointment entity. @author MyEclipse Persistence Tools
 */

public class Appointment implements java.io.Serializable {

	// Fields

	private Integer id;
	private String appointmentNo;
	private String customerName;
	private String telephone;
	private Integer carStyleId;//外键Vehicle-id
	private Date scheduledTime;
	private Integer scheduledPersonNum;
	private String consultantName;
	private String remarks;	
	private Integer intentionId;//外键IntentionCustomer-id
	private String state;
	//一对一单项外键关联	 有外键一方
	private Vehicle vehicle;
	private IntentionCustomer customer;
	// Constructors
	//处理关联关系，解决前台数据显示为0问题
	private String cname;
	private int iid;
	/** default constructor */
	public Appointment() {
	}

	/** minimal constructor */
	public Appointment(Integer carStyleId, Integer intentionId) {
		this.carStyleId = carStyleId;
		this.intentionId = intentionId;
	}

	/** full constructor */
	public Appointment(String appointmentNo, String customerName,
			String telephone, Integer carStyleId, Date scheduledTime,
			Integer scheduledPersonNum, String consultantName, String remarks,
			Integer intentionId, String state) {
		this.appointmentNo = appointmentNo;
		this.customerName = customerName;
		this.telephone = telephone;
		this.carStyleId = carStyleId;
		this.scheduledTime = scheduledTime;
		this.scheduledPersonNum = scheduledPersonNum;
		this.consultantName = consultantName;
		this.remarks = remarks;
		this.intentionId = intentionId;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppointmentNo() {
		return this.appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
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

	public Integer getCarStyleId() {
		return this.carStyleId;
	}

	public void setCarStyleId(Integer carStyleId) {
		this.carStyleId = carStyleId;
	}

	public Date getScheduledTime() {
		return this.scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public Integer getScheduledPersonNum() {
		return this.scheduledPersonNum;
	}

	public void setScheduledPersonNum(Integer scheduledPersonNum) {
		this.scheduledPersonNum = scheduledPersonNum;
	}

	public String getConsultantName() {
		return this.consultantName;
	}

	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIntentionId() {
		return this.intentionId;
	}

	public void setIntentionId(Integer intentionId) {
		this.intentionId = intentionId;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public IntentionCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(IntentionCustomer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}
}
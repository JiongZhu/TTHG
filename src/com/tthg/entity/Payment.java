package com.tthg.entity;


/**
 * Payment entity. @author MyEclipse Persistence Tools
 */
/**
 * 客户支付实体
 * @author 葛康  编写者
 * @since 2016-01-01 编写时间
 *
 */
public class Payment implements java.io.Serializable {

	// Fields

	private Integer id;//id
	private String manNo;//支付编号
	private String cname;//客户
	private Double costPrice;//成本费
	private Double pfee;//劳务费
	private Double tatle;//总计
	private String pdate;//支付时间
	private Short state;//是否签字
	private Integer reId;//外键（维修登记）
	
	private String edate;//支付时间
	private String reNo;//外键编号（维修登记）
	private Repair repair;//维修登记实体

	// Constructors

	/** default constructor */
	public Payment() {
	}

	/** minimal constructor */
	public Payment(Integer reId) {
		this.reId = reId;
	}

	/** full constructor */
	public Payment(String manNo, String cname, Double costPrice, Double pfee,
			Double tatle, String pdate, Short state, Integer reId) {
		this.manNo = manNo;
		this.cname = cname;
		this.costPrice = costPrice;
		this.pfee = pfee;
		this.tatle = tatle;
		this.pdate = pdate;
		this.state = state;
		this.reId = reId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManNo() {
		return this.manNo;
	}

	public void setManNo(String manNo) {
		this.manNo = manNo;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getPfee() {
		return this.pfee;
	}

	public void setPfee(Double pfee) {
		this.pfee = pfee;
	}

	public Double getTatle() {
		return this.tatle;
	}

	public void setTatle(Double tatle) {
		this.tatle = tatle;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getReNo() {
		return reNo;
	}

	public void setReNo(String reNo) {
		this.reNo = reNo;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Integer getReId() {
		return this.reId;
	}

	public void setReId(Integer reId) {
		this.reId = reId;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

}
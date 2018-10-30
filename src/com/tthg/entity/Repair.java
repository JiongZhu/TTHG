package com.tthg.entity;


/**
 * Repair entity. @author MyEclipse Persistence Tools
 */
/**
 * 维修登记实体
 * @author 葛康  编写者
 * @since 2016-01-01 编写时间
 *
 */
public class Repair implements java.io.Serializable {

	// Fields

	private Integer id;//id
	private String reNo;//登记编号
	private String reDate;//登记日期
	private String plateNo;//车辆编号
	private Double kilometres;//里程
	private String reContent;//故障内容
	private String reOperate;//安排人
	private String cname;//客户
	private String uname;//负责人
	private Short state;//维修状态

	private String enDate;//额外属性（预期完成时间）
	
	private Trouble trouble;//故障维修实体
	
	private Evaluate evaluate;//维修评估实体
	
	private Payment payment;//客户支付实体
	
	private Engstate engstate;//工程师状态实体

	// Constructors

	/** default constructor */
	public Repair() {
	}

	/** full constructor */
	public Repair(String reNo, String reDate, String plateNo,
			Double kilometres, String reContent, String reOperate,
			String cname, String uname, Short state) {
		this.reNo = reNo;
		this.reDate = reDate;
		this.plateNo = plateNo;
		this.kilometres = kilometres;
		this.reContent = reContent;
		this.reOperate = reOperate;
		this.cname = cname;
		this.uname = uname;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReNo() {
		return this.reNo;
	}

	public void setReNo(String reNo) {
		this.reNo = reNo;
	}

	public String getReDate() {
		return this.reDate;
	}

	public void setReDate(String reDate) {
		this.reDate = reDate;
	}

	public String getPlateNo() {
		return this.plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Double getKilometres() {
		return this.kilometres;
	}

	public void setKilometres(Double kilometres) {
		this.kilometres = kilometres;
	}

	public String getReContent() {
		return this.reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public String getReOperate() {
		return this.reOperate;
	}

	public void setReOperate(String reOperate) {
		this.reOperate = reOperate;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Trouble getTrouble() {
		return trouble;
	}

	public void setTrouble(Trouble trouble) {
		this.trouble = trouble;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getEnDate() {
		return enDate;
	}

	public void setEnDate(String enDate) {
		this.enDate = enDate;
	}

	public Engstate getEngstate() {
		return engstate;
	}

	public void setEngstate(Engstate engstate) {
		this.engstate = engstate;
	}

}
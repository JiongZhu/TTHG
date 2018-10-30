package com.tthg.entity;

/**
 * Component entity. @author MyEclipse Persistence Tools
 */
/**
 * 零件调用实体
 * @author 葛康  编写者
 * @since 2016-12-23 编写时间
 *
 */
public class Component implements java.io.Serializable {

	// Fields

	private Integer id;//id
	private String compNo;//编号
	private String compName;//零件名
	private String modelNum;//零件规格
	private String cdate;//调用时间
	private Integer cnum;//零件数量
	private Double unitPri;//单价
	private String brokerage;//负责人
	private String uname;//调动人
	private Integer troId;//外键（故障维修）
	
	private String troNo;//额外（故障维修）
	private Trouble trouble;//故障维修实体

	// Constructors

	/** default constructor */
	public Component() {
	}

	/** minimal constructor */
	public Component(Integer troId) {
		this.troId = troId;
	}

	/** full constructor */
	public Component(String compNo, String compName, String modelNum,
			String cdate, Integer cnum, Double unitPri, String brokerage,
			String uname, Integer troId) {
		this.compNo = compNo;
		this.compName = compName;
		this.modelNum = modelNum;
		this.cdate = cdate;
		this.cnum = cnum;
		this.unitPri = unitPri;
		this.brokerage = brokerage;
		this.uname = uname;
		this.troId = troId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompNo() {
		return this.compNo;
	}

	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}

	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getModelNum() {
		return this.modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	public String getCdate() {
		return this.cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public Integer getCnum() {
		return this.cnum;
	}

	public void setCnum(Integer cnum) {
		this.cnum = cnum;
	}

	public Double getUnitPri() {
		return this.unitPri;
	}

	public void setUnitPri(Double unitPri) {
		this.unitPri = unitPri;
	}

	public String getBrokerage() {
		return this.brokerage;
	}

	public void setBrokerage(String brokerage) {
		this.brokerage = brokerage;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getTroId() {
		return this.troId;
	}

	public void setTroId(Integer troId) {
		this.troId = troId;
	}

	public Trouble getTrouble() {
		return trouble;
	}

	public void setTrouble(Trouble trouble) {
		this.trouble = trouble;
	}

	public String getTroNo() {
		return troNo;
	}

	public void setTroNo(String troNo) {
		this.troNo = troNo;
	}

}
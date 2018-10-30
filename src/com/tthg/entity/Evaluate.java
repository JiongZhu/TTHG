package com.tthg.entity;


/**
 * Evaluate entity. @author MyEclipse Persistence Tools
 */
/**
 * 维修评估实体
 * @author 葛康  编写者
 * @since 2016-12-25 编写时间
 *
 */
public class Evaluate implements java.io.Serializable {

	// Fields

	private Integer id;//id
	private String evNo;//评估编号
	private String reDate;//登记时间
	private String edate;//完成时间
	private String plateNo;//车辆编号
	private String reContent;//故障描述
	private String remarks;//备注
	private String uname;//负责人
	private Integer reId;//外键（维修登记）
	
	private Repair repair;//维修登记实体
	private String reNo;//额外编号
	// Constructors

	/** default constructor */
	public Evaluate() {
	}

	/** minimal constructor */
	public Evaluate(Integer reId) {
		this.reId = reId;
	}

	/** full constructor */
	public Evaluate(String evNo, String reDate, String edate,
			String plateNo, String reContent, String remarks,
			String uname, Integer reId) {
		this.evNo = evNo;
		this.reDate = reDate;
		this.edate = edate;
		this.plateNo = plateNo;
		this.reContent = reContent;
		this.remarks = remarks;
		this.uname = uname;
		this.reId = reId;
	}

	// Property accessors
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvNo() {
		return evNo;
	}

	public void setEvNo(String evNo) {
		this.evNo = evNo;
	}

	public String getReDate() {
		return reDate;
	}

	public void setReDate(String reDate) {
		this.reDate = reDate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getReId() {
		return reId;
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

	public String getReNo() {
		return reNo;
	}

	public void setReNo(String reNo) {
		this.reNo = reNo;
	}


}
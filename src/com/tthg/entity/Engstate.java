package com.tthg.entity;

/**
 * Engstate entity. @author MyEclipse Persistence Tools
 */
/**
 * 工程师状态实体
 * @author 葛康  编写者
 * @since 2016-12-29 编写时间
 *
 */
public class Engstate implements java.io.Serializable {

	// Fields

	private Integer id;//id
	private String uno;//用户编号
	private String uname;//用户名
	private String edate;//完成时间
	private Short state;//完成状态
	private Integer reId;//外键维修登记
	
	private Integer extreId;//额外属性，显示外键信息
	private String extreNo;//额外属性，显示外键对应编号
	private Repair repair;//维修登记实体
	private String enDate;//完成时间
	// Constructors

	/** default constructor */
	public Engstate() {
	}

	/** minimal constructor */
	public Engstate(Integer reId) {
		this.reId = reId;
	}

	/** full constructor */
	public Engstate(String uno, String uname, String edate, Short state,
			Integer reId) {
		this.uno = uno;
		this.uname = uname;
		this.edate = edate;
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

	public String getUno() {
		return this.uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
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

	public Integer getExtreId() {
		return extreId;
	}

	public void setExtreId(Integer extreId) {
		this.extreId = extreId;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public String getExtreNo() {
		return extreNo;
	}

	public void setExtreNo(String extreNo) {
		this.extreNo = extreNo;
	}

	public String getEnDate() {
		return enDate;
	}

	public void setEnDate(String enDate) {
		this.enDate = enDate;
	}

}
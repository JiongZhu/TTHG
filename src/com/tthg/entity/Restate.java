package com.tthg.entity;


/**
 * Restate entity. @author MyEclipse Persistence Tools
 */
/**
 * 维修状态实体
 * @author 葛康  编写者
 * @since 2016-12-28 编写时间
 *
 */
public class Restate implements java.io.Serializable {

	// Fields

	private Integer id;//id
	private String proNo;//项目编号
	private String plateNo;//车辆编号
	private Short execution;//完成度
	private Integer troId;//外键(故障检修)
	
	private String troNo;//外键编号(故障检修)
	
	private Trouble trouble;//故障维修实体

	// Constructors

	/** default constructor */
	public Restate() {
	}

	/** minimal constructor */
	public Restate(Integer troId) {
		this.troId = troId;
	}

	/** full constructor */
	public Restate(String proNo, String plateNo, Short execution, Integer troId) {
		this.proNo = proNo;
		this.plateNo = plateNo;
		this.execution = execution;
		this.troId = troId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProNo() {
		return this.proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getPlateNo() {
		return this.plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Short getExecution() {
		return execution;
	}

	public void setExecution(Short execution) {
		this.execution = execution;
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
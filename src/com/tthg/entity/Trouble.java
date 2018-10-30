package com.tthg.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Trouble entity. @author MyEclipse Persistence Tools
 */
/**
 * 故障维修表实体
 * @author 葛康  编写者
 * @since 2016-12-18 编写时间
 *
 */
public class Trouble implements java.io.Serializable {

	// Fields

	private Integer id;//id
	private String troNo;//故障编号
	private String troPart;//故障部位
	private String troContent;//故障描述
	private String handle;//处理方式
	private String plateNo;//车辆编号
	private String uname;//负责人
	private Integer reId;//外键（维修登记）
	
	private String reNo;//额外 参数
	
	private Set components=new HashSet();//零件调用
	
	private Repair repair;//维修登记实体
	
	private Restate restate;//维修评估

	// Constructors

	/** default constructor */
	public Trouble() {
	}

	/** minimal constructor */
	public Trouble(Integer reId) {
		this.reId = reId;
	}

	/** full constructor */
	public Trouble(String troNo, String troPart, String troContent,
			String handle, String plateNo, String uname, Integer reId) {
		this.troNo = troNo;
		this.troPart = troPart;
		this.troContent = troContent;
		this.handle = handle;
		this.plateNo = plateNo;
		this.uname = uname;
		this.reId = reId;
	}

	// Property accessors
//get和set
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTroNo() {
		return this.troNo;
	}

	public void setTroNo(String troNo) {
		this.troNo = troNo;
	}

	public String getTroPart() {
		return this.troPart;
	}

	public void setTroPart(String troPart) {
		this.troPart = troPart;
	}

	public String getTroContent() {
		return this.troContent;
	}

	public void setTroContent(String troContent) {
		this.troContent = troContent;
	}

	public String getHandle() {
		return this.handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getPlateNo() {
		return this.plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getReId() {
		return this.reId;
	}

	public void setReId(Integer reId) {
		this.reId = reId;
	}

	public Set getComponents() {
		return components;
	}

	public void setComponents(Set components) {
		this.components = components;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public Restate getRestate() {
		return restate;
	}

	public void setRestate(Restate restate) {
		this.restate = restate;
	}

	public String getReNo() {
		return reNo;
	}

	public void setReNo(String reNo) {
		this.reNo = reNo;
	}

}
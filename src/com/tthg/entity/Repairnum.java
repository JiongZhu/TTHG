package com.tthg.entity;

/**
 * Repairnum entity. @author MyEclipse Persistence Tools
 */

public class Repairnum implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer januarynum;
	private Integer februarynum;
	private Integer marchnum;
	private Integer aprilnum;
	private Integer maynum;
	private Integer junenum;
	private Integer julynum;
	private Integer augustnum;
	private Integer septembernum;
	private Integer octobernum;
	private Integer novembernum;
	private Integer decembernum;

	// Constructors

	/** default constructor */
	public Repairnum() {
	}

	/** full constructor */
	public Repairnum(Integer januarynum, Integer februarynum, Integer marchnum,
			Integer aprilnum, Integer maynum, Integer junenum, Integer julynum,
			Integer augustnum, Integer septembernum, Integer octobernum,
			Integer novembernum, Integer decembernum) {
		this.januarynum = januarynum;
		this.februarynum = februarynum;
		this.marchnum = marchnum;
		this.aprilnum = aprilnum;
		this.maynum = maynum;
		this.junenum = junenum;
		this.julynum = julynum;
		this.augustnum = augustnum;
		this.septembernum = septembernum;
		this.octobernum = octobernum;
		this.novembernum = novembernum;
		this.decembernum = decembernum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJanuarynum() {
		return this.januarynum;
	}

	public void setJanuarynum(Integer januarynum) {
		this.januarynum = januarynum;
	}

	public Integer getFebruarynum() {
		return this.februarynum;
	}

	public void setFebruarynum(Integer februarynum) {
		this.februarynum = februarynum;
	}

	public Integer getMarchnum() {
		return this.marchnum;
	}

	public void setMarchnum(Integer marchnum) {
		this.marchnum = marchnum;
	}

	public Integer getAprilnum() {
		return this.aprilnum;
	}

	public void setAprilnum(Integer aprilnum) {
		this.aprilnum = aprilnum;
	}

	public Integer getMaynum() {
		return this.maynum;
	}

	public void setMaynum(Integer maynum) {
		this.maynum = maynum;
	}

	public Integer getJunenum() {
		return this.junenum;
	}

	public void setJunenum(Integer junenum) {
		this.junenum = junenum;
	}

	public Integer getJulynum() {
		return this.julynum;
	}

	public void setJulynum(Integer julynum) {
		this.julynum = julynum;
	}

	public Integer getAugustnum() {
		return this.augustnum;
	}

	public void setAugustnum(Integer augustnum) {
		this.augustnum = augustnum;
	}

	public Integer getSeptembernum() {
		return this.septembernum;
	}

	public void setSeptembernum(Integer septembernum) {
		this.septembernum = septembernum;
	}

	public Integer getOctobernum() {
		return this.octobernum;
	}

	public void setOctobernum(Integer octobernum) {
		this.octobernum = octobernum;
	}

	public Integer getNovembernum() {
		return this.novembernum;
	}

	public void setNovembernum(Integer novembernum) {
		this.novembernum = novembernum;
	}

	public Integer getDecembernum() {
		return this.decembernum;
	}

	public void setDecembernum(Integer decembernum) {
		this.decembernum = decembernum;
	}

}
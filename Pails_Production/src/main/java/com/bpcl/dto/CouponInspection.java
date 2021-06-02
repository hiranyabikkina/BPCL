package com.bpcl.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CouponInspection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1942224031118121496L;

	private Long uuid;

	private String couponCode;

	private String couponSize;

	private String lotCode;

	private String plantLocation;

	private String lineUuid;

	private Timestamp createTime;

	private Integer status;

	/**
	 * Setter and Getter method
	 * 
	 * @return
	 */

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponSize() {
		return couponSize;
	}

	public void setCouponSize(String couponSize) {
		this.couponSize = couponSize;
	}

	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
	}

	public String getPlantLocation() {
		return plantLocation;
	}

	public void setPlantLocation(String plantLocation) {
		this.plantLocation = plantLocation;
	}

	public String getLineUuid() {
		return lineUuid;
	}

	public void setLineUuid(String lineUuid) {
		this.lineUuid = lineUuid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
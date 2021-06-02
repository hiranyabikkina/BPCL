package com.bpcl.dto;

import java.io.Serializable;

/**
 * 
 * 
 * @author swetha
 *
 */

public class CouponInspections implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3146201414743292529L;

	private String uuid;

	private Integer plantLocationCode;

	private String couponCode;

	private String couponSize;

	private String lotCode;

	private Integer status;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getPlantLocationCode() {
		return plantLocationCode;
	}

	public void setPlantLocationCode(Integer plantLocationCode) {
		this.plantLocationCode = plantLocationCode;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	

}

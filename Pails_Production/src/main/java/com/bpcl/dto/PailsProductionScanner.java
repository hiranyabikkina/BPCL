package com.bpcl.dto;

import java.io.Serializable;

public class PailsProductionScanner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1350334389555507058L;

	private String bottleCode;

	private String couponCode;

	private Integer compQnty;

	private Integer bottleCodeStatus;

	private Integer couponCodeStatus;

	public String getBottleCode() {
		return bottleCode;
	}

	public void setBottleCode(String bottleCode) {
		this.bottleCode = bottleCode;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getCompQnty() {
		return compQnty;
	}

	public void setCompQnty(Integer compQnty) {
		this.compQnty = compQnty;
	}

	public Integer getBottleCodeStatus() {
		return bottleCodeStatus;
	}

	public void setBottleCodeStatus(Integer bottleCodeStatus) {
		this.bottleCodeStatus = bottleCodeStatus;
	}

	public Integer getCouponCodeStatus() {
		return couponCodeStatus;
	}

	public void setCouponCodeStatus(Integer couponCodeStatus) {
		this.couponCodeStatus = couponCodeStatus;
	}

}

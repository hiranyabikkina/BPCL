package com.bpcl.bean;

import java.util.List;

public class CouponInspectionsToKafka {

	private Integer plantLocation;

	private List<String> couponCode;

	private String couponSize;

	private String lotCode;
	
	
	public Integer getPlantLocation() {
		return plantLocation;
	}

	public void setPlantLocation(Integer plantLocation) {
		this.plantLocation = plantLocation;
	}

	

	public List<String> getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(List<String> couponCode) {
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
	
	
	

	@Override
	public String toString() {
		return "CouponInspectionsToKafka [plantLocation=" + plantLocation + ", couponCode=" + couponCode
				+ ", couponSize=" + couponSize + ", lotCode=" + lotCode + "]";
	}
}

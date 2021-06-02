package com.bpcl.dto;

import java.util.Date;

public class CouponInspectionsMongo {

	private String couponCode;

	private String couponSize;

	private Integer status;
	
	private Date createTime;

	private Date updatedTime;

	private String lotCode;
	
	

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
	}
	
	
}

package com.bpcl.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CouponProduction implements Serializable {
	

	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -4041987330781578535L;

	private String uuid;
	
	private Integer couponQuantity;
	
	private Integer lotCodeQuantity;
	
	private String couponSize;
	
	private Timestamp createTime;
	
	private Integer status;
	
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getCouponQuantity() {
		return couponQuantity;
	}

	public void setCouponQuantity(Integer couponQuantity) {
		this.couponQuantity = couponQuantity;
	}

	public Integer getLotCodeQuantity() {
		return lotCodeQuantity;
	}

	public void setLotCodeQuantity(Integer lotCodeQuantity) {
		this.lotCodeQuantity = lotCodeQuantity;
	}

	public String getCouponSize() {
		return couponSize;
	}

	public void setCouponSize(String couponSize) {
		this.couponSize = couponSize;
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

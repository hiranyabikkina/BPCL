package com.bpcl.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PailsProduction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1701737543147099522L;

	private String uuid;

	private String planId;

	private String couponCode;

	private String bottleCode;

	private String lineId;

	private Integer isCouponApply;

	private String packType;

	private Integer status;

	private Timestamp createTime;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getBottleCode() {
		return bottleCode;
	}

	public void setBottleCode(String bottleCode) {
		this.bottleCode = bottleCode;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public Integer getIsCouponApply() {
		return isCouponApply;
	}

	public void setIsCouponApply(Integer isCouponApply) {
		this.isCouponApply = isCouponApply;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}

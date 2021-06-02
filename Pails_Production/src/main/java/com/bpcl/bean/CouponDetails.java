package com.bpcl.bean;

import java.util.List;

public class CouponDetails {

	private Integer locCode;

	private String lineNo;

	private String lotNo;

	private List<String> couponDetail;

	public Integer getLocCode() {
		return locCode;
	}

	public void setLocCode(Integer locCode) {
		this.locCode = locCode;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public List<String> getCouponDetail() {
		return couponDetail;
	}

	public void setCouponDetail(List<String> couponDetail) {
		this.couponDetail = couponDetail;
	}

}
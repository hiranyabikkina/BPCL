package com.bpcl.bean;

public class PackDetails {

	private String couponNo;

	private String labelCode;

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	@Override
	public String toString() {
		return "packDetails [couponNo=" + couponNo + ", labelCode=" + labelCode + "]";
	}

}

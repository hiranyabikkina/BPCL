package com.bpcl.bean;

import java.util.List;

public class PailsBarrelsDetails {

	private String prodCode;

	private Integer locCode;

	private String packType;

	private List<String> pailsBarrelsDetail;

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public Integer getLocCode() {
		return locCode;
	}

	public void setLocCode(Integer locCode) {
		this.locCode = locCode;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	public List<String> getPailsBarrelsDetail() {
		return pailsBarrelsDetail;
	}

	public void setPailsBarrelsDetail(List<String> pailsBarrelsDetail) {
		this.pailsBarrelsDetail = pailsBarrelsDetail;
	}

}
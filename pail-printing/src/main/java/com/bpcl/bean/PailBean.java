package com.bpcl.bean;

public class PailBean {

	private String plantLocation;
	
	private String lineUuid;
	
	private Integer productCode;
	
	private String productName;

	private Integer productSize;

	private String packType;

	private Integer totalQty;

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

	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductSize() {
		return productSize;
	}

	public void setProductSize(Integer productSize) {
		this.productSize = productSize;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	public Integer getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}

	@Override
	public String toString() {
		return "PailBean [plantLocation=" + plantLocation + ", lineUuid=" + lineUuid + ", productCode=" + productCode
				+ ", productName=" + productName + ", productSize=" + productSize + ", packType=" + packType
				+ ", totalQty=" + totalQty + "]";
	}

}

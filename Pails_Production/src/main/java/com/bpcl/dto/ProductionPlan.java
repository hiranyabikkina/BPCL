package com.bpcl.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductionPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2590194872586576159L;

	private String uuid;

	private Integer planId;

	private Integer locCode;

	private String plantLocation;

	private String lineUuid;

	private String planDate;

	private String productCode;

	private String productName;

	private Integer priority;

	private String packType;

	private String sizeCode;

	private Integer unitsPerCase;

	private String purpose;

	private Integer volume;

	private Double mrp;

	private String mrpBatch;

	private String productBatch;

	private String isCouponApply;
	
	private String labelPrint;

	private Timestamp createTime;

	private Integer status;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getLocCode() {
		return locCode;
	}

	public void setLocCode(Integer locCode) {
		this.locCode = locCode;
	}

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

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public Integer getUnitsPerCase() {
		return unitsPerCase;
	}

	public void setUnitsPerCase(Integer unitsPerCase) {
		this.unitsPerCase = unitsPerCase;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public String getMrpBatch() {
		return mrpBatch;
	}

	public void setMrpBatch(String mrpBatch) {
		this.mrpBatch = mrpBatch;
	}

	public String getProductBatch() {
		return productBatch;
	}

	public void setProductBatch(String productBatch) {
		this.productBatch = productBatch;
	}

	public String getIsCouponApply() {
		return isCouponApply;
	}

	public void setIsCouponApply(String isCouponApply) {
		this.isCouponApply = isCouponApply;
	}

	public String getLabelPrint() {
		return labelPrint;
	}

	public void setLabelPrint(String labelPrint) {
		this.labelPrint = labelPrint;
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

	@Override
	public String toString() {
		return "ProductionPlan [uuid=" + uuid + ", planId=" + planId + ", locCode=" + locCode + ", plantLocation="
				+ plantLocation + ", lineUuid=" + lineUuid + ", planDate=" + planDate + ", productCode=" + productCode
				+ ", productName=" + productName + ", priority=" + priority + ", packType=" + packType + ", sizeCode="
				+ sizeCode + ", unitsPerCase=" + unitsPerCase + ", purpose=" + purpose + ", volume=" + volume + ", mrp="
				+ mrp + ", mrpBatch=" + mrpBatch + ", productBatch=" + productBatch + ", isCouponApply=" + isCouponApply
				+ ", labelPrint=" + labelPrint + ", createTime=" + createTime + ", status=" + status + "]";
	}

	

}

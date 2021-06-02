package com.bpcl.bean;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author s.k.r.subramanyam reddy guide by hiranya
 *
 */
public class ProductionPlanBean {

	
	@JsonProperty("id")
	private Integer planId;

	@JsonProperty("locCode")
	private Integer locCode;

	@JsonProperty("locationName")
	private String plantLocation;

	@JsonProperty("prodCode")
	private String productCode;

	@JsonProperty("prodName")
	private String productName;

	@JsonProperty("prodSize")
	private String sizeCode;

	@JsonProperty("packType")
	private String packType;

	@JsonProperty("upc")
	private Integer unitsPerCase;

	@JsonProperty("prodBatchNo")
	private String productBatch;

	@JsonProperty("purpose")
	private String purpose;

	@JsonProperty("mrpBatch")
	private String mrpBatch;

	@JsonProperty("mrp")
	private Double mrp;

	@JsonProperty("applyCoupon")
	private String isCouponApply;

	@JsonProperty("productionScheduleDate")
	private String planDate;

	@JsonProperty("productionOrd")
	private Integer priority;

	@JsonProperty("batchQty")
	private Integer volume;

	@JsonProperty("lineNo")
	private String lineUuid;

	@JsonProperty("machineName")
	private String machineName;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("id")
	public Integer getPlanId() {
	return planId;
	}

	@JsonProperty("id")
	public void setPlanId(Integer planId) {
	this.planId = planId;
	}

	@JsonProperty("locCode")
	public Integer getLocCode() {
	return locCode;
	}

	@JsonProperty("locCode")
	public void setLocCode(Integer locCode) {
	this.locCode = locCode;
	}

	@JsonProperty("locationName")
	public String getPlantLocation() {
	return plantLocation;
	}

	@JsonProperty("locationName")
	public void setPlantLocation(String plantLocation) {
	this.plantLocation = plantLocation;
	}

	@JsonProperty("prodCode")
	public String getProductCode() {
	return productCode;
	}

	@JsonProperty("prodCode")
	public void setProductCode(String productCode) {
	this.productCode = productCode;
	}

	@JsonProperty("prodName")
	public String getProductName() {
	return productName;
	}

	@JsonProperty("prodName")
	public void setProductName(String productName) {
	this.productName = productName;
	}

	@JsonProperty("prodSize")
	public String getSizeCode() {
	return sizeCode;
	}

	@JsonProperty("prodSize")
	public void setSizeCode(String sizeCode) {
	this.sizeCode = sizeCode;
	}

	@JsonProperty("packType")
	public String getPackType() {
	return packType;
	}

	@JsonProperty("packType")
	public void setPackType(String packType) {
	this.packType = packType;
	}

	@JsonProperty("upc")
	public Integer getUnitsPerCase() {
	return unitsPerCase;
	}

	@JsonProperty("upc")
	public void setUnitsPerCase(Integer unitsPerCase) {
	this.unitsPerCase = unitsPerCase;
	}

	@JsonProperty("prodBatchNo")
	public String getProductBatch() {
	return productBatch;
	}

	@JsonProperty("prodBatchNo")
	public void setProductBatch(String productBatch) {
	this.productBatch = productBatch;
	}

	@JsonProperty("purpose")
	public String getPurpose() {
	return purpose;
	}

	@JsonProperty("purpose")
	public void setPurpose(String purpose) {
	this.purpose = purpose;
	}

	@JsonProperty("mrpBatch")
	public String getMrpBatch() {
	return mrpBatch;
	}

	@JsonProperty("mrpBatch")
	public void setMrpBatch(String mrpBatch) {
	this.mrpBatch = mrpBatch;
	}

	@JsonProperty("mrp")
	public Double getMrp() {
	return mrp;
	}

	@JsonProperty("mrp")
	public void setMrp(Double mrp) {
	this.mrp = mrp;
	}

	@JsonProperty("applyCoupon")
	public String getIsCouponApply() {
	return isCouponApply;
	}

	@JsonProperty("applyCoupon")
	public void setIsCouponApply(String isCouponApply) {
	this.isCouponApply = isCouponApply;
	}

	@JsonProperty("productionScheduleDate")
	public String getPlanDate() {
	return planDate;
	}

	@JsonProperty("productionScheduleDate")
	public void setPlanDate(String planDate) {
	this.planDate = planDate;
	}

	@JsonProperty("productionOrd")
	public Integer getPriority() {
	return priority;
	}

	@JsonProperty("productionOrd")
	public void setPriority(Integer priority) {
	this.priority = priority;
	}

	@JsonProperty("batchQty")
	public Integer getVolume() {
	return volume;
	}

	@JsonProperty("batchQty")
	public void setBatchVolume(Integer volume) {
	this.volume = volume;
	}

	@JsonProperty("lineNo")
	public String getLineUuid() {
	return lineUuid;
	}

	@JsonProperty("lineNo")
	public void setLineUuid(String lineUuid) {
	this.lineUuid = lineUuid;
	}

	@JsonProperty("machineName")
	public String getMachineName() {
	return machineName;
	}

	@JsonProperty("machineName")
	public void setMachineName(String machineName) {
	this.machineName = machineName;
	}

	@JsonProperty("status")
	public Integer getStatus() {
	return status;
	}

	@JsonProperty("status")
	public void setStatus(Integer status) {
	this.status = status;
	}

	@Override
	public String toString() {
	return "ProductionPlanPayload [planId=" + planId + ", locCode=" + locCode + ", plantLocation=" + plantLocation
	+ ", productCode=" + productCode + ", productName=" + productName + ", sizeCode=" + sizeCode
	+ ", packType=" + packType + ", unitsPerCase=" + unitsPerCase + ", productBatch=" + productBatch
	+ ", purpose=" + purpose + ", mrpBatch=" + mrpBatch + ", mrp=" + mrp + ", isCouponApply="
	+ isCouponApply + ", planDate=" + planDate + ", priority=" + priority + ", volume=" + volume
	+ ", lineUuid=" + lineUuid + ", machineName=" + machineName + ", status=" + status + "]";
	}

	
}

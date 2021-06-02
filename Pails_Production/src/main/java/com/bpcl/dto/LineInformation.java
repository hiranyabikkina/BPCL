package com.bpcl.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class LineInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6911100548411352194L;

	private String uuid;

	private Integer locCode;

	private String lineNo;

	private String lineName;

	private String equipmentType;

	private String brandOemName;

	private String modelNo;

	private String serialNo;

	private String macId;

	private String installationDate;

	private String ipAdd;

	private Integer portNo;

	private Timestamp createTime;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

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

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getBrandOemName() {
		return brandOemName;
	}

	public void setBrandOemName(String brandOemName) {
		this.brandOemName = brandOemName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getInstallationDate() {
		return installationDate;
	}

	public void setInstallationDate(String installationDate) {
		this.installationDate = installationDate;
	}

	public String getIpAdd() {
		return ipAdd;
	}

	public void setIpAdd(String ipAdd) {
		this.ipAdd = ipAdd;
	}

	public Integer getPortNo() {
		return portNo;
	}

	public void setPortNo(Integer portNo) {
		this.portNo = portNo;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "LineInformation [uuid=" + uuid + ", locCode=" + locCode + ", lineNo=" + lineNo + ", lineName="
				+ lineName + ", equipmentType=" + equipmentType + ", brandOemName=" + brandOemName + ", modelNo="
				+ modelNo + ", serialNo=" + serialNo + ", macId=" + macId + ", installationDate=" + installationDate
				+ ", ipAdd=" + ipAdd + ", portNo=" + portNo + ", createTime=" + createTime + "]";
	}

}

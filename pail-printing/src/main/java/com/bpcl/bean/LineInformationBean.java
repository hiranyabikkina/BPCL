
package com.bpcl.bean;

public class LineInformationBean {
	private String uuid;
	private Integer locCode;
	private String lineId;
	private String lineName;
	private String equipmentType;
	private String brandOemName;
	private Integer modelNo; 
	private String serialNo;	
	private String macId;
	private String installationDate;
	private String ipAdd;
	private String portNo;
	
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
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
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
	public Integer getModelNo() {
		return modelNo;
	}
	public void setModelNo(Integer modelNo) {
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
	
	
	public String getPortNo() {
		return portNo;
	}
	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}
	@Override
	public String toString() {
		return "LineInformationBean [uuid=" + uuid + ", locCode=" + locCode + ", lineId=" + lineId + ", lineName="
				+ lineName + ", equipmentType=" + equipmentType + ", brandOemName=" + brandOemName + ", modelNo="
				+ modelNo + ", serialNo=" + serialNo + ", macId=" + macId + ", installationDate=" + installationDate
				+ ", ipAdd=" + ipAdd + "]";
	}
	
	
}

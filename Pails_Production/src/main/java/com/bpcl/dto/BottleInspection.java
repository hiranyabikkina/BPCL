package com.bpcl.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class BottleInspection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5534922515280740415L;

	private String uuid;

	private String bottleCode;

	private String plantLocation;

	private String lineUuid;

	private Timestamp createdTime;

	private Integer status;

	/**
	 * Setter and Getter method
	 * 
	 * @return
	 */

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getBottleCode() {
		return bottleCode;
	}

	public void setBottleCode(String bottleCode) {
		this.bottleCode = bottleCode;
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

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

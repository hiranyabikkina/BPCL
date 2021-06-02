package com.bpcl.bean;

import java.util.List;

public class ResProductionData {

	private Integer status;

	private String message;

	private String timeStamp;

	private List<PayloadProdLine> payload;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<PayloadProdLine> getPayload() {
		return payload;
	}

	public void setPayload(List<PayloadProdLine> payload) {
		this.payload = payload;
	}

}
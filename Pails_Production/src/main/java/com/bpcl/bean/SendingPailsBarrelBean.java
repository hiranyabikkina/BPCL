package com.bpcl.bean;

import java.util.List;

public class SendingPailsBarrelBean {
	
	private Integer planId;
	
	private String locCode;

	private String lineNo;

	private String caseBarcode;

	private String batchNo;

	private String prodCode;

	private String prodName;

	private String prodSize;

	private String packType;
	
	private List<PackDetails> packDetails;

	
	
	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineId) {
		this.lineNo = lineId;
	}

	public String getCaseBarcode() {
		return caseBarcode;
	}

	public void setCaseBarcode(String caseBarcode) {
		this.caseBarcode = caseBarcode;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdSize() {
		return prodSize;
	}

	public void setProdSize(String prodSize) {
		this.prodSize = prodSize;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	public List<PackDetails> getPackDetails() {
		return packDetails;
	}

	public void setPackDetails(List<PackDetails> packDetails) {
		this.packDetails = packDetails;
	}

	@Override
	public String toString() {
		return "SendingProducerSecondaryPackageDetailBean [locCode=" + locCode + ", lineId=" + lineNo + ", caseBarcode="
				+ caseBarcode + ", batchNo=" + batchNo + ", prodCode=" + prodCode + ", prodName=" + prodName
				+ ", prodSize=" + prodSize + ", packType=" + packType + ", packDetails=" + packDetails + "]";
	}

}

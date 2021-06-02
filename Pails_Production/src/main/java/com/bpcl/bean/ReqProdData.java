package com.bpcl.bean;

public class ReqProdData {

	private Integer productionLineId;

	private String lineNo;

	private Integer locCode;

	public Integer getProductionLineId() {
		return productionLineId;
	}

	public void setProductionLineId(Integer productionLineId) {
		this.productionLineId = productionLineId;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public Integer getLocCode() {
		return locCode;
	}

	public void setLocCode(Integer locCode) {
		this.locCode = locCode;
	}

	@Override
	public String toString() {
		return "RequestProductionData [productionLineId=" + productionLineId + ", lineNo=" + lineNo + ", locCode="
				+ locCode + "]";
	}

}

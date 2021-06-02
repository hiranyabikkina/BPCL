
package com.bpcl.bean;

public class PrinterStateBean {
	private Integer uuid;
	private String message;
	private String size;	
	private Integer issued;
	private Integer print;
	private Integer inspect;
	/**
	 * @return the uuid
	 */
	public Integer getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the issued
	 */
	public Integer getIssued() {
		return issued;
	}
	/**
	 * @param issued the issued to set
	 */
	public void setIssued(Integer issued) {
		this.issued = issued;
	}
	/**
	 * @return the print
	 */
	public Integer getPrint() {
		return print;
	}
	/**
	 * @param print the print to set
	 */
	public void setPrint(Integer print) {
		this.print = print;
	}
	/**
	 * @return the inspect
	 */
	public Integer getInspect() {
		return inspect;
	}
	/**
	 * @param inspect the inspect to set
	 */
	public void setInspect(Integer inspect) {
		this.inspect = inspect;
	}
	@Override
	public String toString() {
		return "PrinterStateBean [uuid=" + uuid + ", message=" + message + ", size=" + size + ", issued=" + issued
				+ ", print=" + print + ", inspect=" + inspect + "]";
	}
	
	
	
}

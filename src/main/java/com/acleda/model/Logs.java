package com.acleda.model;

public class Logs {
	
	private int logId;
	private int staffId;
	private String action;
	private String description;
	private String serviceNameString;
	public int getLogId() {
		return logId;
	}
	
	public Logs(int staffId, String action, String description, String serviceNameString) {
		super();
		this.staffId = staffId;
		this.action = action;
		this.description = description;
		this.serviceNameString = serviceNameString;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getServiceNameString() {
		return serviceNameString;
	}
	public void setServiceNameString(String serviceNameString) {
		this.serviceNameString = serviceNameString;
	}
	
	
	
}

package com.acleda.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LeaveType {
  private int leaveTypeId;
  
  @NotBlank(message = "LeaveType name is require")
  @Size(max = 100 , message = "LeaveType name must be less than 100 characters ")
  private String leaveTypeName;
  private String createdAt;
  private String createdBy;
  private String updatedAt;
  private String updatedBy;
  private String deletedAt;
  private String deletedBy;
  public int getLeaveTypeId() {
    return leaveTypeId;
  }
  public void setLeaveTypeId(int leaveTypeId) {
    this.leaveTypeId = leaveTypeId;
  }
  public String getLeaveTypeName() {
    return leaveTypeName;
  }
  public void setLeaveTypeName(String leaveTypeName) {
    this.leaveTypeName = leaveTypeName;
  }
  public String getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
  public String getCreatedBy() {
    return createdBy;
  }
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }
  public String getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
  public String getUpdatedBy() {
    return updatedBy;
  }
  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }
  public String getDeletedAt() {
    return deletedAt;
  }
  public void setDeletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
  }
  public String getDeletedBy() {
    return deletedBy;
  }
  public void setDeletedBy(String deletedBy) {
    this.deletedBy = deletedBy;
  }
  
}

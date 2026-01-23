package com.acleda.model;

import java.time.LocalDateTime;

public class LeaveRequest {
    private int LeaveRequestId;
    private String reason;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int userId;
    private int leaveTypeId;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt; 
    private String updatedBy;
    public int getLeaveRequestId() {
        return LeaveRequestId;
    }
    public void setLeaveRequestId(int leaveRequestId) {
        LeaveRequestId = leaveRequestId;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getLeaveTypeId() {
        return leaveTypeId;
    }
    public void setLeaveTypeId(int leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
}

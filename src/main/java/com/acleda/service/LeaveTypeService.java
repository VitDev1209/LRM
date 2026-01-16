package com.acleda.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acleda.dao.LeaveTypeDao;
import com.acleda.model.LeaveType;

@Service
public class LeaveTypeService {

  @Autowired
  private LeaveTypeDao dao;

  private final Logger logger = LoggerFactory.getLogger(LeaveTypeService.class);

  public void insertLeaveType(LeaveType leaveType) {
    if (leaveType == null) {
      logger.warn("insert[3001] leaveType is null");
      throw new IllegalArgumentException("leaveType data cannot be null");
    }
    try {
      dao.insert(leaveType);
    } catch (Exception e) {
      logger.error("leaveType[3001]" + e.getMessage(), e);
    }
  }

  public List<LeaveType> getLeaveType() {
    try {
      List<LeaveType> leaveType = dao.getLeaveType();
      if (leaveType == null) {
        logger.warn("getLeaveType[3002] leave type is null");
        throw new IllegalArgumentException("leaveType data cannot be null");
      }
      return leaveType;
    } catch (Exception e) {
      logger.error("getLeaveType[3002]" + e.getMessage(), e);
      return null;
    }
  }
  
  public LeaveType getLeaveTypeById(int id) {
    if(id < 0) {
      logger.warn("getLeaveTypeById[3003] id is < 0");
    }
    try {
      LeaveType leaveType = dao.getLeaveTypeById(id);
      return leaveType;
    } catch (Exception e) {
      logger.error("getLeaveTypeById[3003]" + e.getMessage() , e);
      return null;
    }
  }
  
  public void updateLeaveType(LeaveType leaveType) {
    if(leaveType == null ) {
      logger.warn("update[3004] leaveType is null");
      throw new IllegalArgumentException("leaveType data cannot be null");
    }
    try {
      dao.update(leaveType);
    } catch (Exception e) {
      logger.error("leaveType[3004]" + e.getMessage() , e);
    }
  }
  
  public void deleteLeaveType(int id) {
    if(id < 0) {
      logger.warn("getLeaveTypeById[3005] id is < 0");
    }
    try {
      dao.delete(id);
    } catch (Exception e) {
      logger.error("deleteLeaveType[3005]" + e.getMessage() , e);
    }
  }

}

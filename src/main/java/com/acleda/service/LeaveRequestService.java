package com.acleda.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acleda.dao.LeaveRequestDao;
import com.acleda.dao.LogDao;
import com.acleda.model.LeaveRequest;
import com.acleda.model.Logs;

@Service
public class LeaveRequestService {
    private final Logger logger = LoggerFactory.getLogger(LeaveRequestService.class);

    @Autowired
    private LeaveRequestDao leaveRequestDao;

    @Autowired
    private LogDao logDao;

    public void insertLeaveRequest(LeaveRequest leaveRequest){
        if(leaveRequest ==null ){
            logger.warn("insertLeaveRequest[3001] null");
        }
        try {
            leaveRequestDao.insertLeaveRequest(leaveRequest);
            Logs logs = new Logs(0, "Insert", "Leave Request", "insertLeaveRequest[3001]");
            logDao.insertLog(logs);
            logger.info("insertLeaveRequest[3001] insert leave request successfully");
        } catch (Exception e) {
            logger.error("insertLeaveRequest[3001]"+ e.getMessage() , e);
        }
    }

    public void updateLeaveRequest(LeaveRequest leaveRequest){
        if(leaveRequest ==null ){
            logger.warn("updateLeaveRequest[3002] null");
        }
        try {
            leaveRequestDao.updateLeaveRequest(leaveRequest);
            Logs logs = new Logs(0, "Update", "Leave Request", "updateLeaveRequest[3002]");
            logDao.insertLog(logs);
            logger.info("updateLeaveRequest[3001] update leave request successfully");
        } catch (Exception e) {
            logger.error("updateLeaveRequest[3002]"+ e.getMessage() , e);
        }
    }

    public LeaveRequest getLeaveRequestById(int id){
        if(id <0){
            logger.warn("getLeaveRequestById[3003] id < 0");
        }
        try {
            LeaveRequest leaveRequest = leaveRequestDao.getLeaveRequestById(id);
            return leaveRequest;
        } catch (Exception e) {
            logger.error("updateLeaveRequest[3002]"+ e.getMessage() , e);
            return null;
        }
    }
    
    public List<LeaveRequest> getLeaveRequests(LeaveRequest leaveRequest){
    	if(leaveRequest == null) {
    		logger.warn("getLeaveRequests[3003] leave request is null");
    	}
        try {
            List<LeaveRequest> leaveRequests= leaveRequestDao.getAllLeaveRequests(leaveRequest);
            return leaveRequests;
        } catch (Exception e) {
            logger.error("" +e.getMessage() , e);
            return null;
        }
    }


}

package com.acleda.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acleda.model.LeaveRequest;

@Repository
public class LeaveRequestDao {
    private final Logger logger = LoggerFactory.getLogger(LeaveRequestDao.class);

    @Autowired
    private DataSource dataSource;

    public void insertLeaveRequest(LeaveRequest leaveRequest){
        try(
            Connection conn = dataSource.getConnection();
            CallableStatement statement = conn.prepareCall("CALL PRBE_INSERT_LEAVE_REQUEST(?,?,?,?,?,?)");
        ){
            statement.setString(1, leaveRequest.getReason());
            statement.setString(2, leaveRequest.getStartDate().toString());
            statement.setString(3, leaveRequest.getEndDate().toString());
            statement.setInt(4, leaveRequest.getUserId());
            statement.setInt(5, leaveRequest.getLeaveRequestId());
            statement.setString(6, "");
            statement.execute();
        }catch (Exception e) {
            logger.error("insertLeaveRequest[2001]" + e.getMessage() , e);
        }
    }

    public boolean updateLeaveRequest(LeaveRequest leaveRequest){
        try(
            Connection conn = dataSource.getConnection();
            CallableStatement statement = conn.prepareCall("CALL PRBE_UPDATE_LEAVE_REQUEST(?,?,?,?,?,?,?,?)");
        ){
            statement.setInt(1, leaveRequest.getLeaveRequestId());
            statement.setString(2, leaveRequest.getReason());
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(5, leaveRequest.getUserId());
            statement.setInt(6, leaveRequest.getLeaveRequestId());
            statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(8, "");
            statement.execute();
            return true;
        }catch(Exception e){
            logger.error("updateLeaveRequest[2002]"+ e.getMessage() , e);
            return false;
        }
    }

    public LeaveRequest getLeaveRequestById(int id){
        try(
            Connection conn = dataSource.getConnection();
            CallableStatement statement = conn.prepareCall("CALLPRBE_GET_LEAVE_REQUEST_BY_ID(?,?)");
        ){
            statement.setInt(1, id);
            statement.registerOutParameter(2, Types.REF_CURSOR);
            statement.execute();
            ResultSet rs = (ResultSet) statement.getObject(2);
            rs.next();
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setLeaveRequestId(leaveRequest.getLeaveRequestId());
            leaveRequest.setReason(leaveRequest.getReason());
            leaveRequest.setStartDate(leaveRequest.getStartDate());
            leaveRequest.setEndDate(leaveRequest.getEndDate());
            leaveRequest.setUserId(leaveRequest.getUserId());
            leaveRequest.setLeaveTypeId(leaveRequest.getLeaveTypeId());
            leaveRequest.setCreatedBy(leaveRequest.getCreatedBy());
            leaveRequest.setUpdatedAt(leaveRequest.getUpdatedAt());
            leaveRequest.setUpdatedBy(leaveRequest.getUpdatedBy());
            rs.close();
            return leaveRequest;
        }catch(Exception e){
            logger.error("getLeaveRequestById[2002]"+e.getMessage() ,e);
            return null;
        }
    }


    public List<LeaveRequest> getAllLeaveRequests(LeaveRequest leaveRequest){
        try(
            Connection conn = dataSource.getConnection();
            CallableStatement statement = conn.prepareCall("PRBE_GET_LEAVE_REQUEST(?)");
        ){
            statement.registerOutParameter(1, Types.REF_CURSOR);
            statement.execute();
            ResultSet rs = (ResultSet) statement.getObject(1);
            List<LeaveRequest> leaveRequests = new ArrayList<LeaveRequest>();
            while (rs.next()) {
                LeaveRequest request = new LeaveRequest();
                request.setLeaveRequestId(rs.getInt(1));
                request.setReason(rs.getString(2));
                request.setStartDate(rs.getTimestamp(3).toLocalDateTime());
                request.setEndDate(rs.getTimestamp(4).toLocalDateTime());
                request.setUserId(rs.getInt(5));
                request.setLeaveTypeId(rs.getInt(6));
                request.setCreatedAt(rs.getTimestamp(7).toLocalDateTime());
                request.setCreatedBy(rs.getString(8));
                request.setUpdatedAt(rs.getTimestamp(9).toLocalDateTime());
                request.setUpdatedBy(rs.getString(10));
                leaveRequests.add(request);
            }
            rs.close();
            return leaveRequests;
        }catch(Exception e){
            logger.error("getAllLeaveRequests[2003]" + e.getMessage() ,e);
            return null;
        }
    }

    public boolean deletenLeaveRequest(int id){
        try(
            Connection conn = dataSource.getConnection();
            CallableStatement statement = conn.prepareCall("CALL PRBE_DELETE_LEAVE_REQUEST(?)");
        ){
            statement.setInt(1, id);
            statement.execute();
            return true;
        }catch(Exception e){
            logger.error("deletenLeaveRequest[2004]" + e.getMessage() , e );
            return false;
        }
    }

}

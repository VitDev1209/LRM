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

import com.acleda.model.LeaveType;

@Repository
public class LeaveTypeDao {
  @Autowired
  private DataSource dataSource;

  private final Logger logger = LoggerFactory.getLogger(LeaveTypeDao.class);

  public void insert(LeaveType leaveType) {
    try (Connection conn = dataSource.getConnection();
        CallableStatement statement = conn.prepareCall("CALL PRBE_INSERT_LEAVE_TYPE(?,?)");) {
      statement.setString(1, leaveType.getLeaveTypeName());
      statement.setString(2, "");
      statement.execute();
    } catch (Exception e) {
      logger.error("insert[2001]" + e.getMessage(), e);
    }
  }

  public List<LeaveType> getLeaveType() {
    try (Connection conn = dataSource.getConnection();
        CallableStatement statement = conn.prepareCall("CALL PRBE_GET_LEAVE_TYPE(?)");) {
      statement.registerOutParameter(1, Types.REF_CURSOR);
      statement.execute();
      final ResultSet rs = (ResultSet) statement.getObject(1);
      final List<LeaveType> leaveTypes = new ArrayList<LeaveType>();
      while (rs.next()) {
        LeaveType leaveType = new LeaveType();
        leaveType.setLeaveTypeId(rs.getInt(1));
        leaveType.setLeaveTypeName(rs.getString(2));
        leaveType.setCreatedAt(rs.getString(3));
        leaveType.setCreatedBy(rs.getString(4));
        leaveType.setUpdatedAt(rs.getString(5));
        leaveType.setUpdatedBy(rs.getString(6));
        leaveType.setDeletedAt(rs.getString(7));
        leaveType.setDeletedBy(rs.getString(8));
        leaveTypes.add(leaveType);
      }
      rs.close();
      return leaveTypes;
    } catch (Exception e) {
      logger.error("getLeaveType[2002]" + e.getMessage(), e);
      return null;
    }
  }

  public LeaveType getLeaveTypeById(int id) {
    try (Connection conn = dataSource.getConnection();
        CallableStatement statement = conn.prepareCall("CALL PRBE_GET_LEAVE_TYPE_BY_ID(?,?)");) {
      statement.setInt(1, id);
      statement.registerOutParameter(2, Types.REF_CURSOR);
      statement.execute();
      ResultSet rs = (ResultSet) statement.getObject(2);
      rs.next();
      LeaveType leaveType = new LeaveType();
      leaveType.setLeaveTypeId(rs.getInt(1));
      leaveType.setLeaveTypeName(rs.getString(2));
      leaveType.setCreatedAt(rs.getString(3));
      leaveType.setCreatedBy(rs.getString(4));
      leaveType.setUpdatedAt(rs.getString(5));
      leaveType.setUpdatedBy(rs.getString(6));
      leaveType.setDeletedAt(rs.getString(7));
      leaveType.setDeletedBy(rs.getString(8));
      rs.close();
      return leaveType;
    } catch (Exception e) {
      logger.error("getLeaveTypeById[2003]" + e.getMessage(), e);
      return null;
    }
  }

  public boolean update(LeaveType leaveType) {
    try (Connection conn = dataSource.getConnection();
        CallableStatement statement = conn.prepareCall("CALL PRBE_UPDATE_LEAVE_TYPE(?,?,?,?)");) {
      statement.setInt(1, leaveType.getLeaveTypeId());
      statement.setString(2, leaveType.getLeaveTypeName());
      statement.setString(3, "");
      statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
      statement.execute();
      return true;
    } catch (Exception e) {
      logger.error("update[2004]" + e.getMessage(), e);
      return false;
    }
  }

  public boolean delete(int id) {
    try (Connection conn = dataSource.getConnection();
        CallableStatement statement = conn.prepareCall("CALL PRBE_DELETE_LEAVE_TYPE(?)");) {
      statement.setInt(1, id);
      statement.execute();
      return true;
    } catch (Exception e) {
      logger.error("delete[2005]" + e.getMessage(), e);
      return false;
    }
  }

}

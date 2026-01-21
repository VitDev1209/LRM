package com.acleda.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acleda.model.Staff;

import dto.StaffViewDTO;

@Repository
public class StaffDao {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(StaffDao.class);

	@Autowired
	private DataSource dataSource;

	public List<?> Get_Staff() {

		final String sql = "{CALL PR_GET_STAFF(?)}";
		List<StaffViewDTO> staffs = new ArrayList<StaffViewDTO>();

		try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.registerOutParameter(1, Types.REF_CURSOR);
			stmt.execute();

			try (ResultSet rs = (ResultSet) stmt.getObject(1)) {

				while (rs.next()) {
					StaffViewDTO staff = new StaffViewDTO();
					staff.setId(rs.getInt("ID"));
					staff.setFullName(rs.getString("FULLNAME"));
					staff.setUserName(rs.getString("USERNAME"));
					staff.setPassword(rs.getString("PASSWORD"));
					staff.setRoleName(rs.getString("ROLE_NAME"));
					staff.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
					staff.setCreated_at(rs.getTimestamp("CREATED_AT"));
					staff.setUpdated_at(rs.getTimestamp("UPDATED_AT"));

					staffs.add(staff);
				}

			}

		} catch (SQLException e) {
			logger.error("Get_Staff[2001]", e.getMessage(), e);
		}

		return staffs;

	}

	public StaffViewDTO getStaffById(Integer id) {

		final String sql = "{ CALL PR_GET_STAFF_byID (?,?) }";
		StaffViewDTO staff = new StaffViewDTO();
		try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {

			stmt.setInt(1, id);
			stmt.registerOutParameter(2, Types.REF_CURSOR);

			try (ResultSet rs = (ResultSet) stmt.getObject(2)) {

				if (rs.next()) {
					staff.setId(rs.getInt("ID"));
					staff.setUserName(rs.getString("FullNAME"));
					staff.setFullName(rs.getString("FullNAME"));
					staff.setPassword(rs.getString("PASSWORD"));
					staff.setRoleName(rs.getString("ROLE_NAME"));
					staff.setDepartmentName(rs.getString("NAME"));
					staff.setCreated_at(rs.getTimestamp("CREATED_AT"));
					staff.setUpdated_at(rs.getTimestamp("UPDATED_AT"));
				}

			}

		} catch (SQLException e) {
			logger.error("getStaffById[2001]", e.getMessage(), e);
		}
		return staff;
	}
	

	public void editStaff(Staff staff) {
		final String sql = "{ CALL PR_Edit_Staff(?,?,?,?,?,?) }";
		try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(sql);) {
			stmt.setInt(1, staff.getId());
			stmt.setString(2, staff.getFullName());
			stmt.setString(3, staff.getUserName());
			stmt.setString(4, staff.getPassword());
			stmt.setString(5, staff.getRole_id());
			stmt.setString(6, staff.getDepartment_id());

			stmt.execute();
			conn.commit();

		} catch (SQLException e) {
			logger.error("editStaff[2001]", e.getMessage(), e);
		}
	}

	public void delStaff(Integer id) {

		final String sql = " CALL PR_DEL_STAFF (?) ";
		try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(sql);) {
			stmt.setInt(1, id);
			stmt.execute();
			conn.commit();

		} catch (SQLException e) {

			logger.error("delStaff[2001]", e.getMessage(), e);
		}

	}
	

}

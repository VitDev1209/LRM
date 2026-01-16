package com.acleda.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acleda.model.Department;

import oracle.jdbc.OracleTypes;

@Repository
public class DepartmentDao {

	private final Logger logger = LoggerFactory.getLogger(DepartmentDao.class);

	@Autowired
	private DataSource dataSource;

	public List<Department> listDepartments() {
		List<Department> list = new ArrayList<>();
		final String sql = "{CALL PR_Get_Department(?)}";
		try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			try (ResultSet resultSet = (ResultSet) stmt.getObject(1)) {
				while (resultSet.next()) {
					Department department = new Department();
					department.setId(resultSet.getInt("ID"));
					department.setName(resultSet.getString("NAME"));
					department.setAbbreviation(resultSet.getString("ABBREVIATION"));
					department.setCreated_at(resultSet.getTimestamp("CREATED_AT"));
					list.add(department);
				}
			}
		} catch (SQLException se) {
			try {
				dataSource.getConnection().rollback();
			} catch (SQLException rollbackSE) {
				logger.error("listDepartments[2001]", rollbackSE);
			}
			logger.error("listDepartments[2001]", se.getMessage(), se);
		}
		return list;
	}

	public Department getDepartment(Integer id) {

		Department department = new Department();

		final String sql = "{CALL PR_Get_One_Department(?,?)}";
		try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {

			stmt.setInt(1, id);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();

			try (ResultSet rs = (ResultSet) stmt.getObject(2)) {
				if (rs.next()) {
					department.setId(rs.getInt("ID"));
					department.setName(rs.getString("NAME"));
					department.setAbbreviation(rs.getString("ABBREVIATION"));
					department.setCreated_at(rs.getTimestamp("CREATED_AT"));
					department.setCreated_by(rs.getString("CREATED_BY"));

				}
			}

		} catch (SQLException se) {
			try {
				dataSource.getConnection().rollback();
			} catch (SQLException rollbaackSE) {
				logger.error("getDepartment[2001]", rollbaackSE.getMessage(), rollbaackSE);
			}
		}
		return department;
	}

	public void addDepartment(Department department) {
		
		String sql = "{ CALL PR_INSERT_Department(?,?) }";

		try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
			stmt.setString(1, department.getName());
			stmt.setString(2, department.getAbbreviation());
			stmt.execute();
			conn.commit();

		} catch (SQLException e) {
			try {
				dataSource.getConnection().rollback();
			} catch (SQLException se) {
				logger.error("addDepartment[2001]", se.getMessage(), se);
			}
		}

	}

	public void rmDepartment(Integer id) {
		String sql = "{CALL PR_del_Department(?)}";
		try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
			stmt.setInt(1, id);
			stmt.execute();
			conn.commit();

		} catch (SQLException e) {
			try {
				dataSource.getConnection().rollback();
			} catch (SQLException se) {
				logger.error("addDepartment[2001]", se.getMessage(), se);
			}
		}

	}

	public void updateDepartment(Department department) {
		String sql = "{CALL PR_Update_Department (?,?,?)}";
		try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {

			stmt.setString(1, department.getName());
			stmt.setString(2, department.getAbbreviation());
			stmt.setInt(3, department.getId());
			stmt.execute();
		} catch (SQLException se) {
			try {
				dataSource.getConnection().rollback();
			} catch (SQLException e) {
				logger.error("updateDepartment[2001]", e.getMessage(), e);
			}
		}
	}
}

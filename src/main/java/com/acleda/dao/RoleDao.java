package com.acleda.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acleda.model.Role;

import oracle.jdbc.OracleTypes;


@Repository
public class RoleDao {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(RoleDao.class);

    @Autowired
    private DataSource dataSource;

    public List<Role> getAllRole() {
        List<Role> list = new ArrayList<>();
        final String sql = "{CALL PR_Get_All_Role(?)}";

        try (
            Connection conn = dataSource.getConnection();
            CallableStatement stmt = conn.prepareCall(sql)
        ) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();

            try (ResultSet resultSet = (ResultSet) stmt.getObject(1)) {
                while (resultSet.next()) {
                    Role role = new Role();
                    role.setRoleId(resultSet.getInt("roleId"));
                    role.setRoleName(resultSet.getString("roleName"));
                    role.setDescription(resultSet.getString("description"));
                    role.setCreatedAt(resultSet.getString("createdAt"));
                    role.setCreatedBy(resultSet.getString("createdBy"));
                    role.setDeletedAt(resultSet.getString("deletedAt"));
                    role.setDeletedBy(resultSet.getString("deletedBy"));
                    role.setUpdatedAt(resultSet.getString("updatedAt"));
                    role.setUpdatedBy(resultSet.getString("updatedBy"));

                    list.add(role);
                }
            }

        } catch (SQLException e) {
            logger.error("getAllRole[2003]", e);
        }

        return list;
    }
}

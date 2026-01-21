package com.acleda.dao;

import java.sql.CallableStatement;
import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acleda.model.Logs;

@Repository
public class LogDao {
	private final Logger logger = LoggerFactory.getLogger(LogDao.class);
	
	@Autowired
	private DataSource dataSource;
	
	public void insertLog(Logs logs) {
		try(
				Connection conn = dataSource.getConnection();
				CallableStatement statement = conn.prepareCall("CALL PRBE_INSERT_LOG(?,?,?,?)");
		){
			statement.setInt(1, 0);
			statement.setString(2, logs.getAction());
			statement.setString(3, logs.getDescription());
			statement.setString(4, logs.getServiceNameString());
			statement.execute();
			
			logger.info("Log inserted successfully: " + logs.getAction());
		}
		catch (Exception e) {
			logger.error("insertLog[2001]" + e.getMessage() , e);
		}
	}
}

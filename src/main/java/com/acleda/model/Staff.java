package com.acleda.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
public class Staff{
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "userName")
	@NotNull(message = "Username is required.")
	private String userName;
	
	@Column(name = "password")
	@NotNull(message = "Password is required.")
	private String password;
	
	@Column(name = "role_id")
	@NotNull(message = "Role ID is required.")
	private String role_id;
	
	@Column(name = "department_id")
	@NotNull(message = "Department ID is requried.")
	private String department_id;
	
	@Column(name = "created_at")
	private Timestamp created_at;

	@Column(name = "created_by")
	private String created_by;

	@Column(name = "updated_at")
	private Timestamp updated_at;

	@Column(name = "updatd_by")
	private String updatd_by;

	@Column(name = "deleted_at")
	private Timestamp deleted_at;

	@Column(name = "deleted_by")
	private String deleted_by;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public String getUpdatd_by() {
		return updatd_by;
	}

	public void setUpdatd_by(String updatd_by) {
		this.updatd_by = updatd_by;
	}

	public Timestamp getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Timestamp deleted_at) {
		this.deleted_at = deleted_at;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}
	
	
	
	
}
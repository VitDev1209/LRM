package com.acleda.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Table
public class Department {

	@Column(name = "id")
	@Id
	private Integer id;

	@Column(name = "name")
	@NotNull(message = "Name is required")
	private String name;

	@Column(name = "abbreviation")
	@NotNull(message = "Abbreviation is requireed")
	@Max(10)
	private String abbreviation;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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

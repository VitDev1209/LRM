package com.acleda.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acleda.dao.StaffDao;
import com.acleda.model.Staff;

import dto.StaffViewDTO;

@Service
public class StaffService {

	@Autowired
	private StaffDao staffDao;

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(StaffDao.class);

	private void validateStaff(Staff staff) {

		if (staff == null || staff.getId() == 0 || staff.getId() < 0) {
			throw new IllegalArgumentException("Invalid Data");
		}
		requirement(staff.getFullName(), "FullName");
		requirement(staff.getUserName(), "Username");
		requirement(staff.getPassword(), "Password");
		requirement(staff.getRole_id(), "Role");
		requirement(staff.getDepartment_id(), "Department");
	}

	private void requirement(String val, String fieldName) {
		if (val == null) {
			throw new IllegalArgumentException(fieldName + "is required");
		}
	}

	public List<?> Get_Staff() {
		return staffDao.Get_Staff();
	}

	public StaffViewDTO getStaffById(Integer id) {
		if (id <= 0 || id == null) {
			logger.error("ID is required.");
		}
		return staffDao.getStaffById(id);
	}

	public void editStaff(Staff staff) {
		validateStaff(staff);
		staffDao.editStaff(staff);
	}

	public void delStaff(Integer id) {
		if (id <= 0 || id == null) {
			logger.error("ID is required.");
		}
		staffDao.delStaff(id);
	}

}

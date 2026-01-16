package com.acleda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acleda.dao.DepartmentDao;
import com.acleda.model.Department;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	public List<Department> listDepartment() {
		return departmentDao.listDepartments();
	}
	
	public Department getDepartment(Integer id) {
		if(id<=0 || id==null) {
			return null;
		}
		return departmentDao.getDepartment(id);
	}
	
	public void addDepartment(Department department) {
		if(department == null) {
			throw new IllegalArgumentException("Department cannot be null");
		}
		departmentDao.addDepartment(department);
	}
	
	
	public void updateDepartment(Department department) {
		
		if(department == null) {
			throw new IllegalArgumentException("Department cannot be null");
		}
		
		departmentDao.updateDepartment(department);
	}
	
	
	public void delDepartment(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid department ID");
        }

		departmentDao.rmDepartment(id);
	}
	
	
	

}

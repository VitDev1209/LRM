package com.acleda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acleda.dao.RoleDao;
import com.acleda.model.Role;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;
	//private final Logger logger = LoggerFactory.getLogger(RoleService.class);	
	public List<Role> listRole(){
//		List<Role> roles = roleDao.getAllRole();
		return roleDao.getAllRole();
	}
	
//	public Role getAllRole(Integer id) {
//		if(id<=0 || id==null) {
//			return null;
//		}
//		return roleDao.getAllRole(id);
//	}
}

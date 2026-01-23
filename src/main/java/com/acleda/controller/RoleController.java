package com.acleda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acleda.model.Role;
import com.acleda.service.RoleService;
import java.util.List;

@Controller 
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/list")
	public String role(Model model) {
		List<Role> roleList = roleService.listRole();
		model.addAttribute("roles", roleList);
		System.out.print("role ");
		return "Role/viewRole";
	}
	
}

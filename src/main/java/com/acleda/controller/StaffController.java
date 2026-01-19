package com.acleda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acleda.model.Staff;
import com.acleda.service.StaffService;

import dto.StaffViewDTO;

@Controller
@RequestMapping("/Staff")
public class StaffController {
	
	@Autowired
	private StaffService staffservice;
	
	@GetMapping("/list_Staff")
	public String Get_Staff(Model model){
		List<?> list = staffservice.Get_Staff();
		model.addAttribute("staffs",list);	
		return "Staff/index";
	}
	
	@GetMapping("/list_Staff_ById/{id}")
	public String getStafById(@PathVariable Integer id,Model model) {
		StaffViewDTO staffViewDTO = new StaffViewDTO();
		staffViewDTO=staffservice.getStaffById(id);
		model.addAttribute(staffViewDTO);
		return "Staff/index";
	}
	
	
	@PutMapping("/edit_Staff")
	public void editStaff(Staff staff) {	
		staffservice.editStaff(staff);
	}
	
	@DeleteMapping("/delStaff/{id}")
	public void delStaff(@PathVariable Integer id) {
		staffservice.delStaff(id);
	}
	
}

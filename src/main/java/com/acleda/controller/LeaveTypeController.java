package com.acleda.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acleda.model.LeaveType;
import com.acleda.service.LeaveTypeService;

@Controller
@RequestMapping("/leaveType")
public class LeaveTypeController extends BaseController {

	@Autowired
	private LeaveTypeService leaveTypeService;

	@GetMapping("/list")
	public String listLeaveType(Model model) {
		List<LeaveType> leaveTypes = leaveTypeService.getLeaveType();
		model.addAttribute("leaveType", leaveTypes);
		return "LeaveType/listLeaveType";
	}

	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("leaveType", new LeaveType());
		return "LeaveType/addLeaveType";
	}

	@PostMapping("/add")
	public String insert(@Valid @ModelAttribute LeaveType leaveType, BindingResult bindingResult, RedirectAttributes ra,
			Model model) {
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getFieldError().getDefaultMessage();
			sendWarning(model, errorMessage);
			return "LeaveType/addLeaveType";
		}
		try {
			Thread.sleep(1500);
			leaveTypeService.insertLeaveType(leaveType);
			return "redirect:/leaveType/list?status=success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/leaveType/list?status=error";
		}
	}

	@GetMapping("/edit")
	public String editForm(@RequestParam("id") int id, Model model) {
		LeaveType leaveTypes = leaveTypeService.getLeaveTypeById(id);
		model.addAttribute("leaveType", leaveTypes);
		return "LeaveType/editLeaveType";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute LeaveType leaveType) {
		leaveTypeService.updateLeaveType(leaveType);
		return "redirect:/leaveType/list?status=success";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id , RedirectAttributes ra) {
		leaveTypeService.deleteLeaveType(id);
		return "redirect:/leaveType/list?status=success";
	}

}

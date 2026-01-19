package com.acleda.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acleda.model.LeaveType;
import com.acleda.service.LeaveTypeService;

@Controller
@RequestMapping("/leaveType")
public class LeaveTypeController {

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
  public String insert(@Valid @ModelAttribute LeaveType leaveType) {
    leaveTypeService.insertLeaveType(leaveType);
    return "redirect:/leaveType/list";
  }

  @GetMapping("/edit")
  public String editForm(@RequestParam("id") int id , Model model) {
    LeaveType leaveTypes = leaveTypeService.getLeaveTypeById(id);
    model.addAttribute("leaveType", leaveTypes);
    return "LeaveType/editLeaveType";
  }
  
  @PostMapping("/update")
  public String update(@Valid @ModelAttribute LeaveType leaveType) {
    leaveTypeService.updateLeaveType(leaveType);
    return "redirect:/leaveType/list";
  }
  
  @GetMapping("/delete")
  public String delete(@RequestParam("id") int id) {
    leaveTypeService.deleteLeaveType(id);
    return "redirect:/leaveType/list";
  }

}

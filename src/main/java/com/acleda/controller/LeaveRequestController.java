package com.acleda.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acleda.model.LeaveRequest;
import com.acleda.service.LeaveRequestService;

@Controller
@RequestMapping("/leaveRequest")
public class LeaveRequestController extends BaseController {
    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping("/insert")
    public String insertForm(Model model){
        model.addAttribute("leaveRequest", new LeaveRequest());
        return "LeaveRequest/addLeaveRequest";
    }

    @PostMapping("/insert")
    public String insertSub(@ModelAttribute @Valid LeaveRequest leaveRequest , 
        BindingResult result , RedirectAttributes ra , Model model){
        if(result.hasErrors()){
            String erroMessage = result.getFieldError().getDefaultMessage();
            this.sendWarning(model, erroMessage);
            return "LeaveRequest/addLeaveRequest";
        }else{
            try {
                Thread.sleep(1500L);
                this.leaveRequestService.insertLeaveRequest(leaveRequest);
                return "redirect:/leaveRequest/list?=status=success";
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/leaveRequest/list?status=error";
            }
        }
    }

    @GetMapping("/editForm")
    public String editForm(@PathVariable int id , Model model){
    	LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestById(id);
        model.addAttribute("leaveRequest", leaveRequest);
    	return "LeaveRequest/editLeaveRequest";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute LeaveRequest leaveRequest , BindingResult result , Model model , RedirectAttributes ra){
        if(result.hasErrors()){
            String errorMessage = result.getFieldError().getDefaultMessage();
            this.sendWarning(model, errorMessage);
        }else {
        	try {
				Thread.sleep(1500L);
				leaveRequestService.updateLeaveRequest(leaveRequest);
				return "redirect:/leaveRequest/list?=status=success";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/leaveRequest/list?=status=error";
			}
        }
        return "";
    }


}

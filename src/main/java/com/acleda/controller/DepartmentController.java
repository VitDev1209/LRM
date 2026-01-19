package com.acleda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acleda.model.Department;
import com.acleda.service.DepartmentService;

import dto.ApiResponse;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	
	
    @GetMapping("/home")
    public String homePage() {
        return "Department/index"; 
    }

    @CrossOrigin(origins = "*") 
    @GetMapping("/list")
    @ResponseBody
    public ApiResponse<List<Department>> listDepartments() {
        List<Department> list = departmentService.listDepartment();
        return new ApiResponse<>("success", 200, list);
    }
	
    
    @GetMapping("/list_Department")
    public String listDepartment(Model model) {
    	List<Department> departments = departmentService.listDepartment();
    	model.addAttribute("departments",departments);
    	return "Department/index";
    }
    
    
	@GetMapping("/get_Department/{id}")
	public String getDepartment(
	        @PathVariable Integer id,
	        Model model) {

	    if (id == null || id <= 0) {
	        return "error/400"; 
	    }

	    Department department = departmentService.getDepartment(id);

	    if (department == null) {
	        return "error/404";
	    }

	    model.addAttribute("department", department);
	    return "Department/index";
	}

	
	@PostMapping("/addDepartment")
	public ApiResponse<?> addDepartment(@Valid @RequestBody Department department,BindingResult result){
		
		if(result.hasErrors())
		{
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(err->{
				errors.put(err.getField(), err.getDefaultMessage());
				
			});
			 return new ApiResponse<>("error", 400, errors);
		}
		departmentService.addDepartment(department);
		return new ApiResponse<>("Success", 200, "");
	}
	
	
	
	@PutMapping("/edit_Department")
	public ApiResponse<?> edit_Department(@Valid @RequestBody Department department,BindingResult result){
		
		if(result.hasErrors())
		{
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(err->{
				errors.put(err.getField(), err.getDefaultMessage());
			});
			return new ApiResponse<>("error", 400, errors);
		}
		
		
		departmentService.updateDepartment(department);
		
		return new ApiResponse<String>("Success", 200, "Department updated");
		
	}
	
	
	@DeleteMapping("remove_Department/{id}")
	public ApiResponse<String> rm_Department(@PathVariable Integer id){
		
        if (id == null || id <= 0) {
            return new ApiResponse<>("error", 400, "Invalid department ID");
        }
		departmentService.delDepartment(id);
		return new ApiResponse<String>("Success", 200, "Department removed");
	}
	
	
}

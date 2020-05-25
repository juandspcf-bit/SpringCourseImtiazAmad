package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String getEmployees(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employeesList", employees);
		return "/employee/employees";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Employee anEmployee = new Employee();
		model.addAttribute("employee", anEmployee);
		
		return "employee/new-employee";
	}
	
	
	@PostMapping("/save")
	public String createProject(Employee employee, Model model) {
		empRepo.save(employee);
		return "redirect:/employees/new";
	}
	


}

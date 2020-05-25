package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) {
		List<Project> projects = proRepo.findAll();
		List<Employee> employees = empRepo.findAll();
		//model.addAttribute("projectsList", projects);
		Map<String, List<?> > projectsAndEmployees = new HashMap<>(); 
		projectsAndEmployees.put("projectsList", projects);
		projectsAndEmployees.put("employeesList", employees);
		model.addAllAttributes(projectsAndEmployees);
		
		return "main/home";
		
	}

}

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
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository proRepo;

	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String getEmployees(Model model) {
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		return "/project/projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		
		return "project/new-project";
	}
	
	@PostMapping("/save")
	public String createProjectwithoutEmployees(Project aProject) {
		System.out.println("A project without employees");
		proRepo.save(aProject);
		return "redirect:/projects/new";
	}
	
	@PostMapping(path="/save", params= "employees")
	public String createProject(Project aProject,  @RequestParam List<Long> employees, @RequestParam String stage) {
		System.out.println("The current stage is" + stage);
		System.out.println(employees);
		//System.out.println(aProject.getEmployees());
		proRepo.save(aProject);
		(empRepo.findAllById(employees)).forEach((employee) -> {
			employee.setProject(aProject);
			empRepo.save(employee);
		});
		
		return "redirect:/projects/new";
	}
	

	
}

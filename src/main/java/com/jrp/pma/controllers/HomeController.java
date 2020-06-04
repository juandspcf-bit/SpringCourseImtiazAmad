package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
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
		Map<String, Object > projectsAndEmployees = new HashMap<>(); //Model preparation
		
		List<Project> projects = proRepo.findAll();				//Projects Table 
		projectsAndEmployees.put("projectsList", projects);
		
		List<Employee> employees = empRepo.findAll();
		projectsAndEmployees.put("employeesListWithEmail", employees); //Employees with email column Table

		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects(); //Projects Count by stage Table
		projectsAndEmployees.put("employeesList", employeesProjectCnt);
		
		List<ChartData> stagesOfProjectsList = proRepo.getProjectStatus();  //Employees with a column of the number of projects assigned Table
		String stagesOfProjectsJson = this.JsonMapper(stagesOfProjectsList);
		projectsAndEmployees.put("stageOfProjectsList", stagesOfProjectsList);
		projectsAndEmployees.put("stageOfProjectsJson", stagesOfProjectsJson);
		
		System.out.println("*****"+ stagesOfProjectsJson);
		
		model.addAllAttributes(projectsAndEmployees);
		
		return "main/home";
		
	}
	
	private String JsonMapper(List<ChartData> projectData) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(projectData);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error in the convertion";
		}
	}

}

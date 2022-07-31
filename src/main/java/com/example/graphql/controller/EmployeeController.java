package com.example.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.graphql.entity.Employee;
import com.example.graphql.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@MutationMapping
	public Employee createEmployee(@Argument String name, @Argument Integer age, @Argument String salary) {
		Employee employee = Employee.builder().name(name).age(age).salary(salary).build();
		return employeeService.createEmployee(employee);
	}

	@QueryMapping
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployees();
	}

	@QueryMapping
	public Employee getEmployee(@Argument Long id) {
		return employeeService.getEmployeeById(id);
	}
}

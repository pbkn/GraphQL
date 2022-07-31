package com.example.graphql.service;

import java.util.List;

import com.example.graphql.entity.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee employee);

	Employee getEmployeeById(Long id);

	List<Employee> getAllEmployees();
}

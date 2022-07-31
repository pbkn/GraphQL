package com.example.graphql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.graphql.entity.Employee;
import com.example.graphql.exception.EmployeeException;
import com.example.graphql.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeException("Employee id " + id + " doesn't exist"));
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> resultList = employeeRepository.findAll();
		if (CollectionUtils.isEmpty(resultList)) {
			throw new EmployeeException("No data in DB");
		}
		return resultList;
	}

}

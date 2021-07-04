package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeedao;

	public List<Employee> getAllEmployees(){
		
		return employeedao.getAllEmployee();
	}
	
	public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException
	{
		return employeedao.updateEmployee(employee);
	}

	public void deleteEmployee(int id) throws EmployeeNotFoundException {
		
		employeedao.deleteEmployee(id);
		
	}
	
}
	
package com.main.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.main.model.Employee;


public interface EmployeeService {
	//public Employee createEmployee(Employee employee);
	public  Employee getEmployeeById(int id);
    public List<Employee> getAllEmployees();
    public Employee updateEmployee(int id, Employee employee);
    public void deleteEmployee(int id);
	public Employee createEmployee(String employee, MultipartFile profileImage);
}

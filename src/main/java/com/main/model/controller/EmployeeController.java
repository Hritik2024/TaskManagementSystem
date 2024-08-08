package com.main.model.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import com.main.model.Employee;
import com.main.model.service.EmployeeService;

@CrossOrigin("*")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/saveData")
    public ResponseEntity<String> createEmployee(@RequestPart("text") String employee, @RequestPart("profileImage") MultipartFile profileImage) {
        Employee savedEmployee = employeeService.createEmployee(employee,profileImage);
        return new ResponseEntity<String>("Save Employee",HttpStatus.CREATED);
    }

    @GetMapping("getSingleId/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllEmp")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("updateEmp/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("deleteId/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "delete Successfully";
    }
}


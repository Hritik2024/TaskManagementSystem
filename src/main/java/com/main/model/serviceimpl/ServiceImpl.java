package com.main.model.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.model.Employee;
import com.main.model.service.EmployeeService;

import com.main.repository.EmployeeRepository;

@Service
public class ServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;


   

    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        if (employeeRepository.existsById(id)) {
            employee.setId(id);
            return employeeRepository.save(employee);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

	@Override
	public Employee createEmployee(String employee, MultipartFile profileImage) {
		ObjectMapper mapper=new ObjectMapper();
try {
	Employee emp=mapper.readValue(employee, Employee.class);
	if(profileImage!=null) emp.setProfileImage(profileImage.getBytes());
System.err.println(emp);
employeeRepository.save(emp);
}
catch (JsonProcessingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
catch(IOException e)
{
	
}

return null;
}
}
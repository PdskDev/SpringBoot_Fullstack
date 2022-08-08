package com.nadet.demo.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
	
	@Autowired
	private HelloRepository repository;
	
	/**Get one employee **/
	public Employee getEmployee(String id) {
		//Search
		Map<String, Object> map = repository.findById(id);
		
		//Get value form Map
		String employeeId = (String) map.get("id");
		String employeeName = (String) map.get("name");
		int employeeAge = (Integer) map.get("age");
		
		//Set new employee
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setEmployeeName(employeeName);
		employee.setEmployeeAge(employeeAge);
		
		return employee;
		
	}

}

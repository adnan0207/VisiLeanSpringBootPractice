package com.visilean.demo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visilean.demo.entity.Employee;
import com.visilean.demo.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/api")
public class EmployeeRESTController {

	private EmployeeServiceImpl empSer;

	public EmployeeRESTController(EmployeeServiceImpl empSer) {
		this.empSer = empSer;
	}

	// REST end point

	@GetMapping("/employees")
	public List<Employee> getAllEmp() {
		return empSer.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployeeByID(@PathVariable int employeeId) {
		Employee empById = empSer.findById(employeeId);

		if (empById == null) {
			throw new RuntimeException("Employee Id not found - " + employeeId);
		}

		return empById;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee emp) {

		// in case if user has passed the id then set it to zero
		// this is to save it as a new item instead of update

		emp.setId(0);

		Employee dBSavedEmp = empSer.save(emp);

		return dBSavedEmp;
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee emp) {
		Employee updatedEmp = empSer.save(emp);

		return updatedEmp;
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {

		Employee tempEmp = empSer.findById(employeeId);

		if (tempEmp == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		empSer.delete(employeeId);

		return "Deleted employee id + " + employeeId;
	}

}

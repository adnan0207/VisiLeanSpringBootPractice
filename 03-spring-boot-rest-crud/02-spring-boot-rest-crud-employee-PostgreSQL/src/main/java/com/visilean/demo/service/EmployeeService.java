package com.visilean.demo.service;

import java.util.List;

import com.visilean.demo.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	Employee findById(int theID);

	Employee save(Employee emp);

	void delete(int theID);

}

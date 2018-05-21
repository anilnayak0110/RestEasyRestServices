package com.anilnayak.employee.service;

import javax.ws.rs.core.Response;

import com.anilnayak.employee.model.Employee;

public interface EmployeeService {

	public Response addEmployee(Employee emp);

	public Response deleteEmployee(int id);

	public Employee getEmployee(int id);

	public Employee[] getAllEmployees();

}

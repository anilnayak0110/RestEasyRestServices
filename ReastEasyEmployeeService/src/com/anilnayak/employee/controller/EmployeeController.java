package com.anilnayak.employee.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.anilnayak.employee.model.Employee;
import com.anilnayak.employee.model.GenericResponse;
import com.anilnayak.employee.service.EmployeeService;

@Path("/empdetails")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class EmployeeController implements EmployeeService {

	public static Map<Integer, Employee> employeeMap = new HashMap<>();

	@Override
	@POST
	@Path("/add")
	public Response addEmployee(Employee emp) {
		GenericResponse response = new GenericResponse();
		if (employeeMap.get(emp.getId()) != null) {
			response.setStatus(false);
			response.setMessage("Employee Already exist with this Id");
			response.setErrorCode("EC-01");
			return Response.status(422).entity(response).build();
		}
		employeeMap.put(emp.getId(), emp);
		response.setStatus(true);
		response.setMessage("Employee Added Successfully");
		return Response.ok(response).build();
	}

	@Override
	@DELETE
	@Path("/delete/{id}")
	public Response deleteEmployee(@PathParam("id") int id) {
		GenericResponse response = new GenericResponse();
		if (employeeMap.get(id) == null) {
			response.setStatus(false);
			response.setMessage("Employee does not exist with this ID");
			response.setErrorCode("EC-02");
			return Response.status(404).entity(response).build();
		}
		employeeMap.remove(id);
		response.setStatus(true);
		response.setMessage("Employee deleted Successfully");
		return Response.ok(response).build();

	}

	@Override
	@GET
	@Path("/getEmployeeById/{id}")
	public Employee getEmployee(@PathParam("id") int id) {
		return employeeMap.get(id);
	}

	@Override
	@GET
	@Path("/getAllEmployee")
	public Employee[] getAllEmployees() {
		Set<Integer> setEmp = employeeMap.keySet();
		Employee[] emp = new Employee[setEmp.size()];
		int i = 0;
		for (Integer ids : setEmp) {
			emp[i] = employeeMap.get(ids);
			i++;
		}
		return emp;
	}

}

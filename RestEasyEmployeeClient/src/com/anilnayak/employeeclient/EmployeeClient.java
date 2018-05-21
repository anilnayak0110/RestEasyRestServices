package com.anilnayak.employeeclient;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class EmployeeClient {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		ResteasyClient client = new ResteasyClientBuilder().build();

		/**
		 * Get All Employee details for GET Request
		 */
		ResteasyWebTarget getTarget = client
				.target("http://localhost:2019/ReastEasyEmployeeService/rest/empdetails/getAllEmployee");
		Response getResponse = getTarget.request().get();
		String value = getResponse.readEntity(String.class);
		System.out.println(value);
		getResponse.close();
		
		/**
		 * Add Employees For POST Request
		 */
		
		ResteasyWebTarget postTarget = client
				.target("http://localhost:2019/ReastEasyEmployeeService/rest/empdetails/add");
		Employee emp = new Employee();
		emp.setId(8);
		emp.setName("Deepak");
		emp.setSalary(30000);
		Response postResponse = postTarget.request().post(Entity.entity(emp, MediaType.APPLICATION_XML));
		System.out.println(postResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:::"+postResponse.getStatus());
		postResponse.close();
		
		
		/**
		 * Delete Employee For DELETE Request
		 */
		ResteasyWebTarget deleteTarget = client.target("http://localhost:2019/ReastEasyEmployeeService/rest/empdetails/delete/2");
		Response deleteResponse = deleteTarget.request().delete();
		System.out.println(deleteResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:"+deleteResponse.getStatus());
		deleteResponse.close();
	}

}

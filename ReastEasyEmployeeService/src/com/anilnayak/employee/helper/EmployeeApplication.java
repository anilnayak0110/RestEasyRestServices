package com.anilnayak.employee.helper;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.anilnayak.employee.controller.EmployeeController;

public class EmployeeApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public EmployeeApplication() {
		singletons.add(new EmployeeController());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
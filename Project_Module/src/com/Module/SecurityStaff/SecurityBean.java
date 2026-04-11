package com.Module.SecurityStaff;

public class SecurityBean {
	
	private long id;
	private String Name;
	private String shift;
	private double salary;
	private String email;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getShift() {
		return shift;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}

package com.employee;

import java.io.FileWriter;
import java.io.IOException;

import com.exception.ValidationException;
import com.exception.Validator;

public class Employee {
	private String empId;
	private String phone;
	private String name;
	private String username;
	private String email;

	private UserAccount account;

	public Employee(String empId, String name ,String phone, String username, String email, UserAccount account) throws ValidationException {
		Validator.validateEmail(email);
		Validator.validateEmpId(empId);
		Validator.validatePhone(phone);
		this.empId = empId;
		this.name= name;
		this.phone = phone;
		this.username = username;
		this.email = email;
		this.account = account;
	}
	

	public Employee(String empId, String name, String phone, String username, String email) throws ValidationException {
		this(empId, name, phone, username, email, null);
	}


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", phone=" + phone + ", username=" + username + ", email=" + email
				+ ", account=" + account + "]";
	}
	
	public void persist() throws IOException {
		try (FileWriter writer = new FileWriter("employee_data.txt", true)) {
            writer.write(this.toString() + "\n");
            System.out.println("Data persisted in file: employee_data.txt");
        }
	}


	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public UserAccount getAccount() {
		return account;
	}


	public void setAccount(UserAccount account) {
		this.account = account;
	}
	
}

package com.auth;

public class Manager extends User {

	public Manager(String username, String hashedPassword) {
		super(username, hashedPassword);
	}

	@Override
	public boolean authenticate(String username, String password) {
		// checks for username and hashed password
		return this.username.equals(username) &&
	               PasswordUtil.checkPassword(password, this.hashedPassword);
	}
	public void showDashboard() {
		System.out.println("Manager logged in");
	}

	@Override
	public String UserType() {
		return "Manager";
	}
}

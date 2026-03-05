package com.auth;

public class RegularEmployee extends User{

	public RegularEmployee(String username, String hashedPassword) {
		super(username, hashedPassword);
	}

	@Override
	public boolean authenticate(String username, String password) {
		// checks for username and hashed password
		return this.username.equals(username) &&
	               PasswordUtil.checkPassword(password, this.hashedPassword);

	}
	
	@Override
	public void showDashboard() {
		System.out.println("Regular Employee logged in");
	}

	@Override
	public String UserType() {
		return "Regular Employee";
	}
}

package com.auth;

public abstract class User {

	protected String username;
    protected String hashedPassword;

    public User(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    // Abstract method for authentication
    public abstract boolean authenticate(String username, String password);

    public String getUsername() {
        return username;
    }

	public abstract void showDashboard();
	public abstract String UserType();
}


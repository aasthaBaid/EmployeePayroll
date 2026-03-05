package com.employee;

import java.io.FileWriter;
import java.io.IOException;

public class UserAccount {
	private String username;
    private String password;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", password=" + password + "]";
	}

	public void persist() throws IOException{
		try (FileWriter writer = new FileWriter("user_account_data.txt", true)) {
            writer.write(this.toString() + "\n");
            System.out.println("Data persisted in file: user_account_data.txt");
        }
		
	}

    
}

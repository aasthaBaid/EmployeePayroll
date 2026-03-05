package com.user;
import java.io.IOException;
import java.util.*;

import com.employee.Employee;
import com.employee.UserAccount;
import com.exception.ValidationException;
public class Main {

	public static void main(String[] args) throws IOException, ValidationException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose option:");
		System.out.println("1. Register");
		System.out.println("2. Exit");
		int option = sc.nextInt();
		sc.nextLine();
		switch(option) {
		case 1: 
			registerEmployee(sc);
			break;

		case 2:
			System.out.println("Exiting... ");
			break;

		default:
			System.out.println("Invalid choice! Try again.");
		} while (option != 2);

		sc.close();

	}


	private static void registerEmployee(Scanner sc) throws IOException, ValidationException {

		System.out.println("1EMPLOYEE REGISTRATION");

		System.out.print("Enter Employee ID (EMP-XXXX): ");
		String empId = sc.nextLine();

		System.out.print("Enter Name: ");
		String name = sc.nextLine();

		System.out.print("Enter Email: ");
		String email = sc.nextLine();

		System.out.print("Enter Phone: ");
		String phone = sc.nextLine();

		System.out.print("Create Username: ");
		String username = sc.nextLine();

		System.out.print("Create Password: ");
		String password = sc.nextLine();


		// --- Using your existing classes ---
		Employee employee = new Employee(empId, name, phone, username,email);
		UserAccount account = new UserAccount(username, password);

		// Save to file / DB
		employee.persist();
		account.persist();

		// Output
		System.out.println("Employee Registered Successfully:");
		System.out.println(employee); // assuming toString() is formatted nicely
		System.out.println("Username: " + username);
		System.out.println("\nData persisted in file: employee_data.txt");
	}


}

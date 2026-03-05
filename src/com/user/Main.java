package com.user;

import java.io.IOException;
import java.util.Scanner;

import com.auth.AuthService;
import com.auth.PasswordUtil;
import com.auth.RegularEmployee;
import com.auth.Session;
import com.auth.User;
import com.employee.Employee;
import com.employee.UserAccount;
import com.exception.ValidationException;

import payslip.FileService;
import payslip.PayrollService;
import payslip.Payslip;

public class Main {

	public static void main(String[] args) throws IOException, ValidationException {
		Scanner sc = new Scanner(System.in);
		AuthService authService = new AuthService(); // single shared instance
		PayrollService payrollService = new PayrollService(); // UC3 service

		int option = 0;
		while (option != 4) {
			System.out.println("\nChoose option:");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Generate Payslip (UC3)");
			System.out.println("4. Exit");
			System.out.print("Enter choice: ");

			String input = sc.nextLine();
			try {
				option = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a number.");
				continue;
			}

			if (option == 1) {
				System.out.println("\n--- EMPLOYEE REGISTRATION ---");

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

				String hashed = PasswordUtil.hashPassword(password);

				Employee employee = new Employee(empId, name, phone, username, email);
				UserAccount account = new UserAccount(username, password);

				employee.persist();
				account.persist();

				User user = new RegularEmployee(username, hashed);
				authService.registerUser(user);

				System.out.println("\nEmployee Registered Successfully!");
				System.out.println(employee);
				System.out.println("Username: " + username);
				System.out.println("Data persisted in file: employee_data.txt");

			} else if (option == 2) {
				System.out.println("\n--- EMPLOYEE LOGIN ---");

				System.out.print("Enter Username: ");
				String username = sc.nextLine();

				System.out.print("Enter Password: ");
				String password = sc.nextLine();

				Session session = authService.login(username, password);
				if (session != null && session.isActive()) {
					System.out.println("Login successful!");
					System.out.println("User type: " + session.getUser().getClass().getSimpleName());
					session.getUser().showDashboard();
				} else {
					System.out.println("Login failed or session inactive.");
				}

			}else if (option == 3) {
				System.out.println("\n--- PAYSLIP GENERATION ---");

				System.out.print("Enter Employee ID (EMP-XXXX): ");
				String empId = sc.nextLine();

				System.out.print("Enter Name: ");
				String name = sc.nextLine();

				System.out.print("Enter Email: ");
				String email = sc.nextLine();

				System.out.print("Enter Phone: ");
				String phone = sc.nextLine();

				System.out.print("Enter Username: ");
				String username = sc.nextLine();

				Employee employee = new Employee(empId, name, phone, username, email);

				System.out.print("Enter Month: ");
				String month = sc.nextLine();

				System.out.print("Enter Basic Salary: ");
				double basic = Double.parseDouble(sc.nextLine());

				System.out.print("Enter HRA: ");
				double hra = Double.parseDouble(sc.nextLine());

				System.out.print("Enter DA: ");
				double da = Double.parseDouble(sc.nextLine());

				System.out.print("Enter Allowances: ");
				double allowances = Double.parseDouble(sc.nextLine());

				// Generate immutable payslip
				Payslip payslip = payrollService.generatePayslip(employee, month, basic, hra, da, allowances);
				System.out.println(payslip);

				// UC4: Clone and persist
				try {
					Payslip cloned = payslip.clone();
					FileService fs = new FileService();
					String txtPath = fs.savePayslipAsText(cloned);
					String pdfPath = fs.savePayslipAsPdf(cloned);

					System.out.println("\nPayslip saved:");
					System.out.println(" - Text file: " + txtPath);
					System.out.println(" - PDF file : " + pdfPath);
				} catch (Exception e) {
					System.out.println("Error saving payslip: " + e.getMessage());
				}
			}
			else if (option == 4) {
				System.out.println("Exiting... Goodbye!");
				return;
			} else {
				System.out.println("Invalid choice! Try again.");
			}
		}

		sc.close();
	}
}

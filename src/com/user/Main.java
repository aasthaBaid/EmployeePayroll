
package com.user;

import java.io.IOException; 
import java.util.ArrayList; 
import java.util.Scanner;
import com.auth.AuthService; 
import com.auth.PasswordUtil; 
import com.auth.RegularEmployee; 
import com.auth.Session; 
import com.dashboard.Dashboard; 
import com.dashboard.DashboardFactory; 
import com.employee.Employee; 
import com.employee.UserAccount; 
import com.exception.ValidationException;
import com.service.ValidationService;
import com.auth.User;
import payslip.FileService; 
import payslip.PayrollService; 
import payslip.Payslip;

public class Main{
	public static void main(String[] args) throws IOException, ValidationException {
		Scanner sc = new Scanner(System.in);
		AuthService authService = new AuthService();
		PayrollService payrollService = new PayrollService();

		int option = 0;
		while (option != 5) {
			System.out.println("\nChoose option:");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Generate Payslip");
			System.out.println("4. Show Dashboard");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");

			String input = sc.nextLine();
			try {
				option = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a number.");
				continue;
			}

			switch (option) {
			case 1:
				System.out.println("\n--- EMPLOYEE REGISTRATION ---");
				try {
					System.out.print("Enter Employee ID (EMP-XXXX): ");
					String empId = sc.nextLine();
					ValidationService.validateEmployeeId(empId);

					System.out.print("Enter Name: ");
					String name = sc.nextLine();

					System.out.print("Enter Email: ");
					String email = sc.nextLine();
					ValidationService.validateEmail(email);

					System.out.print("Enter Phone: ");
					String phone = sc.nextLine();
					ValidationService.validatePhone(phone);

					System.out.print("Create Username: ");
					String username = sc.nextLine();

					System.out.print("Create Password: ");
					String password = sc.nextLine();
					ValidationService.validatePassword(password);

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
				} catch (ValidationException ex) {
					System.out.println("\n❗ Validation Failed: " + ex.getMessage());
				}
				break;

			case 2:
				System.out.println("\n--- EMPLOYEE LOGIN ---");
				System.out.print("Enter Username: ");
				String loginUser = sc.nextLine();

				System.out.print("Enter Password: ");
				String loginPass = sc.nextLine();

				Session session = authService.login(loginUser, loginPass);
				if (session != null && session.isActive()) {
					System.out.println("Login successful!");
					System.out.println("User type: " + session.getUser().getClass().getSimpleName());
					session.getUser().showDashboard();
				} else {
					System.out.println("Login failed or session inactive.");
				}
				break;

			case 3:
				System.out.println("\n--- PAYSLIP GENERATION ---");
				System.out.print("Enter Employee ID (EMP-XXXX): ");
				String empId2 = sc.nextLine();

				System.out.print("Enter Name: ");
				String name2 = sc.nextLine();

				System.out.print("Enter Month: ");
				String month = sc.nextLine();

				System.out.print("Enter Net Pay: ");
				double netPay = Double.parseDouble(sc.nextLine());

				Payslip payslip = new Payslip(empId2, name2, month, netPay);
				System.out.println(payslip);

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
				break;

			case 4:
				System.out.println("\n--- DASHBOARD VIEW ---");
				String empId3 = "EMP1010";
				String name3 = "Riya";
				Employee employee3 = new Employee(empId3, name3, "9999999999", "demoUser", "demo@example.com");

				String type = "EMPLOYEE";
				ArrayList<Payslip> payslipList = new ArrayList<>();
				payslipList.add(new Payslip(empId3, name3, "January", 30000));
				payslipList.add(new Payslip(empId3, name3, "February", 32000));
				payslipList.add(new Payslip(empId3, name3, "March", 28000));

				try {
					Dashboard dashboard = DashboardFactory.getDashboard(type);
					dashboard.display(payslipList, employee3);
				} catch (IllegalArgumentException e) {
					System.out.println("Error: " + e.getMessage());
				}
				break;

			case 5:
				System.out.println("Exiting... Goodbye!");
				sc.close();
				return;

			default:
				System.out.println("Invalid choice! Try again.");
			}
		}
	}
}

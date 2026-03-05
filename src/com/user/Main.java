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

public class Main {

    public static void main(String[] args) throws IOException, ValidationException {
        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService(); // shared instance

        int option = 0;
        while (option != 3) {
            System.out.println("\nChoose option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
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

                // Register with AuthService
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
                    session.getUser().showDashboard();
                } else {
                    System.out.println("Login failed or session inactive.");
                }

            } else if (option == 3) {
                System.out.println("Exiting... Goodbye!");
            } else {
                System.out.println("Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}

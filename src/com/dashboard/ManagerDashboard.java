package com.dashboard;

import java.util.*;
import com.employee.Employee;
import payslip.Payslip;

public class ManagerDashboard implements Dashboard {

    @Override
    public void display(ArrayList<Payslip> payslips, Employee manager) {
        System.out.println("Manager Dashboard:");
        System.out.println("Welcome, Manager " + manager.getName());
        System.out.println("Dashboard type: " + this.getClass().getName());


        // Calculate total payroll
        double totalPayroll = 0;
        for (Payslip p : payslips) {
            totalPayroll += p.getNetPay();
        }
        System.out.println("Total Payroll (Year to Date): " + totalPayroll);

    }
}

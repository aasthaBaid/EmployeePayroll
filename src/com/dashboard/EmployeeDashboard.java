package com.dashboard;

import java.util.*;
import com.employee.Employee;
import payslip.Payslip;

public class EmployeeDashboard implements Dashboard {

    @Override
    public void display(ArrayList<Payslip> payslip, Employee employee) {
        System.out.println("Employee Dashboard:");
        System.out.println("Welcome, " + employee.getName());
        System.out.println("Dashboard type: " + this.getClass().getName());

        // sort payslips by net pay descending
        Collections.sort(payslip, new Comparator<Payslip>() {
            public int compare(Payslip p1, Payslip p2) {
                return Double.compare(p2.getNetPay(), p1.getNetPay());
            }
        });

        System.out.println("Recent Payslips (top 3):");
        int count = 0;
        for (Payslip p : payslip) {
            if (count >= 3) break;
            System.out.println(p);
            count++;
        }

        double total = 0;
        for (Payslip p : payslip) {
            total += p.getNetPay();
        }
        System.out.println("Year to date earnings: " + total);
    }
}

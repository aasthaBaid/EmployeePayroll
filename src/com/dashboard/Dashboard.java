package com.dashboard;

import java.util.ArrayList;

import com.employee.Employee;

import payslip.Payslip;

public interface Dashboard {
	void display(ArrayList<Payslip> payslip, Employee employee);
}

package payslip;

import com.employee.Employee;

public class Payslip {
	private Employee employee;
	private SalaryComponents componenets;
	private String month;
	public Payslip(Employee employee, SalaryComponents componenets, String month) {
		this.employee = employee;
		this.componenets = componenets;
		this.month = month;
	}
	@Override
	 public String toString() {
        return "\n--- Payslip ---\n" +
               "Month: " + month + "\n" +
               "Employee ID: " + employee.getEmpId() + "\n" +
               "Name: " + employee.getName() + "\n" +
               componenets.toString() + "\n" ;
    }

}

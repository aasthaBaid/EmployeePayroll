package payslip;

import com.employee.Employee;

public class PayrollService {
	public Payslip generatePayslip(Employee employee, String month, double basic, double hra, double da, double allowances) {
		SalaryComponents sc = new SalaryComponents(basic, hra, da, allowances);
		double gross = basic + hra + da +allowances;
		
		sc.pf = basic * 0.12;
		sc.tax = gross * 0.10;
		
		sc.netPay = gross - (sc.pf +sc.tax);
		return new Payslip(employee, sc, month);
	}
}

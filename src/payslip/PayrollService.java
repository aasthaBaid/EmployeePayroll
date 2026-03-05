package payslip;

import com.employee.Employee;

public class PayrollService {
    public Payslip generatePayslip(Employee employee, String month,
                                   double basic, double hra, double da, double allowances) {
        double gross = basic + hra + da + allowances;

        double pf = basic * 0.12;       // Provident Fund deduction
        double tax = gross * 0.10;      // Tax deduction
        double netPay = gross - (pf + tax);

        // Return immutable Payslip 
        return new Payslip(employee.getEmpId(), employee.getName(), month, netPay);
    }
}

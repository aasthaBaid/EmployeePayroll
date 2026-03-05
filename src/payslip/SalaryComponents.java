package payslip;

public class SalaryComponents {
	double basicSalary;
	double hra;
	double da;
	double allowances;
	double pf;
	double tax;
	double netPay;
	public SalaryComponents(double basicSalary, double hra, double da, double allowances) {
		super();
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.da = da;
		this.allowances = allowances;
		
	}
	 public String toString() {
	        return String.format(
	            "Basic: %.2f, HRA: %.2f, DA: %.2f, Allowances: %.2f, PF: %.2f, Tax: %.2f, NetPay: %.2f",
	            basicSalary, hra, da, allowances, pf, tax, netPay
	        );
	    }

}

package com.dashboard;

public class DashboardFactory {
    public static Dashboard getDashboard(String role) {
       if("EMPLOYEE".equals(role)) {
    	   return new EmployeeDashboard();
       }
       else if("MANAGER".equals(role)) {
    	   return new ManagerDashboard();
       }
       return null;
    }
}

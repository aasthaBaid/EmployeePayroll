package com.service;

import com.exception.*;

public class ValidationService {

    private static String sanitize(String input) {
        return input == null ? "" : input.trim();
    }

    public static void validateEmail(String email) throws EmailValidationException {
        email = sanitize(email);
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new EmailValidationException("Invalid email format: " + email);
        }
    }

    public static void validatePhone(String phone) throws PhoneValidationException {
        phone = sanitize(phone);
        if (!phone.matches("^\\d{10}$")) {
            throw new PhoneValidationException("Invalid phone number. Must be 10 digits.");
        }
    }

    public static void validatePassword(String password) throws PasswordValidationException {
        password = sanitize(password);
        if (password.length() < 6) {
            throw new PasswordValidationException("Password too short. Must be at least 6 characters.");
        }
    }

    public static void validateEmployeeId(String empId) throws EmployeeIdValidationException {
        empId = sanitize(empId);
        if (!empId.matches("^EMP\\d{4}$")) {
            throw new EmployeeIdValidationException("Invalid Employee ID. Format should be EMPxxxx (e.g., EMP1234)");
        }
    }
}

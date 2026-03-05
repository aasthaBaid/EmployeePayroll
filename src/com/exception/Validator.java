package com.exception;

import java.util.regex.Pattern;

public class Validator {

	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
	private static final String PHONE_REGEX = "^[0-9]{10}$";
	private static final String EMPID_REGEX = "^EMP[0-9]{4}$";

	public static void validateEmail(String email) throws ValidationException{
		if(!Pattern.matches(EMAIL_REGEX, email))
			throw new ValidationException("Invalid email format");
	}
	public static void validatePhone(String phone) throws ValidationException{
		if(!Pattern.matches(PHONE_REGEX,phone))
			throw new ValidationException("Invalid phone number format");
	}
	public static void validateEmpId(String empId) throws ValidationException{
		if(!Pattern.matches(EMPID_REGEX,empId))
			throw new ValidationException("Invalid Employee ID format");
	}
}
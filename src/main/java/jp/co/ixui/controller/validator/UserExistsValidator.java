package jp.co.ixui.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.NewUserRegistrationForm;
import jp.co.ixui.controller.validator.annotation.UserExists;
import jp.co.ixui.service.EmpService;

public class UserExistsValidator implements ConstraintValidator<UserExists, NewUserRegistrationForm> {

	@Autowired
	EmpService service;

	@Override
	public void initialize(UserExists constraintAnnotation) {

	}

	@Override
	public boolean isValid(NewUserRegistrationForm value, ConstraintValidatorContext context) {

		int empNum = value.getEmpNum();
		return service.isUserRegistered(empNum);
	}

}
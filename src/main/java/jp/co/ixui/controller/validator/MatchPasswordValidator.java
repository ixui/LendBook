package jp.co.ixui.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.NewUserRegistrationForm;
import jp.co.ixui.controller.validator.annotation.MatchPassword;
import jp.co.ixui.service.EmpService;

public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, NewUserRegistrationForm> {

	@Autowired
	EmpService service;

	@Override
	public void initialize(MatchPassword constraintAnnotation) {
	}

	@Override
	public boolean isValid(NewUserRegistrationForm value, ConstraintValidatorContext context) {

		String password = value.getPassword();
		String retypePassword = value.getRetypePassword();
		return service.passwordMatch(password, retypePassword);
	}
}

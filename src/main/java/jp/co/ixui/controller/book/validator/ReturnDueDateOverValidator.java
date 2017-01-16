package jp.co.ixui.controller.book.validator;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.book.validator.annotation.ReturnDueDateOver;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.service.BookService;

public class ReturnDueDateOverValidator implements ConstraintValidator<ReturnDueDateOver, Lend> {

	@Autowired
	BookService service;

	@Override
	public void initialize(ReturnDueDateOver constraintAnnotation) {
	}

	@Override
	public boolean isValid(Lend value, ConstraintValidatorContext context) {
		return service.isReturnDueDateOver(value.getReturnDueDate());
	}

}

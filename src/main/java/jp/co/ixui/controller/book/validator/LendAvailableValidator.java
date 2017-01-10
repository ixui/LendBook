package jp.co.ixui.controller.book.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.book.validator.annotation.LendAvailable;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.service.BookService;

public class LendAvailableValidator implements ConstraintValidator<LendAvailable, Lend> {

	@Autowired
	BookService service;

	@Override
	public void initialize(LendAvailable constraintAnnotation) {
	}

	@Override
	public boolean isValid(Lend value, ConstraintValidatorContext context) {

		int bookStockId = value.getBookStockId();
		return service.isLendable(bookStockId);
	}
}
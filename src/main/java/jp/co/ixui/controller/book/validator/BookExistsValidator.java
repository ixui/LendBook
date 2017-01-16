package jp.co.ixui.controller.book.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.book.BookAdminForm;
import jp.co.ixui.controller.book.validator.annotation.BookExists;
import jp.co.ixui.service.BookService;

public class BookExistsValidator implements ConstraintValidator<BookExists, BookAdminForm> {

	@Autowired
	BookService service;

	@Override
	public void initialize(BookExists constraintAnnotation) {

	}

	@Override
	public boolean isValid(BookAdminForm value, ConstraintValidatorContext context) {

		//フォームのISBNを取得
		String isbn = value.getIsbn();

		//登録されていなければTrueを返す
	}

}

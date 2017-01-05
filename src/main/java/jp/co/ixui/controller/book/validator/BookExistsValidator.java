package jp.co.ixui.controller.book.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.book.BookAdminForm;
import jp.co.ixui.controller.book.validator.annotation.BookExists;
import jp.co.ixui.mapper.MstBookMapper;

public class BookExistsValidator implements ConstraintValidator<BookExists, BookAdminForm> {

	@Autowired
	MstBookMapper mstBookMapper;

	@Override
	public void initialize(BookExists constraintAnnotation) {

	}

	@Override
	public boolean isValid(BookAdminForm value, ConstraintValidatorContext context) {

		//フォームのISBNを取得
		String isbn = value.getIsbn();

		//既にISBNが登録されていればエラー
		BookAdminForm mstBook = this.mstBookMapper.selectBook(isbn);
		if(mstBook != null){
			return false;
		}

		//登録されていなければTrueを返す
		return true;
	}

}

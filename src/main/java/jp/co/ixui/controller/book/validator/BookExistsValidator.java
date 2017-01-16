package jp.co.ixui.controller.book.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.book.BookAdminForm;
import jp.co.ixui.controller.book.validator.annotation.BookExists;
import jp.co.ixui.service.BookService;

/**
 * DB上に書籍が存在していないかバリデーションチェック
 * @author NAKAJIMA
 */
public class BookExistsValidator implements ConstraintValidator<BookExists, BookAdminForm> {

	@Autowired
	BookService service;

	/**
	 * 初期設定なし
	 */
	@Override
	public void initialize(BookExists constraintAnnotation) {

	}

	/**
	 * BookAdminFormに格納されているISBNから登録されているか確認する。
	 * @return 既に登録されていればFalse,登録されていなければTrueを返す。
	 */
	@Override
	public boolean isValid(BookAdminForm value, ConstraintValidatorContext context) {

		//フォームのISBNを取得
		String isbn = value.getIsbn();

		//登録されていなければTrueを返す
		return service.isBookResgitered(isbn);
	}

}

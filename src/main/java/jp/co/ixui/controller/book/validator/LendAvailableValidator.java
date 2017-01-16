package jp.co.ixui.controller.book.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.book.validator.annotation.LendAvailable;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.service.BookService;

/**
 * 貸出可否バリデーション
 * @author NAKAJIMA
 *
 */
public class LendAvailableValidator implements ConstraintValidator<LendAvailable, Lend> {

	@Autowired
	BookService service;

	/**
	 * 初期設定なし
	 */
	@Override
	public void initialize(LendAvailable constraintAnnotation) {
	}

	/**
	 * Lendに格納されている蔵書ID(bookStockId)を使用して貸出が可能かどうか確認する。
	 * @return 貸出可能ならtrue, 貸出不可ならfalseを渡す。
	 */
	@Override
	public boolean isValid(Lend value, ConstraintValidatorContext context) {

		int bookStockId = value.getBookStockId();
		return service.isLendable(bookStockId);
	}
}
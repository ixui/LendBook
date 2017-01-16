package jp.co.ixui.controller.book.validator;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.book.validator.annotation.ReturnDueDateOver;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.service.BookService;

/**
 * 貸出時の返却予定日バリデーション
 * @author NAKAJIMA
 *
 */
public class ReturnDueDateOverValidator implements ConstraintValidator<ReturnDueDateOver, Lend> {

	@Autowired
	BookService service;

	/**
	 * 初期設定なし
	 */
	@Override
	public void initialize(ReturnDueDateOver constraintAnnotation) {
	}

	/**
	 * Lendに格納されている返却予定日を使い、返却予定日の妥当性を判定する。<br>
	 * 返却予定日が正常ならtrue,問題があればfalseを返す。
	 */
	@Override
	public boolean isValid(Lend value, ConstraintValidatorContext context) {
		return service.isReturnDueDateOver(value.getReturnDueDate());
	}
}
package jp.co.ixui.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.NewUserRegistrationForm;
import jp.co.ixui.controller.validator.annotation.MatchPassword;
import jp.co.ixui.service.EmpService;

/**
 * パスワードと確認用パスワードの値が一致しているかバリデーションチェック
 * @author NAKAJIMA
 *
 */
public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, NewUserRegistrationForm> {

	/**
	 * ユーザに関する処理を行うクラス
	 */
	@Autowired
	EmpService service;

	/**
	 * 初期設定なし
	 */
	@Override
	public void initialize(MatchPassword constraintAnnotation) {
	}

	/**
	 * {@link NewUserRegistrationForm}に格納されているpasswordとretypePasswordが一致しているか確認しています。
	 * @return 一致していればTrue、一致していなければFalseが返ります。
	 */
	@Override
	public boolean isValid(NewUserRegistrationForm value, ConstraintValidatorContext context) {

		String password = value.getPassword();
		String retypePassword = value.getRetypePassword();
		return service.passwordMatch(password, retypePassword);
	}
}

package jp.co.ixui.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.controller.NewUserRegistrationForm;
import jp.co.ixui.controller.validator.annotation.UserExists;
import jp.co.ixui.service.EmpService;

/**
 * DBを検索しユーザが登録されていないかをバリデーションチェック
 * @author NAKAJIMA
 *
 */
public class UserExistsValidator implements ConstraintValidator<UserExists, NewUserRegistrationForm> {

	/**
	 * ユーザに関する処理を行うクラス
	 */
	@Autowired
	EmpService service;

	/**
	 * 初期設定なし
	 */
	@Override
	public void initialize(UserExists constraintAnnotation) {

	}

	/**
	 * {@link NewUserRegistrationForm}に格納されているEmpNumから、<br>
	 * 同じ社員番号が登録されていないかを確認しています。
	 * @return 登録されていなければTrue, 登録されていればFalseが返ります。
	 */
	@Override
	public boolean isValid(NewUserRegistrationForm value, ConstraintValidatorContext context) {

		int empNum = value.getEmpNum();
		return service.isUserRegistered(empNum);
	}

}
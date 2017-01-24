package jp.co.ixui.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import jp.co.ixui.controller.validator.annotation.MatchPassword;
import jp.co.ixui.controller.validator.annotation.UserExists;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@UserExists
@MatchPassword
public class NewUserRegistrationForm {

	/**
	 * 社員番号
	 */
	@NotNull
	@Min(message = "社員番号は1桁以上入力してください。", value = 1)
	@Max(message = "社員番号は4桁以下入力してください。", value= 9999)
	private int empNum;

	/**
	 * ユーザ名
	 */
	@NotNull
	@NotEmpty(message = "ユーザ名を入力してください。")
	private String empName;

	/**
	 * メールアドレス
	 */
	@NotNull
	@NotEmpty(message = "メールアドレスを入力してください。")
	private String mailAddress;

	/**
	 * パスワード
	 */
	@NotNull
	@NotEmpty(message = "パスワードを入力してください。")
	private String password;

	/**
	 * 確認用パスワード
	 */
	@NotNull
	@NotEmpty(message = "確認用パスワードを入力してください。")
	private String retypePassword;

	@NotNull
	@NotEmpty
	private String adminFrag;

	/**
	 * 登録者番号
	 */
	private int registEmpNum;

	/**
	 * 更新者番号
	 */
	private int updateEmpNum;

	/**
	 * 登録日時
	 */
	private String registTime;

	/**
	 * 更新日時
	 */
	private String updateTime;
}

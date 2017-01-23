package jp.co.ixui.controller;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserRegistrationForm {

	/**
	 * 社員番号
	 */
	@NotNull
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
	private String password;

	/**
	 * 確認用パスワード
	 */
	@NotNull
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

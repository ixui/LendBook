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
	 * <p>社員番号</p>
	 * 社員番号は一桁以上四桁以下で入力します。<br>
	 * nullは使用できません。
	 */
	@NotNull
	@Min(message = "社員番号は1桁以上入力してください。", value = 0)
	@Max(message = "社員番号は4桁以下入力してください。", value= 9999)
	private int empNum;

	/**
	 * <p>ユーザ名<p>
	 * null、空文字は使用できません。
	 */
	@NotNull
	@NotEmpty(message = "ユーザ名を入力してください。")
	private String empName;

	/**
	 * <p>メールアドレス</p>
	 * null、空文字は使用できません。
	 */
	@NotNull
	@NotEmpty(message = "メールアドレスを入力してください。")
	private String mailAddress;

	/**
	 * <p>パスワード</p>
	 * null、空文字は使用できません。
	 */
	@NotNull
	@NotEmpty(message = "パスワードを入力してください。")
	private String password;

	/**
	 * <p>確認用パスワード</p>
	 * null、空文字は使用できません。
	 */
	@NotNull
	@NotEmpty(message = "確認用パスワードを入力してください。")
	private String retypePassword;

	/**
	 * <p>管理者フラグ</p>
	 * <p>登録時に設定、変更は可能です。<br>
	 * 0:一般ユーザ 1:管理者<br>
	 * 管理者でなければアクセスできない画面や処理があります。<br>
	 * null、空文字は使用できません。
	 */
	@NotNull
	@NotEmpty
	private String adminFrag;

	/**
	 * <p>登録者番号</p>
	 */
	private int registEmpNum;

	/**
	 * <p>更新者番号</p>
	 */
	private int updateEmpNum;

	/**
	 * <p>登録日時</p>
	 */
	private String registTime;

	/**
	 * <p>更新日時</p>
	 */
	private String updateTime;
}

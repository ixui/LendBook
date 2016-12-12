package jp.co.ixui.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MstEmp {

	//社員番号
	private int empNum;

	//メールアドレス
	@NotNull
	@NotEmpty(message="メールアドレスを入力してください")
	private String mailAddress;

	//パスワード
	@NotNull
	@NotEmpty(message="パスワードを入力してください。")
	private String password;

	//社員名
	@NotNull
	private String empName;

	//管理者フラグ
	@NotNull
	private String adminFrag;
}

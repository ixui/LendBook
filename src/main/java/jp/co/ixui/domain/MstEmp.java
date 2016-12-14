package jp.co.ixui.domain;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 社員マスタードメインオブジェクト
 * @author NAKAJIMA
 *
 */
@Getter
@Setter
public class MstEmp {

	//社員番号
	@NotNull
	private int empNum;

	//メールアドレス
	@NotNull
	private String mailAddress;

	//パスワード
	@NotNull
	private String password;

	//社員名
	@NotNull
	private String empName;

	//管理者フラグ
	@NotNull
	private String adminFrag;
}

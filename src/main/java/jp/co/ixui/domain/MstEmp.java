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

	/**
	 * <p>社員番号</p>
	 * 登録時に設定し、変更は可能です。
	 */
	//社員番号
	@NotNull
	private int empNum;

	/**
	 * <p>メールアドレス</p>
	 * 登録時に設定、変更は可能です。
	 */
	//メールアドレス
	@NotNull
	private String mailAddress;

	/**
	 * <p>パスワード</p>
	 * 登録時に設定、変更は可能です。<br>
	 * 英数字で入力、DB上にはハッシュ化された値が入ります。
	 */
	//パスワード
	@NotNull
	private String password;

	/**
	 * <p>社員名</p>
	 * 登録時に設定、変更は可能です。<br>
	 */
	//社員名
	@NotNull
	private String empName;

	/**
	 * <p>管理者フラグ</p>
	 * <p>登録時に設定、変更は可能です。<br>
	 * 0:一般ユーザ 1:管理者<br>
	 * 管理者でなければアクセスできない画面や処理があります。
	 */
	//管理者フラグ
	@NotNull
	private String adminFrag;
}

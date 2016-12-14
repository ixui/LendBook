package jp.co.ixui.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

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
	private int empNum;

	//メールアドレス
    @NotEmpty(message="{lastName}{NotEmpty}")
    @Size(max=50, message="{lastName}{Max}")
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

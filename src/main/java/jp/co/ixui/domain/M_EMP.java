package jp.co.ixui.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class
M_EMP {
	
	/**
	 * 社員番号
	 */
	@NotNull
	private String emp_num;
	
	/**
	 *　メールアドレス
	 */
	@NotNull
	@NotEmpty(message="メールアドレスを入力してください。")
	private String mail_address;
	
	/**
	 * 社員名
	 */
	@NotNull
	private String emp_name;
	
	
	/**
	 * パスワード
	 */
	@NotNull
	@NotEmpty(message="パスワードを入力してください。")
	private String password;
	

}

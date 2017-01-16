package jp.co.ixui;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import jp.co.ixui.domain.MstEmp;

/**
 * ログイン時にユーザー情報を格納する。
 * @author NAKAJIMA
 *
 */
public class LoginUserDetails extends User{

	private final MstEmp mstEmp;

	/**
	 * ログイン時に起動され、ログインユーザの情報をこのクラスに格納する。<br>
	 * ログインユーザ情報を取得する際はこのクラスを利用する。
	 * @param mstEmp UserDetailsServiceImplで取得したログイン情報
	 */
	//LoginUserDetailsで取得した情報を格納する
	public LoginUserDetails(MstEmp mstEmp){
		super(
				mstEmp.getMailAddress(),
				mstEmp.getPassword(),
				AuthorityUtils.createAuthorityList(mstEmp.getAdminFrag()));
		this.mstEmp = mstEmp;

	}

	public MstEmp getUser(){
		return this.mstEmp;
	}
}
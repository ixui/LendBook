package jp.co.ixui;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.security.UserDetailsServiceImpl;

/**
 * <p> ログイン時に起動され、ログインユーザの情報をこのクラスに格納します。</p>
 * <p> {@link UserDetailsServiceImpl#loadUserByUsername(String)}から渡された<br>
 * {@link MstEmp}(ログインユーザ)を保持し、呼び出された際にログインユーザーの情報を渡します。</p>
 * @author NAKAJIMA
 *
 */
public class LoginUserDetails extends User{

	/**
	 * ログインユーザ情報
	 */
	private final MstEmp mstEmp;

	/**
	 * 渡された{@link MstEmp}の値をこのクラスのフィールドに格納します。
	 * @param mstEmp {@link UserDetailsServiceImpl#loadUserByUsername(String)}で取得した値
	 */
	public LoginUserDetails(MstEmp mstEmp){
		super(
				mstEmp.getMailAddress(),
				mstEmp.getPassword(),
				AuthorityUtils.createAuthorityList(mstEmp.getAdminFrag()));
		this.mstEmp = mstEmp;

	}

	/**
	 * @return このクラスの{@link MstEmp}の値を返す
	 */
	public MstEmp getUser(){
		return this.mstEmp;
	}
}
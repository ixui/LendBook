package jp.co.ixui;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import jp.co.ixui.domain.MstEmp;

public class LoginUserDetails extends User{

	private final MstEmp mstEmp;

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

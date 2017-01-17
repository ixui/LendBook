package jp.co.ixui.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.ixui.LoginUserDetails;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.service.EmpService;

/**
 * ログインフォームPOST時の妥当性判定
 * @author NAKAJIMA
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	/**
	 * 社員サービスクラス
	 */
	@Autowired
	EmpService service;

	/**
	 * ログインフォームに入力されたメールアドレスからユーザ情報を確認します。<br>
	 * メールアドレスが空、又はnullの場合にはエラー<br>
	 * メールアドレスが入力されている場合、該当ユーザーが存在するかDBを検索し、<br>
	 * 存在していない場合はエラー<br>
	 * 存在している場合は{@link LoginUserDetails#LoginUserDetails(MstEmp)}に取得したユーザ情報を渡します。<br>
	 * @param mailAddress ログイン時にPOSTされたメールアドレス<br>
	 * @return ログインユーザーの情報を取得し、<br>
	 * {@link LoginUserDetails#LoginUserDetails(MstEmp)}に返します。
	 */
	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {

		//ログインフォームに入れたメールアドレスの値を判定
		if(mailAddress == null || "".equals(mailAddress)){
			throw new UsernameNotFoundException("MailAddress is empty");
		}

		//社員1名の情報をmstEmpに格納
		MstEmp mstEmp = service.getUser(mailAddress);
		//該当するメールアドレスが存在しない場合エラー
		if(mstEmp == null){
			throw new UsernameNotFoundException("User not found for name: " + mailAddress);
		}

		return new LoginUserDetails(mstEmp);

	}
}

package jp.co.ixui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.domain.MstEmp;

/**
 * <p>{@link LoginUserDetails}のユニットテストです。</p>
 * ログインユーザの情報が格納されます。
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
public class LoginUserDetailsTest {

	/**
	 * {@link MstEmp}に仮の値を入れ、{@link LoginUserDetails}に渡します。<br>
	 * 渡した値が正常に入っているかを確認しています。
	 */
	//正常
	@Test
	public void LoginUserDetailsにユーザ情報が格納されているか確認(){

		//MstEmpに仮の値を入れる
		MstEmp mstEmp = new MstEmp();
		mstEmp.setMailAddress("test@tosyo.co.jp");
		mstEmp.setPassword("test");
		mstEmp.setAdminFrag("0");

		LoginUserDetails loginUserDetails = new LoginUserDetails(mstEmp); //MstEmpの値をLoginUserDetailsに渡す

		//値が正しいか判定
		assertEquals("test@tosyo.co.jp", loginUserDetails.getUsername());
		assertEquals("test", loginUserDetails.getPassword());
		assertEquals("0", loginUserDetails.getUser().getAdminFrag());
	}
}

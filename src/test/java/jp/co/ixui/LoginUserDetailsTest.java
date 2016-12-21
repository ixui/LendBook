package jp.co.ixui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.domain.MstEmp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginUserDetailsTest {

	//正常
	@Test
	public void ユーザー情報が格納されているか確認(){

		//MstEmpに仮の値を入れる
		MstEmp mstEmp = new MstEmp();
		mstEmp.setMailAddress("test@tosyo.co.jp");
		mstEmp.setPassword("test");
		mstEmp.setAdminFrag("0");

		//MstEmpに値が入っているか確認
		assertEquals("test@tosyo.co.jp", mstEmp .getMailAddress());
		assertEquals("test", mstEmp .getPassword());
		assertEquals("0", mstEmp .getAdminFrag());

		LoginUserDetails loginUserDetails = new LoginUserDetails(mstEmp); //MstEmpの値をLoginUserDetailsに渡す
		//値が正しいか判定
		assertEquals("test@tosyo.co.jp", loginUserDetails.getUsername());
		assertEquals("test", loginUserDetails.getPassword());
		//adminFragはリストで格納されているため比較方法は再度考える。
	}

	//異常
	@Test
	public void MstEmpが空の場合にLoginUserDetailsを処理(){ //テスト名がおかしい 「MstEmpが空の場合エラーがでるか確認」の方がよかったかもしれないです

		MstEmp mstEmp2 = new MstEmp(); //値を入れていないMstEmpオブジェクト

		try{
		LoginUserDetails loginUserDetails = new LoginUserDetails(mstEmp2); //MstEmpの値をLoginuserDetailsに渡す
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

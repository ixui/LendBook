package jp.co.ixui;

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

		LoginUserDetails loginUserDetails = new LoginUserDetails(mstEmp);
		System.out.println(loginUserDetails.getUser());
	}

	//異常
	@Test
	public void MstEmpが空の場合にLoginUserDetailsを処理(){

		MstEmp mstEmp2 = new MstEmp();

		try{
		LoginUserDetails loginUserDetails = new LoginUserDetails(mstEmp2);
		System.out.println(loginUserDetails.getUser());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

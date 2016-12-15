package jp.co.ixui.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.domain.MstEmp;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserDetalsServiceImplTest {


	@Mock
	MstEmp mstEmp;

	@InjectMocks
	private UserDetailsServiceImpl userDetailsServiceImpl;


	@Test
	public void メールアドレスが空の場合認証テスト(){
		//UserDetailsServiceImpl userDetails = new UserDetailsServiceImpl();
		try{
		userDetailsServiceImpl.loadUserByUsername(null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void 該当のユーザーが存在しない場合の認証テスト(){
		try{
		userDetailsServiceImpl.loadUserByUsername("null@tosyo.co.jp");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

/*	@Test
	public void 該当ユーザーが存在した場合の認証(){
			userDetailsServiceImpl.loadUserByUsername("admin@tosyo.co.jp");
	}*/

}

package jp.co.ixui.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.mapper.MstEmpMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceImplTest {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	MstEmpMapper mstEmpMapper;

	@Test
	public void 存在するユーザーの認証(){
		String mail_address = "admin@tosyo.co.jp";
		try{
		userDetailsServiceImpl.loadUserByUsername(mail_address);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void 存在しないユーザーの認証(){
		try{
		userDetailsServiceImpl.loadUserByUsername("null@tosyo.co.jp");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void 空文字の認証(){
		try{
			userDetailsServiceImpl.loadUserByUsername("");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

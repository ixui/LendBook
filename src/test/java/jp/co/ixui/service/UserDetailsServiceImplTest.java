package jp.co.ixui.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jp.co.ixui.security.UserDetailsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserDetailsServiceImplTest {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Test
	public void 照合が正しく行われているか(){
		String mail_address = "admin@tosyo.co.jp";
		userDetailsServiceImpl.loadUserByUsername(mail_address);
	}

}

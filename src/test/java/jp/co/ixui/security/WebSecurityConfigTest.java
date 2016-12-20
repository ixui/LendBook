package jp.co.ixui.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jp.co.ixui.security.WebSecurityConfig.AuthenticationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSecurityConfigTest {

	@Autowired
	private WebApplicationContext context; //アプリケーションの設定等を管理するコンテキスト

	private MockMvc mockMvc; //リクエストとレスポンスとそれに付属する情報のオブジェクト

	@Autowired
	WebSecurityConfig webSecurityConfig;

	@Autowired
	ObjectPostProcessor<Object> objectPostProcessor;

	@Autowired
	AuthenticationManagerBuilder authenticationBuilder;

	HashMap<Class<? extends Object>, Object> sharedObjects = new HashMap<Class<? extends Object>, Object>();

	@Autowired
	AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	UserDetailsServiceImpl userDetailsService;


	//事前処理
	@Before
	public void 事前処理() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context) //モックの初期化
        		.apply(springSecurity())
        		.build();
		}

	@Test
	public void 許可されているページへアクセス() throws Exception{
		HttpSecurity http = new HttpSecurity(objectPostProcessor, authenticationBuilder, sharedObjects);
		webSecurityConfig.configure(http);
		mockMvc.perform(get("/login"))
			.andExpect(status().isOk());
		}

	@Test
	public void 許可されていないページへアクセス() throws Exception{
		HttpSecurity http = new HttpSecurity(objectPostProcessor, authenticationBuilder, sharedObjects);
		webSecurityConfig.configure(http);
		mockMvc.perform(get("/main"))
			.andExpect(status().is3xxRedirection());
		}


	@Test
	public void パスワードエンコーダー(){
		PasswordEncoder s = authenticationConfiguration.passwordEncoder();
		System.out.println(s.encode("1234"));
		System.out.println(s.matches("1234", "$2a$10$dOVXGCLiErzFZ.8usPKJ/urZRHOVDrhvuPFmQ.dXPoulu10Ejolna"));
	}


	@Test
	public void 起動テスト() throws Exception{
		AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder(objectPostProcessor);
		authenticationConfiguration.init(auth);

		mockMvc.perform(
				formLogin()
					.user("mail_address", "admin@tosyo.co.jp")
					.password("password", "aaaa"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/main"));
	}

}



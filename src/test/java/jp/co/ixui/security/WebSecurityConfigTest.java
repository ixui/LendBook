package jp.co.ixui.security;

import static org.junit.Assert.*;
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

	/**
	 * MockMvcのオブジェクトを作成するのに必要なアプリケーション設定
	 */
	@Autowired
	private WebApplicationContext context;

    /**
     * モックオブジェクト
     */
	private MockMvc mockMvc; //リクエストとレスポンスとそれに付属する情報のオブジェクト

	/**
	 * WebSecurityConfigurerAdapterを継承したクラスです。<br>
	 * Securityの設定をしています。
	 */
	@Autowired
	WebSecurityConfig webSecurityConfig;

	/**
	 * オブジェクトの初期化をしています。
	 */
	@Autowired
	ObjectPostProcessor<Object> objectPostProcessor;

	/**
	 * 認証情報構築のためのクラスです。
	 */
	@Autowired
	AuthenticationManagerBuilder authenticationBuilder;

	/**
	 * HttpSecurityに使うハッシュ表です。
	 */
	HashMap<Class<? extends Object>, Object> sharedObjects = new HashMap<Class<? extends Object>, Object>();

	/**
	 * GlobalAuthenticationConfigurerAdapterを継承したクラスです。<br>
	 * ログイン処理をカスタマイズしています。
	 */
	@Autowired
	AuthenticationConfiguration authenticationConfiguration;

	/**
	 * UserDetailsServiceを継承したクラスです。<br>
	 * ログインユーザーの値を検証しています。
	 */
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	/**
	 * MockMvcの初期設定
	 * @throws Exception
	 */
	@Before
	public void 事前処理() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context) //モックの初期化
        		.apply(springSecurity())
        		.build();
		}

	/**
	 * Security設定で許可されているページへアクセスします。
	 * @throws Exception
	 */
	@Test
	public void 許可されているページへアクセス() throws Exception{
		HttpSecurity http = new HttpSecurity(objectPostProcessor, authenticationBuilder, sharedObjects);
		webSecurityConfig.configure(http);
		mockMvc.perform(get("/login"))
			.andExpect(status().isOk());
		}

	/**
	 * Security設定でログインしていないユーザが許可されていないページへアクセスします。
	 * @throws Exception
	 */
	@Test
	public void 許可されていないページへアクセス() throws Exception{
		HttpSecurity http = new HttpSecurity(objectPostProcessor, authenticationBuilder, sharedObjects);
		webSecurityConfig.configure(http);
		mockMvc.perform(get("/main"))
			.andExpect(status().is3xxRedirection());
		}

	/**
	 * パスワードエンコーダに入れた値を検証しています。
	 */
	@Test
	public void パスワードエンコーダー(){
		PasswordEncoder s = authenticationConfiguration.passwordEncoder();
		System.out.println(s.encode("1234"));
		assertTrue(s.matches("1234", "$2a$10$dOVXGCLiErzFZ.8usPKJ/urZRHOVDrhvuPFmQ.dXPoulu10Ejolna"));
	}
}

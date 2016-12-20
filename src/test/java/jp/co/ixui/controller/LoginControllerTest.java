package jp.co.ixui.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest
public class LoginControllerTest {

	@Autowired
	private WebApplicationContext context; //アプリケーションの設定等を管理するコンテキスト

	private MockMvc mockMvc; //リクエストとレスポンスとそれに付属する情報のオブジェクト

	//事前処理
	@Before
	public void 事前処理() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context) //モックの初期化
        		.apply(springSecurity())
        		.build();
		}

	//異常
	@Test
	public void ログインしないでメイン画面へアクセス() throws Exception{
		mockMvc.perform(get("/main"))
				.andExpect(status().is4xxClientError());
	}

	//正常
	@Test
	@WithMockUser(username="admin@tosyo.co.jp")
	public void ログインしてメイン画面へアクセス() throws Exception{
		mockMvc.perform(get("/main"))
				.andExpect(status().isOk());
	}

	//正常
	@Test
	public void ログインページのテスト() throws Exception{
		mockMvc.perform(
				formLogin()
					.user("username", "admin@tosyo.co.jp")
					.password("password", "aaaa")
					.loginProcessingUrl("/main"));
	}
}

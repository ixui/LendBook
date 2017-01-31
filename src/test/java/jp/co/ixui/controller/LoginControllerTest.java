package jp.co.ixui.controller;



import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * {@link MainController#main(org.springframework.web.servlet.ModelAndView)}のユニットテストです。<br>
 * SpringSecurityが正常に動作しているか確認のためのテストクラスです。
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

	/**
	 * MockMvcのオブジェクトを作成するのに必要なアプリケーション設定
	 */
	@Autowired
	private WebApplicationContext context;

	/**
     * モックオブジェクト
     */
	private MockMvc mockMvc;

	/**
	 * MockMvcの初期設定<br>
	 * SpringSecurityが適用されています。
	 */
	@Before
	public void 事前処理() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
        		.apply(springSecurity())
        		.build();
		}

	/**
	 * ログインしないでメイン画面へアクセスします。<br>
	 * SpringSecurityによってアクセスが許可されずエラーになります。
	 * @throws Exception
	 */
	@Test
	public void ログインしないでメイン画面へアクセス() throws Exception{
		mockMvc.perform(get("/main"))
				.andExpect(status().is3xxRedirection());
	}

	/**
	 * モックユーザを作成しログイン状態でメイン画面へアクセスします。<br>
	 * 正常にログインできます。
	 * @throws Exception
	 */
	@Test
	@WithMockUser(username="admin@tosyo.co.jp")
	public void ログインしてメイン画面へアクセス() throws Exception{
		mockMvc.perform(get("/main"))
				.andExpect(status().isOk());
	}
}

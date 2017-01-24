package jp.co.ixui.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * {@link MainController}のユニットテストです。
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {

	/**
	 * MockMvcのオブジェクトを作成するのに必要なアプリケーション設定
	 */
    @Autowired
    private WebApplicationContext context;

    /**
     * モックオブジェクト
     */
	private MockMvc mockMvc;

	@InjectMocks
	MainController controller;

	/**
	 * MockMvcの初期設定
	 */
	@Before
	public void 事前処理(){
        this.mockMvc = MockMvcBuilders
        		.webAppContextSetup(this.context) //モックの初期化
        		.build();
		}

	/**
	 * インデックス画面へアクセスします。<br>
	 * @throws Exception
	 */
	@Test
	public void インデックス画面へアクセス() throws Exception{
			mockMvc.perform(get("/"))
			.andExpect(status().isOk());
	}

	/**
	 * ログイン画面へアクセスします。<br>
	 * @throws Exception
	 */
	@Test
	public void ログイン画面へアクセス() throws Exception{
			mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}

	/**
	 * ユーザ登録/管理画面へアクセスします。
	 * @throws Exception
	 */
	@Test
	public void ユーザ登録管理画面へアクセス() throws Exception{
		mockMvc.perform(get("/admin/user"))
		.andExpect(status().isOk());
	}

	/**
	 * ユーザ登録/管理ページから新規ユーザ登録ができるかを確認しています。
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void ユーザ登録フォームの送信テスト() throws Exception{
		this.mockMvc.perform(post("/admin/user")
				.param("empNum",  "5010")
				.param("empName", "TEST")
				.param("mailAddress", "test@tosyo.co.jp")
				.param("password", "abcd")
				.param("retypePassword",  "abdd")
				.param("adminFrag", "0"))
				.andExpect(status().isOk())
	    		.andExpect(model().hasErrors())
	    		.andExpect(model().errorCount(1))
	    		.andExpect(model().attributeHasErrors("userForm"));
	}
}

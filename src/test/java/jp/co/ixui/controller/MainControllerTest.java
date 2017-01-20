package jp.co.ixui.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
}

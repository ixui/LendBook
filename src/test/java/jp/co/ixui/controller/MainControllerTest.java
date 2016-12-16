package jp.co.ixui.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MainControllerTest {


	@Autowired
	private WebApplicationContext context; //アプリケーションの設定等を管理するコンテキスト

	private MockMvc mockMvc; //リクエストとレスポンスとそれに付属する情報のオブジェクト

	//事前処理
	@Before
	public void 事前処理() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context) //モックの初期化
        		.build();
		}

	@Test
	public void メイン画面へアクセス() throws Exception{
			mockMvc.perform(get("/"))
			.andExpect(status().isOk());
	}

	@Test
	public void ログイン画面へアクセス() throws Exception{
			mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}
}

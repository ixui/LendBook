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

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {

    @Autowired
    protected WebApplicationContext context;

	private MockMvc mockMvc; //リクエストとレスポンスとそれに付属する情報のオブジェクト

	//事前処理
	@Before
	public void 事前処理() throws Exception{
        this.mockMvc = MockMvcBuilders
        		.webAppContextSetup(this.context) //モックの初期化
        		.build();
		}

	//正常
	@Test
	public void メイン画面へアクセス() throws Exception{
			mockMvc.perform(get("/"))
			.andExpect(status().isOk());
	}

	//正常
	@Test
	public void ログイン画面へアクセス() throws Exception{
			mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}
}

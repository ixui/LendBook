package jp.co.ixui.controller.book;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jp.co.ixui.service.BookDisplayService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

	@Autowired
	private WebApplicationContext context; //アプリケーションの設定等を管理するコンテキスト

	private MockMvc mockMvc; //リクエストとレスポンスとそれに付属する情報のオブジェクト

	@Mock
	BookDisplayService service;

	@InjectMocks
	BookControllerTest controller;

	//事前処理
	@Before
	public void 事前処理() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context) //モックの初期化
        		.build();
	}

	//正常
	@Test
	public void 書籍登録フォーム送信テスト() throws Exception{
    ResultActions resultActions = mockMvc.perform(post("/admin/book")
    		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
    		.param("bookName", "TEST")
    		.param("author", "author")
    		.param("publisher", "publish")
    		.param("isbn", "123-1234567890")
    		.param("publishDate", "2015/8/25")
    		.param("content", "Test"));

    resultActions.andExpect(model().hasNoErrors());
}

	//異常
	@Test
	public void 書籍登録フォーム未入力送信テスト() throws Exception{

		//ISBNを表記していない
        ResultActions resultActions = mockMvc.perform(post("/admin/book")
        		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
        		.param("bookName", "TEST")
        		.param("author", "author")
        		.param("publisher", "publish")
        		.param("publishDate", "2015/8/25")
        		.param("content", "Test"));

        //エラー
        resultActions.andExpect(model().hasErrors());
	}
}

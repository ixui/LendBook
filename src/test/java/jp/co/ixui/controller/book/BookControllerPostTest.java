package jp.co.ixui.controller.book;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import jp.co.ixui.LoginUserDetails;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.mapper.LendMapper;
import jp.co.ixui.mapper.MstBookStockMapper;
import jp.co.ixui.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerPostTest {

	@Autowired
	private WebApplicationContext context; //アプリケーションの設定等を管理するコンテキスト

	private MockMvc mockMvc; //リクエストとレスポンスとそれに付属する情報のオブジェクト

	BookService service;

	BookController controller;

	LendMapper lendMapper;

	MstBookStockMapper mstBookStockMapper;

	//事前処理
	@Before
	public void 事前処理() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context) //モックの初期化
        		.build();

	}

	//正常
	@Test
	@Transactional
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

	@Test
	@Transactional
	public void 認証テスト() throws Exception{

		String isbn = "123-1230123011";
		int bookStockId = 10;

		//ログインユーザー
		MstEmp loginUser = new MstEmp();
		loginUser.setEmpName("test");
		loginUser.setAdminFrag("1");
		loginUser.setEmpNum(9999);
		loginUser.setMailAddress("test@gmail.com");
		loginUser.setPassword("aaaa");

		//蔵書
		MstBookStock mockBookStock = new MstBookStock();
		mockBookStock.setBookStockId(bookStockId);
		mockBookStock.setIsbn(isbn);
		mockBookStock.setOwnerEmpNum(9999);

	    //認証処理
		LoginUserDetails user = new LoginUserDetails(loginUser);
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		//登録処理
	    ResultActions resultActions = mockMvc.perform(post("/admin/book")
	    		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
	    		.param("bookName", "TEST")
	    		.param("author", "author")
	    		.param("publisher", "publish")
	    		.param("isbn", isbn)
	    		.param("publishDate", "2015/8/25")
	    		.param("content", "Test"));

	    //貸出完了
		mockMvc.perform(post("/reserve/{isbn}", isbn)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("returnDueDate", "2017/1/13"))
			.andExpect(model().hasNoErrors());
	}
}
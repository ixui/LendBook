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
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.service.BookService;

/**
 * {@link BookController}のユニットテストです。<br>
 * POSTメソッドに正常にアクセスできるかを確認しています
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerPostTest {

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
	 * テスト対象クラス
	 */
	@InjectMocks
	BookController controller;

	/**
	 * 書籍の処理クラス<br>
	 * モック
	 */
	@Mock
	BookService service;

	/**
	 * 書籍<br>
	 * モック
	 */
	@Mock
	MstBook mstBook;

	/**
	 * 蔵書
	 */
	MstBookStock mstBookStock = new MstBookStock();

	/**
	 * 社員
	 */
	MstEmp mstEmp = new MstEmp();

	/**
	 * MockMvcの初期設定<br>
	 * ユーザ/蔵書の値を設定
	 */
	@Before
	public void 事前処理() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context) //モックの初期化
        		.build();

    	//ユーザ
		mstEmp.setEmpName("test");
		mstEmp.setAdminFrag("1");
		mstEmp.setEmpNum(5010);
		mstEmp.setMailAddress("test@tosyo.co.jp");
		mstEmp.setPassword("test");

		//蔵書
		mstBookStock.setBookStockId(10);
		mstBookStock.setIsbn("123-1230123011");
		mstBookStock.setOwnerEmpNum(9999);
	}

	/**
	 * 書籍の登録ページからPOSTができるかを確認しています
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void 書籍登録フォーム送信できるか確認() throws Exception{

	LoginUserDetails user = new LoginUserDetails(mstEmp);
	Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
	SecurityContextHolder.getContext().setAuthentication(auth);

	this.mockMvc.perform(post("/admin/book")
			.param("author",  "author")
			.param("publisher", "publishe")
			.param("publishDate", "2015/8/25")
			.param("content", "Test")
			.param("isbn",  "132-1234567890")
			.param("bookName", "TEST"))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/admin/book"))
    		.andExpect(model().hasNoErrors());
	}

	/**
	 * 貸出ページからPOSTし、完了画面への遷移が行われているか確認しています。
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void 貸出完了後の画面遷移の確認() throws Exception{

		String isbn = "123-1230123011";

	    //認証処理
		LoginUserDetails user = new LoginUserDetails(mstEmp);
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		//登録処理
	    ResultActions resultActions = mockMvc.perform(post("/admin/book")
	    		.param("bookName", "TEST")
	    		.param("author", "author")
	    		.param("publisher", "publish")
	    		.param("isbn", isbn)
	    		.param("publishDate", "2015/8/25")
	    		.param("content", "Test"));

	    //貸出完了
		mockMvc.perform(post("/reserve/{isbn}", isbn)
				.param("returnDueDate", "2017/1/13"))
			.andExpect(status().isOk())
			.andExpect(model().hasNoErrors());
	}
}
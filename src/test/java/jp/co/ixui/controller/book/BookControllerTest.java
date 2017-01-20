package jp.co.ixui.controller.book;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jp.co.ixui.LoginUserDetails;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.mapper.MstBookStockMapper;
import jp.co.ixui.service.BookService;

/**
 * {@link BookController}のユニットテストです。<br>
 * GETメソッドに正常にアクセスできるかを確認しています。
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

	/**
	 * テスト対象クラス
	 */
	@InjectMocks
	BookController controller;

	/**
     * モックオブジェクト
     */
	private MockMvc mockMvc;

	/**
	 * 書籍の処理クラス<br>
	 * モック
	 */
	@Mock
	BookService service;

	/**
	 * 蔵書用マッパー<br>
	 * モック
	 */
	@Mock
	MstBookStockMapper mstBookStockMapper;

	/**
	 * MockMvcの初期設定<br>
	 */
	@Before
	public void 事前準備() throws Exception{
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller)
        		.build();
	}

	/**
	 * 書籍登録ページへアクセスします
	 * @throws Exception
	 */
	@Test
	public void 書籍登録ページへアクセステスト() throws Exception{

		mockMvc.perform(get("/admin/book"))
			.andExpect(status().isOk());
	}

	/**
	 * 個別の書籍ページへアクセスします。<br>
	 * 書籍情報を渡してあげて画面に表示します。
	 * @throws Exception
	 */
	@Test
	public void 書籍ページアクセステスト() throws Exception{
		String isbn = "999-9876543210";

		MstBook bookDetail = new MstBook();
		bookDetail.setAuthor("author");
		bookDetail.setBookName("bookName");
		bookDetail.setContent("content");
		bookDetail.setIsbn(isbn);
		bookDetail.setPublishDate("2000/10/10");
		bookDetail.setPublisher("publisher");

		when(service.getBook(isbn)).thenReturn(bookDetail);

		mockMvc.perform(get("/book/{isbn}", isbn))
			.andExpect(status().isOk());
	}

	/**
	 * 貸出の予約画面へアクセスします。<br>
	 * 書籍情報/蔵書情報/借りるユーザの情報を渡してあげて画面に表示します。
	 * @throws Exception
	 */
	@Test
	public void 貸出予約画面アクセステスト() throws Exception{
		String isbn = "123-1234561230";

		MstBook bookDetail = new MstBook();
		bookDetail.setAuthor("author");
		bookDetail.setBookName("bookName");
		bookDetail.setContent("content");
		bookDetail.setIsbn(isbn);
		bookDetail.setPublishDate("2000/10/10");
		bookDetail.setPublisher("publisher");

		MstBookStock mstBookStock = new MstBookStock();
		mstBookStock.setBookStockId(50);
		mstBookStock.setOwnerEmpNum(9999);


		LoginUserDetails user = mock(LoginUserDetails.class);

		when(service.getBook(isbn)).thenReturn(bookDetail);
		when(service.getBookStock(isbn)).thenReturn(mstBookStock);

		mockMvc.perform(get("/reserve/{isbn}", isbn))
			.andExpect(status().isOk());
	}
}
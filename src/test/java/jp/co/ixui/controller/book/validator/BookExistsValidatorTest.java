package jp.co.ixui.controller.book.validator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.controller.book.BookAdminForm;
import jp.co.ixui.mapper.MstBookMapper;
import jp.co.ixui.service.BookService;

/**
 * {@link BookExistsValidator}のユニットテストです。<br>
 * 新規で書籍登録の際に既に本が登録されているかどうか、<br>
 * バリデーションでチェックを行っています。
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookExistsValidatorTest {

	/**
	 * テスト対象クラス
	 */
	@InjectMocks
	private BookExistsValidator bookExistsValidator;

	/**
	 * バリデーションの設定
	 */
	private ConstraintValidatorContext context;

	/**
	 * 書籍マッパー<br>
	 * モック
	 */
	@Mock
	private MstBookMapper mstBookMapper;

	/**
	 * 書籍の処理クラス<br>
	 * モック
	 */
	@Mock
	BookService service;

	/**
	 * 登録されていない書籍を登録して、<br>
	 * 既に書籍が登録されていないかを確認しています。
	 * @throws Exception
	 */
	@Test
	public void 本が存在しているかテスト() throws Exception{

		//本の値をセット
		BookAdminForm bookAdminForm = new BookAdminForm();
		bookAdminForm.setAuthor("author");
		bookAdminForm.setBookName("TEST");
		bookAdminForm.setContent("Test");
		bookAdminForm.setIsbn("123-1234567890");
		bookAdminForm.setPublishDate("1995/8/10");
		bookAdminForm.setPublisher("publish");

		when(service.isBookRegistered(bookAdminForm.getIsbn())).thenReturn(true);

		assertEquals(true, bookExistsValidator.isValid(bookAdminForm, context));
	}
}

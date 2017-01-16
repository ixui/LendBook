package jp.co.ixui.controller.book;

import static org.mockito.Mockito.*;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.controller.book.validator.BookExistsValidator;
import jp.co.ixui.mapper.MstBookMapper;
import jp.co.ixui.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookExistsValidatorTest {

	@InjectMocks
	private BookExistsValidator bookExistsValidator;

	private ConstraintValidatorContext context;

	@Mock
	private MstBookMapper mstBookMapper;

	@Mock
	BookService service;

	//正常
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

		when(service.isBookResgitered(bookAdminForm.getIsbn())).thenReturn(true);

		//ISBN認証
		bookExistsValidator.isValid(bookAdminForm, context);
	}
}

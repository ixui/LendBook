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

import jp.co.ixui.service.BookDisplayService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InDividualBookTest {

	@InjectMocks
	BookController controller;

	@Mock
	BookDisplayService service;

	private MockMvc mockMvc;

	@Before
	public void 事前処理() throws Exception{
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller)
        		.build();
	}

	@Test
	public void 書籍ページアクセステスト() throws Exception{
		String isbn = "999-9876543210";

		BookAdminForm bookDetail = new BookAdminForm();
		bookDetail.setAuthor("author");
		bookDetail.setBookName("bookName");
		bookDetail.setContent("content");
		bookDetail.setIsbn(isbn);
		bookDetail.setPublishDate("2000/10/10");
		bookDetail.setPublisher("publisher");

		when(service.selectBook(isbn)).thenReturn(bookDetail);

		mockMvc.perform(get("/book/{isbn}", isbn))
			.andExpect(status().isOk());
	}
}

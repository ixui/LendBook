package jp.co.ixui.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.domain.MstBook;
import jp.co.ixui.mapper.MstBookMapper;

@RunWith(SpringRunner.class)
public class BookDisplayServiceTest {

    @Mock
    private MstBookMapper mapper;

    @InjectMocks
    private BookService service;

    MstBook mstBook = new MstBook();

    @Before
    public void before(){
    	mstBook.setAuthor("test");
    	mstBook.setBookName("book");
    	mstBook.setContent("content");
    	mstBook.setIsbn("123-1234567890");
    	mstBook.setPublishDate("2010/8/1");
    	mstBook.setPublisher("publisher");
    	mstBook.setRegistTime("2016/12/28");
    }

	@Test
	public void 書籍取得(){

		//取得書籍数
		int newbook = 1;

		List<MstBook> list = new ArrayList<MstBook>();
		list.add(mstBook);
		//selectNewBookの戻り値をlistに指定
		when(service.selectNewBook(newbook)).thenReturn(list);

		//取得した書籍数が正しいか確認
		assertEquals(newbook,service.selectNewBook(newbook).size());
	}

}
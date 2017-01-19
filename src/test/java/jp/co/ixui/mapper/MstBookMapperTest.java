package jp.co.ixui.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ixui.domain.MstBook;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MstBookMapperTest {

	@Autowired
	MstBookMapper mapper;

	MstBook mstBook = new MstBook();

	@Before
	public void 事前準備(){
    	//書籍
    	mstBook.setAuthor("test");
    	mstBook.setBookName("book");
    	mstBook.setContent("content");
    	mstBook.setIsbn("123-1234567890");
    	mstBook.setPublishDate("2010/8/1");
    	mstBook.setPublisher("publisher");
	}

	@Test
	@Transactional
	public void 書籍登録と取得(){
		mapper.registerBook(mstBook);
		MstBook getBook = mapper.getBook(mstBook.getIsbn());

		assertEquals("test", getBook.getAuthor());
		assertEquals("123-1234567890", getBook.getIsbn());
	}

	@Test
	@Transactional
	public void 新着書籍取得(){
		int limit = 2;

		MstBook newbook = new MstBook();
		BeanUtils.copyProperties(mstBook, newbook);
		newbook.setIsbn("999-9876543210");

		mapper.registerBook(mstBook);
		mapper.registerBook(newbook);

		List<MstBook> books = mapper.getNewlyBook(limit);

		assertEquals(2, books.size());
	}
}

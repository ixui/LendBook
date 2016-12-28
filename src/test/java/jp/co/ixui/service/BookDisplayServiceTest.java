package jp.co.ixui.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.domain.MstBook;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDisplayServiceTest {

	@Autowired
	BookDisplayService bookDisplayService;

	@Test
	public void 書籍取得(){
		int newbook = 1;
		List<MstBook> list = bookDisplayService.selectNewBook(newbook);

		assertEquals(1,list.size());
	}
}

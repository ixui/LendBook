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

/**
 * {@link MstBookMapper}のユニットテストです。<br>
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MstBookMapperTest {

	/**
	 * 蔵書用マッパー
	 */
	@Autowired
	MstBookMapper mapper;

	/**
	 * 書籍
	 */
	MstBook mstBook = new MstBook();

	/**
	 * 書籍の値を設定しています。
	 */
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

	/**
	 * {@link MstBookMapper#getBook(String)}が正常に書籍の登録を行っているか確認しています。<br>
	 */
	@Test
	@Transactional
	public void 書籍登録と取得(){

		//書籍登録と取得
		mapper.registerBook(mstBook);
		MstBook getBook = mapper.getBook(mstBook.getIsbn());

		assertEquals("test", getBook.getAuthor());
		assertEquals("123-1234567890", getBook.getIsbn());
	}

	/**
	 * {@link MstBookMapper#getNewlyBook(int)}が正常に書籍を取得しているかを確認しています。<br>
	 * 登録日が同じになってしまうので日付順には並びません。<br>
	 * 登録日が同じ場合はISBN順に並んでいます。
	 *
	 */
	@Test
	@Transactional
	public void 新着書籍取得(){
		int limit = 2;

		//二冊目の書籍を作成
		MstBook newbook = new MstBook();
		BeanUtils.copyProperties(mstBook, newbook);
		newbook.setIsbn("999-9876543210");

		//二冊の書籍を登録
		mapper.registerBook(mstBook);
		mapper.registerBook(newbook);

		//書籍をリストにして取得
		List<MstBook> books = mapper.getNewlyBook(limit);

		assertEquals(2, books.size());
		assertEquals("123-1234567890", books.get(0).getIsbn());
		assertEquals("999-9876543210", books.get(1).getIsbn());
	}
}

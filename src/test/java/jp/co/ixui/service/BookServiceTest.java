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

import jp.co.ixui.domain.Lend;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.mapper.LendMapper;
import jp.co.ixui.mapper.MstBookMapper;
import jp.co.ixui.mapper.MstBookStockMapper;

@RunWith(SpringRunner.class)
public class BookServiceTest {

    @Mock
    private MstBookMapper mapper;

    @InjectMocks
    private BookService service;

    MstBook mstBook = new MstBook();

    @Mock
    LendMapper lendMapper;

    @Mock
    MstBookStockMapper mstBookStockMapper;

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
	public void 新規書籍取得(){

		//取得書籍数
		int newbook = 1;

		List<MstBook> list = new ArrayList<MstBook>();
		list.add(mstBook);
		//selectNewBookの戻り値をlistに指定
		when(service.getNewlyBook(newbook)).thenReturn(list);

		//取得した書籍数が正しいか確認
		assertEquals(newbook,service.getNewlyBook(newbook).size());
	}

	@Test
	public void 書籍情報取得(){

		//取得ISBN
		String isbn = "123-1234567890";
		when(service.getBook(isbn)).thenReturn(mstBook);

		assertEquals(isbn, mstBook.getIsbn());
	}

	@Test
	public void 貸出処理(){

		String isbn = "111-9865327845";
		Lend lend = new Lend();
		lend.setEmpNum(5010);

		MstEmp mstEmp = new MstEmp();

		MstBookStock mstBookStock = new MstBookStock();
		mstBookStock.setBookStockId(10);
		mstBookStock.setOwnerEmpNum(9999);
		mstBookStock.setIsbn(isbn);

		when(service.getBookStock(isbn)).thenReturn(mstBookStock);


		service.registerLend(lend, mstEmp, isbn);
	}

    @Test
    public void 貸出可否テスト(){

    	Lend lend = new Lend();
    	lend.setBookStockId(50);

    	when(service.lendMapper.getLendingHistory(lend.getBookStockId())).thenReturn(lend);
    	when(service.lendMapper.getReturnDate(lend.getBookStockId())).thenReturn(lend);

    	service.isLendable(lend.getBookStockId());
    }
}
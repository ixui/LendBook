package jp.co.ixui.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.LoginUserDetails;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.mapper.LendMapper;
import jp.co.ixui.mapper.MstBookMapper;
import jp.co.ixui.mapper.MstBookStockMapper;

/**
 * <p>{@link BookService}のユニットテストです。</p>
 * 書籍に関する処理をテストを行っています。
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
public class BookServiceTest {

	/**
	 * テスト対象クラス
	 */
    @InjectMocks
    private BookService service;

    /**
     * 貸出用マッパー<br>
     * モック
     */
    @Mock
    LendMapper lendMapper;

    /**
     * 書籍用マッパー<br>
     * モック
     */
    @Mock
    private MstBookMapper mstBookMapper;

    /**
     * 蔵書用マッパー<br>
     * モック
     */
    @Mock
    MstBookStockMapper mstBookStockMapper;

    /**
     * 書籍
     */
    MstBook mstBook = new MstBook();

    /**
     * 蔵書
     */
    MstBookStock mstBookStock = new MstBookStock();

    /**
     * ユーザ
     */
    MstEmp mstEmp = new MstEmp();

    /**
     * 貸出
     */
    Lend lend = new Lend();

    @Before
    public void before(){
    	//書籍
    	mstBook.setAuthor("test");
    	mstBook.setBookName("book");
    	mstBook.setContent("content");
    	mstBook.setIsbn("123-1234567890");
    	mstBook.setPublishDate("2010/8/1");
    	mstBook.setPublisher("publisher");
    	mstBook.setRegistTime("2016/12/28");

    	//蔵書
    	mstBookStock.setBookStockId(1);
    	mstBookStock.setIsbn("123-1234567890");
    	mstBookStock.setOwnerEmpNum(9999);

    	//ユーザ
		mstEmp.setEmpName("test");
		mstEmp.setAdminFrag("0");
		mstEmp.setEmpNum(5010);
		mstEmp.setMailAddress("test@tosyo.co.jp");
		mstEmp.setPassword("test");
    }

    /**
     * {@link BookService#registerBook(MstBook, MstBookStock, LoginUserDetails)}のテストです。<br>
     * 書籍と蔵書に書籍登録に必要な情報が格納されているかを確認しています。
     */
    @Test
    public void 書籍登録時にユーザ情報が格納されているか確認(){

    	LoginUserDetails user = new LoginUserDetails(mstEmp);
    	service.registerBook(mstBook, mstBookStock, user);

    	assertEquals(5010, mstBook.getRegistEmpNum());
    	assertEquals(5010, mstBookStock.getRegistEmpNum());
    }

    /**
     * {@link BookService#getBook(String)}のテストです。<br>
     * 蔵書の情報が正しく取得できるかを確認しています。
     */
    @Test
	public void 蔵書情報が取得できるか確認(){
    	when(mstBookStockMapper.getBookStock(anyString())).thenReturn(mstBookStock);

		MstBookStock getBookStock = service.getBookStock(mstBookStock.getIsbn());
		assertEquals("123-1234567890", getBookStock.getIsbn());
		assertEquals(1, getBookStock.getBookStockId());
	}

    /**
     * {@link BookService#getNewlyBook(int)}のテストです。<br>
     * 新着書籍を複数冊取得し、正しく情報が格納されているか確認しています。
     */
	@Test
	public void 新着書籍が取得できるか確認(){
		int limit = 2;
		MstBook newMstBook = new MstBook();
		BeanUtils.copyProperties(mstBook, newMstBook);
		newMstBook.setBookName("aiueo");

		List<MstBook> bookList = new ArrayList<MstBook>();
		bookList.add(mstBook);
		bookList.add(newMstBook);

		when(mstBookMapper.getNewlyBook(limit)).thenReturn(bookList);
		service.getNewlyBook(limit);
		assertEquals(mstBook, bookList.get(0));
		assertEquals(newMstBook, bookList.get(1));
		assertEquals("aiueo", bookList.get(1).getBookName());
	}

	/**
	 * {@link BookService#getBook(String)}のテストです。<br>
	 * 書籍の情報が正しく取得できるかを確認しています。
	 */
	@Test
	public void 書籍情報が取得できるか確認(){

		when(mstBookMapper.getBook(anyString())).thenReturn(mstBook);

		MstBook getBook = service.getBook(mstBook.getIsbn());
		assertEquals("123-1234567890", getBook.getIsbn());
		assertEquals("test", getBook.getAuthor());
		assertEquals("book", getBook.getBookName());
	}

	/**
	 * {@link BookService#registerLend(Lend, MstEmp, String)}のテストです。<br>
	 * 貸出用のドメインに貸出処理に必要な情報が正しく入っているかを確認しています。
	 */
	@Test
	public void 貸出処理のための必要な値が正確に入っているか確認(){

		doNothing().when(lendMapper).registerLend(any());
		when(service.getBookStock(anyString())).thenReturn(mstBookStock);

		service.registerLend(lend, mstEmp, "123-1234567890");
		assertEquals(5010, lend.getEmpNum());
		assertEquals(1, lend.getBookStockId());
		assertEquals(9999, lend.getOwnerEmpNum());
	}

	/**
	 * {@link BookService#isLendable(int)}のテストです。<br>
	 * 貸出履歴が存在していない書籍を貸出できる(true)かを確認しています。
	 */
	@Test
	public void 貸出履歴が存在していない書籍の貸出可否の確認(){

		when(lendMapper.getLendingHistory(anyInt())).thenReturn(null);
		service.isLendable(1);
		assertEquals(true, service.isLendable(1));
		assertEquals(null, lendMapper.getLendingHistory(anyInt()));
	}

	/**
	 * {@link BookService#isLendable(int)}のテストです。<br>
	 * 返却されている書籍を貸出できる(true)かを確認しています。<br>
	 */
	@Test
	public void 返却済みの書籍の貸出可否の確認(){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = sdf.format(cal.getTime());
        lend.setReturnDate(strDate);

		when(lendMapper.getLendingHistory(anyInt())).thenReturn(lend);
		when(lendMapper.getReturnDate(anyInt())).thenReturn(lend);

		assertEquals(true, service.isLendable(1));
	}

	/**
	 * {@link BookService#isLendable(int)}のテストです。<br>
	 * 返却されていない書籍は貸出できない(false)かを確認しています。
	 */
	@Test
	public void 返却されていない書籍の貸出可否の確認(){

        lend.setReturnDate(null);

		when(lendMapper.getLendingHistory(anyInt())).thenReturn(lend);
		when(lendMapper.getReturnDate(anyInt())).thenReturn(lend);

		assertEquals(false, service.isLendable(1));
	}

	/**
	 * {@link BookService#isBookRegistered(String)}のテストです。<br>
	 * 重複している書籍は登録できない(false)かを確認しています。
	 */
    @Test
    public void 書籍の重複確認(){

    	when(mstBookMapper.getBook(anyString())).thenReturn(mstBook);

    	assertEquals(false, service.isBookRegistered(anyString()));
    }

    /**
     * {@link BookService#isReturnDueDateOver(String)}のテストです。<br>
     * 返却予定日が60日を超えている場合は貸出できない(false)かを確認しています
     */
    @Test
    public void 返却予定日が超えていた時の確認(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = sdf.format(cal.getTime());

    	assertEquals(false, service.isReturnDueDateOver(strDate));
    }

    /**
     * {@link BookService#isReturnDueDateOver(String)}のテストです。<br>
     * 返却予定日が空の場合を確認しています。<br>
     * {@link BookService#isReturnDueDateOver(String)}では、trueを返し別のバリデーションでチェックをしています。
     */
    @Test
    public void 返却予定日が入力されていない時の確認(){

    	assertEquals(true, service.isReturnDueDateOver(""));
    }

    /**
     * {@link BookService#isReturnDueDateOver(String)}のテストです。<br>
     * 返却予定日が不正な値(yyyy/MM/dd以外)だった場合を確認しています。<br>
     * {@link BookService#isReturnDueDateOver(String)}では、trueを返し別のバリデーションでチェックをしています。
     */
    @Test
    public void 返却予定日に不正な値が入力されている場合(){
    	assertEquals(true, service.isReturnDueDateOver("2017-8/5"));
    }
}
package jp.co.ixui.mapper;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ixui.domain.Lend;
import jp.co.ixui.domain.MstBookStock;

/**
 * {@link LendMapper}のユニットテストです。<br>
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LendMapperTest {

	/**
	 * 貸出用マッパー
	 */
	@Autowired
	LendMapper mapper;

	/**
	 * 蔵書用マッパー
	 */
	@Autowired
	MstBookStockMapper mstBookStockMapper;

	/**
	 * 貸出
	 */
	Lend lend = new Lend();

	/**
	 * 蔵書
	 */
	MstBookStock mstBookStock = new MstBookStock();

	/**
	 * 日付
	 */
    Calendar cal = Calendar.getInstance();

    /**
     * 日付（yyyy/MM/dd）フォーマット
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * (yyyy/MM/dd)フォーマット形式の今日の日付
     */
    String nowDate = sdf.format(cal.getTime());

    /**
     * 2カ月後の日付/貸出/蔵書の値を設定しています。
     */
	@Before
	public void 事前準備(){

		//2カ月後の日付
        cal.add(Calendar.MONTH, 2);
        String afterDate = sdf.format(cal.getTime());

        //貸出
		lend.setBookStockId(10002);
		lend.setEmpNum(5010);
		lend.setLendDate(nowDate);
		lend.setOwnerEmpNum(9999);
		lend.setReturnDueDate(afterDate);
		lend.setReturnDate(null);

    	//蔵書
		mstBookStock.setBookStockId(10002);
    	mstBookStock.setIsbn("123-1234567890");
    	mstBookStock.setOwnerEmpNum(9999);
	}

	/**
	 * {@link LendMapper#registerLend(Lend)}が正常に貸出処理を行うかを確認しています。<br>
	 */
	@Test
	@Transactional
	public void 貸出処理の正常動作確認(){

		//貸出の登録と取得
		mapper.registerLend(lend);
		Lend getLend = mapper.getLendingHistory(lend.getBookStockId());

		assertEquals(9999, getLend.getOwnerEmpNum());
		assertEquals(5010, getLend.getRegistEmpNum());
	}

	/**
	 * {@link LendMapper#getReturnDate(int)}が正常に返却日を取得しているかを確認しています。<br>
	 * このテストでは返却日をnullに指定し取得しています。
	 */
	@Test
	@Transactional
	public void 返却日の取得(){

		//蔵書の登録と取得
    	mstBookStockMapper.registerBookStock(mstBookStock);
    	MstBookStock getBookStock = mstBookStockMapper.getBookStock(mstBookStock.getIsbn());

    	//貸出DBから返却日の取得
    	lend.setBookStockId(getBookStock.getBookStockId());
		mapper.registerLend(lend);
		Lend getLend = mapper.getReturnDate(lend.getBookStockId());

		assertEquals(null, getLend.getReturnDate());
		assertEquals(9999, getLend.getOwnerEmpNum());
		assertEquals(5010, getLend.getEmpNum());
	}
}
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class LendMapperTest {

	@Autowired
	LendMapper mapper;

	@Autowired
	MstBookStockMapper mstBookStockMapper;

	Lend lend = new Lend();

	MstBookStock mstBookStock = new MstBookStock();

    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String nowDate = sdf.format(cal.getTime());

	@Before
	public void 事前準備(){
        cal.add(Calendar.MONTH, 2);
        String afterDate = sdf.format(cal.getTime());

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

	@Test
	@Transactional
	public void 貸出処理の確認(){

		mapper.registerLend(lend);
		Lend getLend = mapper.getLendingHistory(lend.getBookStockId());
		assertEquals(9999, getLend.getOwnerEmpNum());
		assertEquals(5010, getLend.getRegistEmpNum());
	}

	@Test
	@Transactional
	public void 返却日の取得(){

    	mstBookStockMapper.registerBookStock(mstBookStock);
    	MstBookStock getBookStock = mstBookStockMapper.getBookStock(mstBookStock.getIsbn());
    	lend.setBookStockId(getBookStock.getBookStockId());
		mapper.registerLend(lend);
		Lend getLend = mapper.getReturnDate(lend.getBookStockId());
		assertEquals(9999, getLend.getOwnerEmpNum());
		assertEquals(5010, getLend.getEmpNum());
	}
}
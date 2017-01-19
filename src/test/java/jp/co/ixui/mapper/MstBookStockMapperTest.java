package jp.co.ixui.mapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ixui.domain.MstBookStock;

/**
 * {@link MstBookStockMapper}のユニットテストです。<br>
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MstBookStockMapperTest {

	/**
	 * 蔵書用マッパー
	 */
	@Autowired
	MstBookStockMapper mapper;

	/**
	 * 蔵書
	 */
	MstBookStock mstBookStock = new MstBookStock();

	/**
	 * 蔵書の値を設定しています。
	 */
    @Before
    public void before(){
    	//蔵書
    	mstBookStock.setIsbn("123-1234567890");
    	mstBookStock.setOwnerEmpNum(9999);
    }

    /**
     * {@link MstBookStockMapper#getBookStock(String)}が正常に蔵書の登録を行っているか確認しています。<br>
     */
    @Test
    @Transactional
    public void 蔵書の登録と検索(){

    	//蔵書の登録と検索
    	mapper.registerBookStock(mstBookStock);
    	MstBookStock getBookStock = mapper.getBookStock(mstBookStock.getIsbn());

    	assertEquals("123-1234567890", getBookStock.getIsbn());
    	assertEquals(9999, getBookStock.getOwnerEmpNum());
    }
}

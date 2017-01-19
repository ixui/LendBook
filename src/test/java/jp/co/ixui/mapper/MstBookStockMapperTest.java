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

@RunWith(SpringRunner.class)
@SpringBootTest
public class MstBookStockMapperTest {

	@Autowired
	MstBookStockMapper mapper;

	MstBookStock mstBookStock = new MstBookStock();

    @Before
    public void before(){
    	//蔵書
    	mstBookStock.setIsbn("123-1234567890");
    	mstBookStock.setOwnerEmpNum(9999);
    }

    @Test
    @Transactional
    public void 蔵書の登録と検索(){

    	mapper.registerBookStock(mstBookStock);
    	MstBookStock getBookStock = mapper.getBookStock(mstBookStock.getIsbn());

    	assertEquals("123-1234567890", getBookStock.getIsbn());
    	assertEquals(9999, getBookStock.getOwnerEmpNum());
    }
}

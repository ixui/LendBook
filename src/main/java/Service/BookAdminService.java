package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.mapper.MstBookMapper;
import jp.co.ixui.mapper.MstBookStockMapper;

public class BookAdminService {
	@Autowired
	MstBookMapper mstBookMapper;

	@Autowired
	MstBookStockMapper mstBookStockMapper;

	@Transactional
	public void insertBook(MstBook mstBook, MstBookStock mstBookStock){
		mstBookMapper.insertBook(mstBook);
		mstBookStockMapper.insertBookStock(mstBookStock);
	}
}

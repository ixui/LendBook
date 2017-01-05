package jp.co.ixui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.mapper.MstBookMapper;
import jp.co.ixui.mapper.MstBookStockMapper;

@Service
public class BookService {

	@Autowired
	MstBookMapper mstBookMapper;

	@Autowired
	MstBookStockMapper mstBookStockMapper;

	@Transactional
	public void insertBook(MstBook mstBook, MstBookStock mstBookStock){
		mstBookMapper.insertBook(mstBook);
		mstBookStockMapper.insertBookStock(mstBookStock);
	}

	//蔵書マスターに登録
	public void insertBookStock(MstBookStock mstBookStock){
		mstBookStockMapper.insertBookStock(mstBookStock);
	}

	//新着書籍4件取得
	public List<MstBook> selectNewBook(int newbook){
		return mstBookMapper.selectNewBook(newbook);
	}

	//各書籍画面表示
	public MstBook selectBook(String isbn){
		return mstBookMapper.selectBook(isbn);
	}
}
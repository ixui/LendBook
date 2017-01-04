package jp.co.ixui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ixui.domain.Lend;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.mapper.LendMapper;
import jp.co.ixui.mapper.MstBookMapper;
import jp.co.ixui.mapper.MstBookStockMapper;

@Service
public class BookService {

	@Autowired
	MstBookMapper mstBookMapper;

	@Autowired
	MstBookStockMapper mstBookStockMapper;

	@Autowired
	LendMapper lendMapper;

	public void insertBook(MstBook mstBook){
		//オブジェクトに入れたものをmapperを使ってINSERT
		mstBookMapper.insertBook(mstBook);
	}

	//蔵書マスターに登録
	public void insertBookStock(MstBookStock mstBookStock){
		mstBookStockMapper.insertBookStock(mstBookStock);
	}

	//蔵書検索
	public MstBookStock selectBookStock(String isbn){
		return mstBookStockMapper.selectBookStock(isbn);
	}

	//新着書籍4件取得
	public List<MstBook> selectNewBook(int newbook){
		return mstBookMapper.selectNewBook(newbook);
	}

	//各書籍画面表示
	public MstBook selectBook(String isbn){
		return mstBookMapper.selectBook(isbn);
	}

	//貸出処理
	public void insertLend(Lend lend, MstEmp mstEmp,String isbn){
		lend.setEmpNum(mstEmp.getEmpNum());
		MstBookStock mstBookStcok = selectBookStock(isbn);
		lend.setBookStockId(mstBookStcok.getBookStockId());
		lend.setOwnerEmpNum(mstBookStcok.getOwnerEmpNum());
		lendMapper.insertLend(lend);
	}
}

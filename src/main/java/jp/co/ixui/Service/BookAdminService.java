package jp.co.ixui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ixui.domain.MstBook;
import jp.co.ixui.domain.MstBookStock;
import jp.co.ixui.mapper.MstBookMapper;
import jp.co.ixui.mapper.MstBookStockMapper;

@Service
public class BookAdminService {

	@Autowired
	MstBookMapper mstBookMapper;

	@Autowired
	MstBookStockMapper mstBookStockMapper;

	public void insertBook(MstBook mstBook){
		//オブジェクトに入れたものをmapperを使ってINSERT
		mstBookMapper.insertBook(mstBook);
	}

	//蔵書マスターに登録
	public void insertBookStock(MstBookStock mstBookStock){
		mstBookStockMapper.insertBookStock(mstBookStock);
	}
}

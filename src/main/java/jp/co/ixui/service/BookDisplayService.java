package jp.co.ixui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ixui.domain.MstBook;
import jp.co.ixui.mapper.MstBookMapper;

@Service
public class BookDisplayService {

	@Autowired
	MstBookMapper mstBookMapper;

	//新着書籍4件取得
	public List<MstBook> selectNewBook(int newbook){
		return mstBookMapper.selectNewBook(newbook);
	}

	//各書籍画面表示
	public MstBook selectBook(String isbn){
		return mstBookMapper.selectBook(isbn);
	}
}
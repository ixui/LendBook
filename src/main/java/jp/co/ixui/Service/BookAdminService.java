package jp.co.ixui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ixui.domain.MstBook;
import jp.co.ixui.mapper.MstBookMapper;

@Service
public class BookAdminService {

	@Autowired
	MstBookMapper mstBookMapper;

	public void insertBook(MstBook mstBook){
		mstBookMapper.insertBook(mstBook);	//オブジェクトに入れたものをmapperを使ってINSERT
	}
}

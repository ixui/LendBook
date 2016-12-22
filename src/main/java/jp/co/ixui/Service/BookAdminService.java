package jp.co.ixui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ixui.controller.book.BookAdminForm;
import jp.co.ixui.domain.MstBook;
import jp.co.ixui.mapper.MstBookMapper;

@Service
public class BookAdminService {

	@Autowired
	MstBookMapper mstBookMapper;

	//書籍登録
	public void insertBook(BookAdminForm bookAdminForm){
		MstBook mstBook = new MstBook();	//MstBookオブジェクト生成
		mstBook.setBookName(bookAdminForm.getBookName());	//書籍名
		mstBook.setAuthor(bookAdminForm.getAuthor());		//著者
		mstBook.setPublisher(bookAdminForm.getPublisher());	//出版社
		mstBook.setPublishDate(bookAdminForm.getPublishDate());	//出版日
		mstBook.setIsbn(bookAdminForm.getIsbn());	//ISBN
		mstBook.setContent(bookAdminForm.getContent());	//内容
		mstBookMapper.insertBook(mstBook);	//オブジェクトに入れたものをmapperを使ってINSERT
	}
}

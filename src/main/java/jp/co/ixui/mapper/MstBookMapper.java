package jp.co.ixui.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.MstBook;

@Mapper
public interface MstBookMapper {

	//書籍の登録
	void registerBook(MstBook mstBook);

	//ISBNから書籍の検索
	MstBook getBook(String isbn);

	//新着書籍取得
	List<MstBook> getNewlyBook(int limit);
}
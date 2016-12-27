package jp.co.ixui.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.MstBook;

@Mapper
public interface MstBookMapper {

	//書籍の登録
	void insertBook(MstBook mstBook);

	//ISBNから書籍の検索
	MstBook selectBook(String isbn);

	//新着書籍4件取得
	List<MstBook> selectFourNewBook();

}

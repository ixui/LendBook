package jp.co.ixui.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.MstBook;

@Mapper
public interface MstBookMapper {

	//書籍の登録
	void insertBook(MstBook mstBook);
}

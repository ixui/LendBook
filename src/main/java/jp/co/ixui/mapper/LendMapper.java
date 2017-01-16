package jp.co.ixui.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.Lend;

@Mapper
public interface LendMapper {

	//貸出処理
	void registerLend(Lend lend);

	//返却されているかを取得
	Lend getReturnDate(int bookStockId);

	//貸出履歴を検索
	Lend getLendingHistory(int bookStockId);
}

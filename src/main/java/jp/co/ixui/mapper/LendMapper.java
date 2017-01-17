package jp.co.ixui.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.Lend;

/**
 * 貸出用マッパー
 * @author NAKAJIMA
 *
 */
@Mapper
public interface LendMapper {

	/**
	 * 書籍の貸出処理を行います。
	 * @param lend 貸出情報
	 */
	//貸出処理
	void registerLend(Lend lend);

	/**
	 * 借りようとしている蔵書の情報を取得します。<br>
	 * @param bookStockId 借りようとしている蔵書ID
	 * @return Lendに借りる蔵書の情報を返します。
	 */
	//返却されているかを取得
	Lend getReturnDate(int bookStockId);

	/**
	 * 借りようとしている書籍が貸出履歴に存在するかを取得します<br>
	 * @param bookStockId 借りようとしている蔵書ID
	 * @return Lendにかかりる蔵書の情報を返します。
	 */
	//貸出履歴を検索
	Lend getLendingHistory(int bookStockId);
}

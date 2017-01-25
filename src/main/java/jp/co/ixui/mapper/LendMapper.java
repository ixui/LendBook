package jp.co.ixui.mapper;

import java.util.List;

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
	void registerLend(Lend lend);

	/**
	 * 借りようとしている蔵書の情報を取得します。<br>
	 * @param bookStockId 借りようとしている蔵書ID
	 * @return 借りる蔵書の情報を返します。
	 */
	Lend getReturnDate(int bookStockId);

	/**
	 * 借りようとしている書籍が貸出履歴に存在するかを取得します<br>
	 * @param bookStockId 借りようとしている蔵書ID
	 * @return 借りる蔵書の情報を返します。
	 */
	Lend getLendingHistory(int bookStockId);

	//貸出リスト
	List<Lend> getLendingList(int empNum);

	//本の返却
	void returnBook(int lendId);
}

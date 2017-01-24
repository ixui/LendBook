package jp.co.ixui.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.MstBook;

/**
 * 書籍用マッパー
 * @author NAKAJIMA
 *
 */
@Mapper
public interface MstBookMapper {

	/**
	 * <p>書籍の登録をします。</p>
	 * @param mstBook 書籍の情報
	 */
	//書籍の登録
	void registerBook(MstBook mstBook);

	/**
	 * <p>ISBNから書籍の情報を取得します。</p>
	 * @param isbn ISBN-13を使用。
	 * @return 該当書籍の情報を返します。
	 */
	//ISBNから書籍の検索
	MstBook getBook(String isbn);

	/**
	 * <p>新着書籍を取得します</p>
	 * @param limit 取得する書籍数
	 * @return 取得した各書籍の情報を返します。
	 */
	//新着書籍取得
	List<MstBook> getNewlyBook(int limit);

	//貸出本リスト
	List<MstBook> getLendingBookList(int empNum);
}
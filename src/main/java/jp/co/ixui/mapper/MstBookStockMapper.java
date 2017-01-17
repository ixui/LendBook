package jp.co.ixui.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.MstBookStock;

/**
 * 蔵書用マッパー
 * @author NAKAJIMA
 *
 */
@Mapper
public interface MstBookStockMapper {
	/**
	 *  <p>蔵書の登録をします。</p>
	 * @param mstBookStock 蔵書の情報
	 */
	//蔵書の登録
	void registerBookStock(MstBookStock mstBookStock);

	/**
	 * <p>ISBNから蔵書の情報を取得します。</p>
	 * @param isbn ISBN-13を使用。
	 * @return 該当蔵書の情報を返します。
	 */
	//ISBNから蔵書の検索
	MstBookStock getBookStock(String isbn);
}

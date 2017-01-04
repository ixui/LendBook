package jp.co.ixui.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.MstBookStock;

@Mapper
public interface MstBookStockMapper {
	//蔵書の登録
	void insertBookStock(MstBookStock mstBookStock);
}

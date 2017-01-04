package jp.co.ixui.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.domain.Lend;

@Mapper
public interface LendMapper {

	//貸出処理
	void insertLend(Lend lend);
}

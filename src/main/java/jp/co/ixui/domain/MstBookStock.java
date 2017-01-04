package jp.co.ixui.domain;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MstBookStock {

	//蔵書ID
	private int bookStockId;

	//ISBN
	@NotNull
	private String isbn;

	//所有者番号
	private int ownerEmpNum;

	//登録者
	private int registEmpNum;

	//更新者
	private int updateEmpNum;

	//登録日時
	private String registTime;

	//更新日時
	private String updateTime;
}

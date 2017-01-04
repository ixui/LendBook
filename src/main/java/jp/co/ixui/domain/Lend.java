package jp.co.ixui.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lend {

	//貸出id
	private String lendId;

	//蔵書id
	private String bookStockId;

	//社員番号
	private String empNum;

	//所有者番号
	private String ownerEmpNum;

	//貸出日
	private String lendDate;

	//返却予定日
	private String returnDueDate;

	//返却日
	private String returnDate;

	//登録者番号
	private String registEmpNum;

	//更新者番号
	private String updateEmpNum;

	//登録日時
	private String registTime;

	//更新日時
	private String updateTime;
}

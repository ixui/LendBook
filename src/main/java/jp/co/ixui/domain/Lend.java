package jp.co.ixui.domain;



import org.hibernate.validator.constraints.NotEmpty;

import jp.co.ixui.controller.book.validator.annotation.ReturnDueDateOver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ReturnDueDateOver
public class Lend {

	//貸出id
	private int lendId;

	//蔵書id
	private int bookStockId;

	//社員番号
	private int empNum;

	//所有者番号
	private int ownerEmpNum;

	//貸出日
	private String lendDate;

	//返却予定日
	@NotEmpty(message = "返却予定日を入力してください。")
	private String returnDueDate;

	//返却日
	private String returnDate;

	//登録者番号
	private int registEmpNum;

	//更新者番号
	private int updateEmpNum;

	//登録日時
	private String registTime;

	//更新日時
	private String updateTime;
}

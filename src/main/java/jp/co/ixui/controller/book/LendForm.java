package jp.co.ixui.controller.book;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import jp.co.ixui.controller.book.validator.annotation.ReturnDueDateOver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ReturnDueDateOver
public class LendForm {
	//貸出id
	private int lendId;

	//蔵書id
	private int bookStockId;

	//ISBN
	private String isbn;

	//社員番号
	private int empNum;

	//所有者番号
	private int ownerEmpNum;

	//貸出日
	private String lendDate;

	//返却予定日
	@NotEmpty(message = "返却予定日を入力してください。")
	@Pattern(regexp="[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}", message="出版日はyyyy/mm/ddの形式で入力してください。 (例):2016/7/25")
	private String returnDueDate;

	//返却日
	private String returnDate;

	//書籍名
	@NotNull
	@NotEmpty(message = "書籍名を入力してください。")
	private String bookName;

	//著者
	@NotNull
	@NotEmpty(message = "著者を入力してください。")
	private String author;

	//出版日
	@NotNull
	@Pattern(regexp="[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}", message="出版日はyyyy/mm/ddの形式で入力してください。 (例):2016/7/25")
	private String publishDate;

	//出版社
	@NotNull
	@NotEmpty(message = "出版社を入力してください。")
	private String publisher;

	//内容
	@NotNull
	@NotEmpty(message = "書籍の内容を入力してください。")
	private String content;

	//登録者番号
	private int registEmpNum;

	//更新者番号
	private int updateEmpNum;

	//登録日時
	private String registTime;

	//更新日時
	private String updateTime;
}

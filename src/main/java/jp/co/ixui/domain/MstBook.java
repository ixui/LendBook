package jp.co.ixui.domain;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 書籍マスタードメインオブジェクト
 * @author NAKAJIMA
 *
 */
@Getter
@Setter
public class MstBook {

	//ISBN
	@NotNull
	private String isbn;

	//書籍名
	@NotNull
	private String bookName;

	//著者
	@NotNull
	private String author;

	//出版日
	@NotNull
	private String publishDate;

	//出版社
	@NotNull
	private String publisher;

	//内容
	@NotNull
	private String content;

	//登録者
	private String registEmpNum;

	//更新者
	private String updateEmpNum;

	//登録日時
	private String registTime;

	//更新日時
	private String updateTime;
}

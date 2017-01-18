package jp.co.ixui.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

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

	/**
		 * <p>String型 ISBN-13を使用します。<br>
		 * 3桁の数字-10桁の数字を使用してください。<br>
		 * 例: 123-1234567890</p>
		 * Nullは使用できません。
	 */
	@NotNull
	@NotEmpty
	private String isbn;

	/**
	 * <p>書籍の名前</p>
	 * nullは使用できません。<br>
	 */
	@NotNull
	@NotEmpty
	private String bookName;

	/**
	 * <p>著者名</p><br>
	 * <b>複数の著者の入力は非推奨です。</b><br>
	 * Nullは使用できません。
	 */
	@NotNull
	@NotEmpty
	private String author;

	/**
	 * <p>出版日</p>
	 * <p>半角yyyy/MM/ddのフォーマットで入力してください。<br>
	 * 例: 2010/12/24<br>
	 * 全角やyyyy-MM-ddの場合エラーになります。<br>
	 * Nullは使用できません。</p>
	 */
	@NotNull
	@NotEmpty
	private String publishDate;

	/**
	 * <p>出版社</p><br>
	 * Nullは使用できません。
	 */
	@NotNull
	@NotEmpty
	private String publisher;

	/**
	 * <p>書籍の内容</p>
	 * <p>文字数は200文字以下です。<br>
	 * Nullや空文字は使用できません。</p>
	 */
	@NotNull
	@NotEmpty
	private String content;

	/**
	 * <p>登録者番号</p>
	 */
	private int registEmpNum;

	/**
	 * <p>更新者番号</p>
	 * DBの更新したアカウントの社員番号が入ります。
	 */
	private int updateEmpNum;

	/**
	 * <p>登録日時</p>
	 * DBに最初に入力された日の日付が入ります。<br>
	 * 一度入力された後は変更しないでください。
	 */
	private String registTime;

	/**
	 * <p>更新日時</p>
	 * 最初に値が入るのは登録された日付です。<br>
	 * それ以降は返却処理が行われるなどのDB上の処理が行われた時に更新されます。
	 */
	private String updateTime;
}

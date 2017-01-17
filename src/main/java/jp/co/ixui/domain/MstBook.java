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

	/**
	 * <p>String型 ISBN-13を使用します。</p><br>
	 * 例: 123-1234567890<br>
	 */
	//ISBN
	@NotNull
	private String isbn;

	/**
	 * <p>書籍の名前</p><br>
	 * 空文字、Null以外は使えません。<br>
	 */
	//書籍名
	@NotNull
	private String bookName;

	/**
	 * <p>著者名</p><br>
	 * 複数の著者も入力可能できてしまうが、非推奨です。
	 */
	//著者
	@NotNull
	private String author;

	/**
	 * <p>出版日</p><br>
	 * 半角yyyy/MM/ddのフォーマットで入力<br>
	 * 例: 2010/12/24<br>
	 * 全角やyyyy-MM-ddの場合エラーになります。
	 */
	//出版日
	@NotNull
	private String publishDate;

	/**
	 * <p>出版社</p><br>
	 * 空文字、Nullはエラーになります。
	 */
	//出版社
	@NotNull
	private String publisher;

	/**
	 * <p>書籍の内容</p><br>
	 * 文字数は200文字以下です。
	 */
	//内容
	@NotNull
	private String content;

	/**
	 * <p>登録者番号</p>
	 */
	//登録者
	private String registEmpNum;

	/**
	 * <p>更新者番号</p>
	 * DBの更新したアカウントの社員番号が入ります。
	 */
	//更新者
	private String updateEmpNum;

	/**
	 * <p>登録日時</p>
	 * DBに最初に入力された日の日付が入ります。<br>
	 * 一度入力された後は変更しないでください。
	 */
	//登録日時
	private String registTime;

	/**
	 * <p>更新日時</p>
	 * 最初に値が入るのは登録された日付です。<br>
	 * それ以降は返却処理が行われるなどのDB上の処理が行われた時に更新されます。
	 */
	//更新日時
	private String updateTime;
}

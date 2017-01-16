package jp.co.ixui.controller.book;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import jp.co.ixui.controller.book.validator.annotation.BookExists;
import lombok.Getter;
import lombok.Setter;

/**
 * 書籍登録用フォーム
 * 書籍登録フォームで送信された値が格納される。
 * @author NAKAJIMA
 *
 */
@Getter
@Setter
@BookExists
public class BookAdminForm {

		/**
		 * String型 ISBN-13を使用<br>
		 * ISBN-10の場合エラー
		 */
		//ISBN
		@NotNull
		@Pattern(regexp="[0-9]{3}-[0-9]{10}", message="ISBNは「3桁の数字-(ハイフン)10桁の数字」を入力してください。(例):978-4844330868")
		private String isbn;

		/**
		 * 書籍の名前<br>
		 * 空文字、Null以外に指定なし。<br>
		 * 可能であればAmazonで使われている書籍名を利用。
		 */
		//書籍名
		@NotNull
		@NotEmpty(message = "書籍名を入力してください。")
		private String bookName;

		/**
		 * 著者名<br>
		 * 複数の著者も入力可能だが、<br>
		 * 複数名の検索が行えるか不明なので非推奨
		 */
		//著者
		@NotNull
		@NotEmpty(message = "著者を入力してください。")
		private String author;

		/**
		 * 出版日<br>
		 * 半角yyyy/MM/ddのフォーマットで入力<br>
		 * 全角やyyyy-MM-ddの場合エラーになります。
		 */
		//出版日
		@NotNull
		@Pattern(regexp="[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}", message="出版日はyyyy/mm/ddの形式で入力してください。 (例):2016/7/25")
		private String publishDate;

		/**
		 * 出版社<br>
		 * 正式名称を使用してください。
		 */
		//出版社
		@NotNull
		@NotEmpty(message = "出版社を入力してください。")
		private String publisher;

		/**
		 * 書籍の内容<br>
		 * 可能であれば読んだ人間の書籍評が望ましい。<br>
		 * Amazonや公式サイトなどから引用可能であれば使用。
		 */
		//内容
		@NotNull
		@NotEmpty(message = "書籍の内容を入力してください。")
		private String content;

		/**
		 * 貸出状況<br>
		 * 貸出可否のバリデーションチェックで使用。<br>
		 * Trueを返されれば貸出可能
		 */
		//貸出可否
		private Boolean isLendable;
}

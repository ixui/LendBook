package jp.co.ixui.controller.book;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import jp.co.ixui.controller.book.validator.annotation.BookExists;
import lombok.Getter;
import lombok.Setter;

/**
 * <p><b>書籍登録用フォーム</b></p>
 * 書籍登録フォームで送信された値が格納されます。
 * @author NAKAJIMA
 */
@Getter
@Setter
@BookExists
public class BookAdminForm {

		/**
		 * <p>String型 ISBN-13を使用します。</p><br>
		 * 例: 123-1234567890<br>
		 * ISBN-10の場合エラー
		 */
		//ISBN
		@NotNull
		@Pattern(regexp="[0-9]{3}-[0-9]{10}", message="ISBNは「3桁の数字-(ハイフン)10桁の数字」を入力してください。(例):978-4844330868")
		private String isbn;

		/**
		 * <p>書籍の名前</p><br>
		 * 空文字、Null以外は使えません。<br>
		 */
		//書籍名
		@NotNull
		@NotEmpty(message = "書籍名を入力してください。")
		private String bookName;

		/**
		 * <p>著者名</p><br>
		 * 複数の著者も入力可能できてしまうが、非推奨です。
		 */
		//著者
		@NotNull
		@NotEmpty(message = "著者を入力してください。")
		private String author;

		/**
		 * <p>出版日</p><br>
		 * 半角yyyy/MM/ddのフォーマットで入力<br>
		 * 例: 2010/12/24<br>
		 * 全角やyyyy-MM-ddの場合エラーになります。
		 */
		//出版日
		@NotNull
		@Pattern(regexp="[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}", message="出版日はyyyy/mm/ddの形式で入力してください。 (例):2016/7/25")
		private String publishDate;

		/**
		 * <p>出版社</p><br>
		 * 空文字、Nullはエラーになります。
		 */
		//出版社
		@NotNull
		@NotEmpty(message = "出版社を入力してください。")
		private String publisher;

		/**
		 * <p>書籍の内容</p><br>
		 * 文字数は200文字以下です。
		 */
		//内容
		@NotNull
		@NotEmpty(message = "書籍の内容を入力してください。")
		private String content;

		/**
		 * <p>貸出状況</p><br>
		 * 貸出可否のバリデーションチェックで使用します。<br>
		 * Trueを返されれば貸出可能
		 */
		//貸出可否
		private Boolean isLendable;
}

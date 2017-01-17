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
		 * <p>String型 ISBN-13を使用します。<br>
		 * 3桁の数字-10桁の数字を使用してください。<br>
		 * 例: 123-1234567890</p>
		 * Nullは使用できません。
		 */
		//ISBN
		@NotNull
		@Pattern(regexp="[0-9]{3}-[0-9]{10}", message="ISBNは「3桁の数字-(ハイフン)10桁の数字」を入力してください。(例):978-4844330868")
		private String isbn;

		/**
		 * <p>書籍の名前</p>
		 * Nullは使用できません。
		 */
		//書籍名
		@NotNull
		@NotEmpty(message = "書籍名を入力してください。")
		private String bookName;

		/**
		 * <p>著者名</p><br>
		 * <b>複数の著者の入力は非推奨です。</b><br>
		 * Nullは使用できません。
		 */
		//著者
		@NotNull
		@NotEmpty(message = "著者を入力してください。")
		private String author;

		/**
		 * <p>出版日</p>
		 * <p>半角yyyy/MM/ddのフォーマットで入力してください。<br>
		 * 例: 2010/12/24<br>
		 * 全角やyyyy-MM-ddの場合エラーになります。<br>
		 * Nullは使用できません。</p>
		 */
		//出版日
		@NotNull
		@Pattern(regexp="[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}", message="出版日はyyyy/mm/ddの形式で入力してください。 (例):2016/7/25")
		private String publishDate;

		/**
		 * <p>出版社</p>
		 * Nullは使用できません。
		 */
		//出版社
		@NotNull
		@NotEmpty(message = "出版社を入力してください。")
		private String publisher;

		/**
		 * <p>書籍の内容</p>
		 * <p>文字数は200文字以下です。<br>
		 * Nullや空文字は使用できません。</p>
		 */
		//内容
		@NotNull
		@NotEmpty(message = "書籍の内容を入力してください。")
		private String content;

		/**
		 * <p>貸出状況</p>
		 * <p>貸出可否のバリデーションチェックで使用します。<br>
		 * Trueを返されれば貸出可能です。</p>
		 */
		//貸出可否
		private Boolean isLendable;
}

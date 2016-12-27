package jp.co.ixui.controller.book;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import jp.co.ixui.controller.book.validator.annotation.BookExists;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@BookExists
public class BookAdminForm {

		//ISBN
		@NotNull
		@Pattern(regexp="[0-9]{3}-[0-9]{10}", message="ISBNは「3桁の数字-(ハイフン)10桁の数字」を入力してください。(例):978-4844330868")
		private String isbn;

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
}

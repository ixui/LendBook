package jp.co.ixui.controller.book;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookAdminForm {
		//ISBN
		@NotNull
		@NotEmpty(message = "ISBNを入力してください。")
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
		@NotEmpty(message = "出版日を入力してください。")
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

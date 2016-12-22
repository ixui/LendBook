package jp.co.ixui.controller.book;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookAdminForm {
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
}

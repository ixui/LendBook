package jp.co.ixui.controller.book.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import jp.co.ixui.controller.book.validator.BookExistsValidator;

/**
 * 書籍が存在しているか判定するアノテーション
 * @author NAKAJIMA
 *
 */
@Constraint(validatedBy = BookExistsValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BookExists {

	//表示するメッセージ
	String message() default "既に同じ書籍が登録されています。";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.TYPE, ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		BookExists[] value();
	}
}

package jp.co.ixui.controller.book.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import jp.co.ixui.controller.book.validator.LendAvailableValidator;

/**
 * 貸出ができるかを判別するアノテーション
 * @author NAKAJIMA
 *
 */
@Constraint(validatedBy = LendAvailableValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LendAvailable {

	//表示するメッセージ
	String message() default "書籍は他の方へ貸出中です。";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.TYPE, ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		LendAvailable[] value();
	}
}
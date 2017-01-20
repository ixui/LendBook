package jp.co.ixui.controller.book.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import jp.co.ixui.controller.book.validator.ReturnDueDateOverValidator;

/**
 * 貸出期限の上限を指定するアノテーション
 * @author NAKAJIMA
 *
 */
@Constraint(validatedBy = ReturnDueDateOverValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnDueDateOver {

	//表示するメッセージ
	String message() default "貸出期限は60日までです。";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.TYPE, ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		ReturnDueDateOver[] value();
	}

}

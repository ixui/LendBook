package jp.co.ixui.controller.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import jp.co.ixui.controller.validator.MatchPasswordValidator;

@Constraint(validatedBy = MatchPasswordValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchPassword {

	//表示するメッセージ
	String message() default "パスワードが一致しません。";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.TYPE, ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		MatchPassword[] value();
	}
}

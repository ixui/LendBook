package jp.co.ixui.controller.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import jp.co.ixui.controller.validator.UserExistsValidator;

/**
 * 社員番号が既に登録されていないかを確認するアノテーション
 * @author NAKAJIMA
 *
 */
@Constraint(validatedBy = UserExistsValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserExists {

		//表示するメッセージ
		String message() default "既に同じ社員番号が登録されています。";
		Class<?>[] groups() default {};
		Class<? extends Payload>[] payload() default {};

		@Target({ ElementType.TYPE, ElementType.FIELD })
		@Retention(RetentionPolicy.RUNTIME)
		@Documented
		@interface List {
			UserExists[] value();
		}
}

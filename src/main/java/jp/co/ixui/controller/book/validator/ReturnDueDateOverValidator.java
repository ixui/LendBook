package jp.co.ixui.controller.book.validator;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.co.ixui.controller.book.validator.annotation.ReturnDueDateOver;
import jp.co.ixui.domain.Lend;

public class ReturnDueDateOverValidator implements ConstraintValidator<ReturnDueDateOver, Lend> {

	@Override
	public void initialize(ReturnDueDateOver constraintAnnotation) {
	}

	@Override
	public boolean isValid(Lend value, ConstraintValidatorContext context) {

		//空文字はNotEmptyで表示する。
		if(value.getReturnDueDate() == ""){
			return true;
		}

		//返却予定日
		String returnDueDate = value.getReturnDueDate();
		//現在の日付
		Date today = new Date();

		//フォーマット作成
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		//returnDueDateをDate型に置き換える
		Date retunDueDateTo = null;
		try {
			retunDueDateTo = sdf.parse(returnDueDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//日付をlong値に変換
		long dateTimeTo = retunDueDateTo.getTime();
		long dateTimeFrom = today.getTime();

		//差分の日付を算出
		long dayDiff = ( dateTimeTo - dateTimeFrom ) / (1000 * 60 * 60 * 24);

		//60日以上の貸出はエラー
		if(dayDiff > 62){
			return false;
		}

		return true;
	}

}

package jp.co.ixui.controller.book.validator;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.domain.Lend;
import jp.co.ixui.mapper.LendMapper;
import jp.co.ixui.service.BookService;

/**
 * {@link ReturnDueDateOverValidator}のユニットテストです。<br>
 * 返却日が正常に入力されているか、また60日を超えていないかを、<br>
 * バリデーションでチェックを行っています。
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReturnDueDateOverValidatorTest {

	/**
	 * テスト対象クラス
	 */
	@InjectMocks
	private ReturnDueDateOverValidator validator;

	/**
	 * バリデーションの設定
	 */
	private ConstraintValidatorContextImpl context;

	/**
	 * 貸出マッパー<br>
	 * モック
	 */
	@Mock
	LendMapper lendMapper;

	/**
	 * 書籍の処理クラス<br>
	 * モック
	 */
	@Mock
	BookService service;

	/**
	 * 返却日が正常に入力されているか、<br>
	 * また60日を超えていないかを確認しています<br>
	 */
	@Test
	public void 貸出日テスト(){

		//翌日を取得
		Calendar nextCal = Calendar.getInstance();
		nextCal.setTime(new Date());
		nextCal.add(Calendar.DAY_OF_MONTH, 1);
		Date nextDate = nextCal.getTime();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
		String nextDay = sdf1.format(nextDate);

		Lend lend = new Lend();
		lend.setReturnDueDate(nextDay);

		when(service.isReturnDueDateOver(anyString())).thenReturn(true);

		assertEquals(true, validator.isValid(lend, context));
	}
}

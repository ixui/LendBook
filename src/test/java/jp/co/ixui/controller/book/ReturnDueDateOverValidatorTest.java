package jp.co.ixui.controller.book;

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

import jp.co.ixui.controller.book.validator.ReturnDueDateOverValidator;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.mapper.LendMapper;
import jp.co.ixui.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReturnDueDateOverValidatorTest {

	@InjectMocks
	private ReturnDueDateOverValidator validator;

	private ConstraintValidatorContextImpl context;

	@Mock
	LendMapper lendMapper;

	@Mock
	BookService service;

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

		validator.isValid(lend, context);
	}
}

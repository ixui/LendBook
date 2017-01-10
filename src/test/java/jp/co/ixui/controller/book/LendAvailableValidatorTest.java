package jp.co.ixui.controller.book;

import static org.mockito.Mockito.*;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.controller.book.validator.LendAvailableValidator;
import jp.co.ixui.domain.Lend;
import jp.co.ixui.mapper.LendMapper;
import jp.co.ixui.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LendAvailableValidatorTest {

	@InjectMocks
	private LendAvailableValidator lendAvailableValidator;

	private ConstraintValidatorContextImpl context;

	@Mock
	LendMapper lendMapper;

	@Mock
	BookService service;

	@Test
	public void 貸出可否テスト(){

		Lend lend = new Lend();
		lend.setBookStockId(100);

		when(service.isLendable(lend.getBookStockId())).thenReturn(true);

		lendAvailableValidator.isValid(lend, context);
	}
}
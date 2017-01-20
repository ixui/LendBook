package jp.co.ixui.controller.book.validator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.domain.Lend;
import jp.co.ixui.mapper.LendMapper;
import jp.co.ixui.service.BookService;

/**
 * {@link LendAvailableValidator}のユニットテストです。<br>
 * 蔵書の貸出履歴、貸出状況から貸出が可能かどうかを、<br>
 * バリデーションでチェックを行っています。
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
public class LendAvailableValidatorTest {

	/**
	 * テスト対象クラス
	 */
	@InjectMocks
	private LendAvailableValidator lendAvailableValidator;

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
	 * 貸出できるかどうかを確認しています。
	 */
	@Test
	public void 貸出可否テスト(){

		Lend lend = new Lend();
		lend.setBookStockId(100);

		when(service.isLendable(lend.getBookStockId())).thenReturn(true);

		assertEquals(true, lendAvailableValidator.isValid(lend, context));
	}
}
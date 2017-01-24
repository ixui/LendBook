package jp.co.ixui.controller.validator;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.controller.NewUserRegistrationForm;
import jp.co.ixui.mapper.MstEmpMapper;
import jp.co.ixui.service.EmpService;

@RunWith(SpringRunner.class)
public class UserExistsValidatorTest {

	/**
	 * テスト対象クラス
	 */
	@InjectMocks
	private UserExistsValidator validator;

	/**
	 * バリデーションの設定
	 */
	private ConstraintValidatorContext context;

	/**
	 * 書籍マッパー<br>
	 * モック
	 */
	@Mock
	private MstEmpMapper mapper;

	/**
	 * 書籍の処理クラス<br>
	 * モック
	 */
	@Mock
	EmpService service;

	@Test
	public void ユーザが存在している時にFalseを返す(){

		NewUserRegistrationForm form = new NewUserRegistrationForm();
		form.setEmpNum(5010);

		when(service.isUserRegistered(anyInt())).thenReturn(false);

		assertEquals(false, validator.isValid(form, context));

	}
}

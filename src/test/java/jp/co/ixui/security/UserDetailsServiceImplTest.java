package jp.co.ixui.security;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.service.EmpService;

/**
 * <p>{@link UserDetailsServiceImpl}のユニットテストです。</p>
 * メールアドレスが存在するか確認し、ユーザ情報を取得します。
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
public class UserDetailsServiceImplTest {

	/**
	 * インジェクト対象
	 */
	@InjectMocks
	UserDetailsServiceImpl userDetailsServiceImpl;

	/**
	 * モック化
	 */
	@Mock
	EmpService service;

	/**
	 * メールアドレスが空文字だった場合のエラーメッセージをチェック
	 */
	@Test
	public void メールアドレスが空文字だった場合の確認(){

		try{
			userDetailsServiceImpl.loadUserByUsername("");
		}catch(UsernameNotFoundException e){
			assertEquals("MailAddress is empty", e.getMessage());
		}
	}

	/**
	 * ユーザが存在しない場合のエラーメッセージをチェック
	 */
	@Test
	public void 存在しないユーザーの認証(){

		try{
		userDetailsServiceImpl.loadUserByUsername("null@tosyo.co.jp");
		}catch(Exception e){
			assertEquals("User not found for name: null@tosyo.co.jp", e.getMessage());
		}
	}

	/**
	 * ユーザ情報が正常に取得できるかをチェック
	 */
	@Test
	public void ユーザ情報を取得(){
		String mailAddress = "test@tosyo.co.jp";

		MstEmp mstEmp = new MstEmp();
		mstEmp.setEmpName("test");
		mstEmp.setAdminFrag("0");
		mstEmp.setEmpNum(5010);
		mstEmp.setMailAddress(mailAddress);
		mstEmp.setPassword("test");

		when(service.getUser(mailAddress)).thenReturn(mstEmp);
		userDetailsServiceImpl.loadUserByUsername(mailAddress);

		assertEquals(mstEmp, service.getUser(mailAddress));
	}
}

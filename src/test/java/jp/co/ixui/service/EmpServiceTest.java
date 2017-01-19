package jp.co.ixui.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ixui.domain.MstEmp;
import jp.co.ixui.mapper.MstEmpMapper;

/**
 * {@link EmpService}のユニットテストです。<br>
 * ユーザに関する処理を行っています。
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
public class EmpServiceTest {

	/**
	 * テスト対象クラス
	 */
	@InjectMocks
	EmpService service;

	/**
	 * ユーザマッパー
	 */
	@Mock
	MstEmpMapper mstEmpMapper;

	/**
	 * ユーザ
	 */
	MstEmp mstEmp = new MstEmp();

	@Before
	public void 事前準備(){
		mstEmp.setEmpName("test");
		mstEmp.setAdminFrag("0");
		mstEmp.setEmpNum(5010);
		mstEmp.setMailAddress("test@tosyo.co.jp");
		mstEmp.setPassword("test");
	}

	/**
	 * {@link EmpService#getUser(String)}のテストです。<br>
	 * ユーザの情報が正しく取得できるかを確認しています。
	 */
	@Test
	public void ユーザ情報を正しく取得できるか確認(){

		when(mstEmpMapper.getUser(anyString())).thenReturn(mstEmp);

		assertEquals("test", service.getUser(anyString()).getEmpName());
		assertEquals(5010, service.getUser(anyString()).getEmpNum());
		assertEquals("test@tosyo.co.jp", service.getUser(anyString()).getMailAddress());
	}
}

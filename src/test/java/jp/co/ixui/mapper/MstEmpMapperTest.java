package jp.co.ixui.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ixui.domain.MstEmp;

/**
 * {@link MstEmpMapper}のユニットテストです。<br>
 * @author NAKAJIMA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MstEmpMapperTest {

	/**
	 * ユーザマッパー
	 */
	@Autowired
	MstEmpMapper mapper;

	/**
	 * ユーザ
	 */
	MstEmp mstEmp = new MstEmp();

	/**
	 * ユーザ情報の値を設定しています。
	 */
	@Before
	public void 事前準備(){
    	//ユーザ
		mstEmp.setEmpName("test");
		mstEmp.setAdminFrag("0");
		mstEmp.setEmpNum(5010);
		mstEmp.setMailAddress("test@tosyo.co.jp");
		mstEmp.setPassword("test");
	}

	/**
	 * ユーザ登録を作成していません。
	 */
	@Test
	@Transactional
	public void ユーザ情報を取得(){
	}
}
